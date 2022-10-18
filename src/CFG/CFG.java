package CFG;

import CFG.Node.*;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CFG {
    private final ArrayList<String> lines;
    private final Parser parser;
    private Node start, end;
    private final ArrayList<Node> nodes = new ArrayList<>();

    public CFG(String filePath) throws FileNotFoundException {
        this.parser = new Parser(filePath);
        this.lines = this.parser.getLines();
        lineToNode();
        start = nodes.get(0);
        end = nodes.get(nodes.size()-1);
    }

    private void lineToNode() {
        for (String line: lines) {
            Node node = parser.buildNode(line);
            this.nodes.add(node);
        }
    }

    public void buildTree() {
        ArrayList<Node> ifJumpOutPoints = new ArrayList<>();
        Node parent = null;
        Node parentIf = null;
        FORBlock parentFor = null;
        WHILEBlock parentWhile = null;
        DOPoint parentDo  = null;

        boolean hadIf = false;
        boolean hadElseIf = false;
        boolean hadElse = false;
        boolean hadDo = false;
        boolean hadWhile = false;
        boolean hadFor = false;

        for (Node node : nodes) {
            if (parent == null) {
                parent = node;
            }
            else {
                if (node instanceof IFBlock) {
                    hadIf = true;
                    setParent(parent, node);
                    parentIf = node;
                    parent = node;
                }
                else if (node instanceof ELSEIFBlock) {
                    hadElseIf = true;

                    if(parentIf instanceof IFBlock) {
                        setParent(parentIf, node);
                    }
                    else if (parentIf instanceof ELSEIFBlock) {
                        setParent(parentIf, node);
                    }

                    parentIf = node;
                    parent = node;
                }
                else if (node instanceof ELSEBlock) {
                    hadElse = true;

                    if(parentIf instanceof IFBlock) {
                        setParent(parentIf, node);
                    }
                    else if (parentIf instanceof ELSEIFBlock) {
                        setParent(parentIf, node);
                    }

                    parent = node;
                }
                else if (node instanceof WHILEBlock) {
                    setParent(parent, node);
                    if(hadDo){
                        hadDo = false;
                        setParent(node, parentDo);
                    }
                    else {
                        hadWhile = true;
                        parentWhile = (WHILEBlock) node;
                    }
                    parent = node;
                }
                else if (node instanceof FORBlock) {
                    hadFor = true;
                    setParent(parent, node);
                    parentFor = (FORBlock) node;
                    parent = node;
                }
                else if (node instanceof DOPoint) {
                    hadDo = true;
                    setParent(parent, node);
                    parentDo = (DOPoint) node;
                    parent = node;
                }
                else {
                    if (!hadIf && !hadWhile && !hadFor && !hadDo && !hadElseIf && !hadElse) {
                        if(ifJumpOutPoints.size() > 0) {
                            for (Node par: ifJumpOutPoints) {
                                setParent(par, node);
                            }
                            ifJumpOutPoints.clear();
                        }
                        else {
                            setParent(parent, node);
                        }
                        parent = node;
                    }
                    else if (hadIf) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadIf = false;
                            ifJumpOutPoints.add(node);
                        }
                        else {
                            parent = node;
                        }
                    }
                    else if (hadElseIf) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadElseIf = false;
                            ifJumpOutPoints.add(node);
                        }
                        else {
                            parent = node;
                        }
                    }
                    else if(hadElse) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadElse = false;
                            ifJumpOutPoints.add(node);
                        }
                        else {
                            parent = node;
                        }
                    }
                    else if (hadDo) {
                        setParent(parent, node);
                        parent = node;
                    }
                    else if (hadWhile) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadWhile = false;
                            setParent(node, parentWhile);
                            parent = parentWhile;
                        }
                        else {
                            parent = node;
                        }
                    }
                    else if(hadFor) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadFor = false;
                            setParent(node, parentFor);
                            parent = parentFor;
                        }
                        else {
                            parent = node;
                        }
                    }
                }
            }
        }
    }

    private void setParent(Node parent, Node child) {
        parent.childs.add(child);
    }

    public void printLinesWithTag() {
        for (Node node: nodes) {
            System.out.print(node.line());
            if(node instanceof IFBlock)             System.out.println("\tif");
            else if (node instanceof ELSEIFBlock)   System.out.println("\telse  if");
            else if (node instanceof ELSEBlock)     System.out.println("\telse");
            else if (node instanceof DOPoint)       System.out.println("\tdo");
            else if (node instanceof WHILEBlock)    System.out.println("\twhile");
            else if (node instanceof FORBlock)      System.out.println("\tfor");
            else if (node instanceof preprocess)    System.out.println("\tpreprocess");
            else if (node instanceof declaration)   System.out.println("\tdeclaration");
            else if (node instanceof statement)     System.out.println("\tstatement");
        }
    }

    public void printChild() {
        for (Node node: nodes) {
            System.out.println("node-"+"[ "+node.line+" ]");
            for (Node child : node.childs) {
                System.out.println("\t\t\t"+"-child-"+"[ "+child.line+" ]");
            }
        }
    }

    private int countEdge() {
        int edgeCount = 0;
        for (Node node: nodes) {
            edgeCount += node.childs.size();
        }
        return edgeCount;
    }

    public int cyclomaticComplexity() {
        int complexity = 0;
        int edge_count = countEdge(), nodes = this.nodes.size();
        complexity = edge_count - nodes + 2;
        return complexity;
    }
}

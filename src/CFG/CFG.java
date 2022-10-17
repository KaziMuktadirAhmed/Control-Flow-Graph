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
                        ((IFBlock) parentIf).setJumpTOElseIf((ELSEIFBlock) node);
                        ((IFBlock) parentIf).hasElseIf = true;
                    }
                    else if (parentIf instanceof ELSEIFBlock) {
                        ((ELSEIFBlock) parentIf).setJumpTOElseIf((ELSEIFBlock) node);
                        ((ELSEIFBlock) parentIf).hasElseIf = true;
                    }

                    parentIf = node;
                    parent = node;
                }
                else if (node instanceof ELSEBlock) {
                    hadElse = true;

                    if(parentIf instanceof IFBlock) {
                        ((IFBlock) parentIf).setJumpTOElse((ELSEBlock) node);
                        ((IFBlock) parentIf).hasElse = true;
                    }
                    else if (parentIf instanceof ELSEIFBlock) {
                        ((ELSEIFBlock) parentIf).setJumpTOElse((ELSEBlock) node);
                        ((ELSEIFBlock) parentIf).hasElse = true;
                    }

                    parent = node;
                }
                else if (node instanceof WHILEBlock) {
                    hadWhile = true;
                    setParent(parent, node);
                    parentWhile = (WHILEBlock) node;
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
                        else setParent(parent, node);
                        parent = node;
                    }
                    else if (hadIf) {
                        setParent(parent, node);
//                        parent = node;
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
//                        parent = node;
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
//                        parent = node;
                        if(node.line.contains("}")) {
                            hadElse = false;
                            ifJumpOutPoints.add(node);
                        }
                        else {
                            parent = node;
                        }
                    }
                    else if (hadDo) {
                        hadDo = false;
                    }
                    else if (hadWhile) {
                        hadWhile = false;
                    }
                    else if(hadFor) {
                        setParent(parent, node);
                        if(node.line.contains("}")) {
                            hadFor = false;
                            parentFor.setJumpTO(node);
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
            if(node instanceof preprocess) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof declaration) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof statement) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof IFBlock) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof ELSEIFBlock) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof ELSEBlock) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof FORBlock) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof WHILEBlock) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
            else if(node instanceof DOPoint) System.out.println("node-"+node.line+"-child-"+node.childs.get(0).line);
        }
    }
}

package CFG;

import CFG.Node.*;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CFG {
    private final ArrayList<String> lines;
    private final Parser parser;

    private Node start, end;
    private ArrayList<Node> nodes = new ArrayList<>();

    public CFG(String filePath) throws FileNotFoundException {
        this.parser = new Parser(filePath);
        this.lines = this.parser.getLines();
    }


    public void buildTree() {
        for (String line: lines) {
            Node node = parser.buildNode(line);
            this.nodes.add(node);
        }

        start = nodes.get(0);
        end = nodes.get(nodes.size()-1);



        for (int i = 0; i < nodes.size(); i++) {

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

    private void setChild() {}
}

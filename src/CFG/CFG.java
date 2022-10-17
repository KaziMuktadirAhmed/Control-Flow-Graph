package CFG;

import CFG.Node.*;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CFG {
    private final ArrayList<String> lines;
    private final Parser parser;

    private Node start;
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

        for (Node node: nodes) {
            if(node instanceof preprocess) start = node;
            System.out.println(node.line());
        }
    }

    private void setChild() {}
}

package CFG;

import CFG.Node.*;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CFG {
    private String filePath;
    private ArrayList<String> lines;
    private Parser parser;
    private ArrayList<Node> nodes = new ArrayList<>();

    public CFG(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.parser = new Parser(filePath);
    }

    public void buildTree() {}
}

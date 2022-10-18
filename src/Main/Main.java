package Main;

import CFG.CFG;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Hello world!");
        test();
    }

    public static void test() throws FileNotFoundException {
        CFG cfg = new CFG("test.txt");
        cfg.buildTree();
//        cfg.printLinesWithTag();
        cfg.printChild();
        System.out.println("\nCyclomatric complexity: " + cfg.cyclomatricComplexity());
    }
}
package Parser;

import CFG.Node.Node;
import CFG.Node.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private final ArrayList<String> lines = new ArrayList<>();
    private final ArrayList<String> statements = new ArrayList<>();

    public Parser(String filePath) throws FileNotFoundException {
        fileToString(filePath);
    }

    private void fileToString(String filePath) throws FileNotFoundException {
        File input_file = new File(filePath);
        Scanner scan_file = new Scanner(input_file);
        while (scan_file.hasNextLine()) lines.add(scan_file.nextLine());
    }

    private Node tokenizeWords(String line) {
        Node node = null;

        if(line.contains("#")) {
            extractPreprocessLine(line);
            node = new preprocess(line);
        }

        return node;
    }

    private static void extractPreprocessLine(String line) {
        String[] chars = line.split("");
        String temp = "";
        boolean flag = true;
        for (String c:chars) {
            if(c.equals(" ") && flag) continue;
            else {
                temp += c;
                flag = false;
            }
        }
    }

//    private Node buildNode (String line) {
//        Node node = new Node(line);
//        return node;
//    }

//    public void testFunc() {
//        ArrayList<String> tokens = tokenizeWords(lines.get(0));
//        for (String token: tokens) {
//            System.out.println(token);
//        }
//    }

    public void printFile () {
        int line_count = 1;
        for (String line : lines) {
            System.out.println(line_count + ": " + line);
            line_count++;
        }
    }
}

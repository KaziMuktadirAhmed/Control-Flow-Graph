package Parser;

import CFG.Node.*;

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
            String l =extractPreprocessLine(line);
            node = new preprocess(l);
        }

        else if (line.contains("else if")) {
            String cond = extractCondition(line);
            node = new ELSEIFBlock(line, cond);
        }

        else if (line.contains("if")) {
            String cond = extractCondition(line);
            node = new IFBlock(line, cond);
        }

        else if (line.contains("else")) {
            node = new ELSEBlock(line);
        }

        else if (line.contains("do")) {

        }

        else if (line.contains("while")) {
            String condition = extractCondition(line);
            node = new WHILEBlock(line, condition);
        }

        else if (line.contains("for")) {

        }

        else if (line.contains("void") || line.contains("int") || line.contains("float") || line.contains("double")) {

        }

        else {

        }
        refineNode(node);

        return node;
    }

    private void refineNode(Node node) {
    }

    private String extractCondition(String line) {
        String[] chars = line.split("");
        String condition = "";
        String trimmedLeadingSpace = "";
        boolean flag = true;

        for (String c: chars) {
            if(c.equals(" ") && flag) continue;
            else {
                trimmedLeadingSpace += c;
                flag = false;
            }
        }

        chars = trimmedLeadingSpace.split("");
        flag = false;

        for(String c: chars){
            if(!flag && c.equals("(")) continue;
            else {
                if (c.equals(")"))
                    flag = false;
                else {
                    condition += c;
                    flag = true;
                }
            }
        }

        return condition;
    }

    private static String extractPreprocessLine(String line) {
        String[] chars = line.split("");
        String linePreprocess = "";
        boolean flag = true;
        for (String c:chars) {
            if(c.equals(" ") && flag) continue;
            else {
                linePreprocess += c;
                flag = false;
            }
        }
        return linePreprocess;
    }

    public ArrayList<String> getLines() {
        return lines;
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

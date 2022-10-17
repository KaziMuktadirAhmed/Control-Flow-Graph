package Parser;

import CFG.Node.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private final ArrayList<String> lines = new ArrayList<>();

    public Parser(String filePath) throws FileNotFoundException {
        fileToString(filePath);
    }

    private void fileToString(String filePath) throws FileNotFoundException {
        File input_file = new File(filePath);
        Scanner scan_file = new Scanner(input_file);
        while (scan_file.hasNextLine()) lines.add(scan_file.nextLine());
    }

    public Node buildNode(String line) {
        Node node = null;

        if(line.contains("#")) {
            String l = trimLeadingSpace(line);
            node = new preprocess(l);
        }

        else if (line.contains("else if")) {
            String cond = extractCondition(line);
            node = new ELSEIFBlock(trimLeadingSpace(line), cond);
        }

        else if (line.contains("if")) {
            String cond = extractCondition(line);
            node = new IFBlock(trimLeadingSpace(line), cond);
        }

        else if (line.contains("else")) {
            String l = trimLeadingSpace(line);
            node = new ELSEBlock(l);
        }

        else if (line.contains("do")) {
            String l = trimLeadingSpace(line);
            node = new DOPoint(l);
        }

        else if (line.contains("while")) {
            String condition = extractCondition(line);
            node = new WHILEBlock(trimLeadingSpace(line), condition);
        }

        else if (line.contains("for")) {
            String[] innner_for = extractFor(line);
            node = new FORBlock(trimLeadingSpace(line), innner_for[0], innner_for[1], innner_for[2]);
        }

        else if (line.contains("void ") || line.contains("int ") || line.contains("float ") || line.contains("double ")) {
            String l = trimLeadingSpace(line);
            node = new declaration(l);
        }

        else {
            String l = trimLeadingSpace(line);
            node = new statement(l);
        }

        return node;
    }

    private String[] extractFor(String line) {
        String[] inner_for = {"", "", ""};
        String[] chars = trimLeadingSpace(line).split("");

        int flag = 0;

        for (String c: chars) {
            if(c.equals("(")){
                flag++;
            }
            if(flag == 1) {
                if(c.equals(";")) {
                    flag++;
                    continue;
                }
                inner_for[0] += c;
            }
            else if (flag == 2) {
                if(c.equals(";")){
                    flag++;
                    continue;
                }
                inner_for[1] += c;
            }
            else if (flag == 3) {
                if(c.equals(";")) {
                    flag++;
                    continue;
                }
                inner_for[2] += c;
            }
        }

        return inner_for;
    }

    private String extractCondition(String line) {
        String trimmedLine = trimLeadingSpace(line);
        String[] chars = trimmedLine.split("");
        String condition = "";
        boolean flag = false;

        for(String c: chars){
            if(!flag) {
                if(c.equals("(")) flag = true;
            }
            else {
                if (c.equals(")"))
                    flag = false;
                else {
                    condition += c;
                }
            }
        }

        return condition;
    }

    private static String trimLeadingSpace(String line) {
        String[] chars = line.split("");
        String l = "";
        boolean flag = true;
        for (String c:chars) {
            if((c.equals(" ") || c.equals("\t")) && flag) continue;
            else {
                l += c;
                flag = false;
            }
        }
        return l;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void testFunc() {
        for (String line:lines) {
            Node node = buildNode(line);
            System.out.println(node.line());

            if(node instanceof WHILEBlock)
                System.out.println("condition-" + ((WHILEBlock) node).condition);
        }
    }

    public void printFile () {
        int line_count = 1;
        for (String line : lines) {
            System.out.println(line_count + ": " + line);
            line_count++;
        }
    }
}

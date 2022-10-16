package Parser;

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
//        tokenizeWords(lines.get(0));
    }

    private ArrayList<String> tokenizeWords(String line) {
        String[] char_arr = line.split("");
        ArrayList<String> tokens = new ArrayList<>();
        String temp = "";
        for (int i=0; i<char_arr.length; i++) {
            String c = char_arr[i];
            if(!c.equals(" ") && i < char_arr.length-1) temp += c;
            else if (temp.length() > 0) {
                if(i == char_arr.length-1) temp += c;
                tokens.add(temp);
                temp = "";
            }
        }
        return tokens;
    }

    public void testFunc() {
        ArrayList<String> tokens = tokenizeWords(lines.get(2));
        for (String token: tokens) {
            System.out.println(token);
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

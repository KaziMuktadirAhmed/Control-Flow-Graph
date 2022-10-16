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
    }

    private void tokenize(String line) {

    }

    public void testFunc() {
        for (String line: lines) {
            int word_count = 1;
            String[] words = line.split(" ");
            for (String word: words) {
                System.out.print(word_count + ": " + word + " ");
                word_count++;
            }
            System.out.println();
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

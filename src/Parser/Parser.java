package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private final String original_input;
    private final ArrayList<String> lines = new ArrayList<>();

    public Parser(String filePath) throws FileNotFoundException {
        this.original_input = fileToString(filePath);
    }

    private String fileToString(String filePath) throws FileNotFoundException {
        String fileString = "";
        File input_file = new File(filePath);
        Scanner scan_file = new Scanner(input_file);
        while (scan_file.hasNextLine()){
            String temp_line = scan_file.nextLine();
            fileString += temp_line;
            lines.add(temp_line);
        }
        return fileString;
    }

    public void printFile () {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}

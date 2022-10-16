package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private String original_input;

    public Parser(String filePath) throws FileNotFoundException {
        this.original_input = fileToString(filePath);
    }

    public String fileToString(String filePath) throws FileNotFoundException {
        String fileString = "";
        File input_file = new File(filePath);
        Scanner scan_file = new Scanner(input_file);
        while (scan_file.hasNextLine()) fileString += scan_file.nextLine();
        return fileString;
    }
}

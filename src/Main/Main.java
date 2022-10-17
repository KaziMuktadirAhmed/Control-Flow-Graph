package Main;

import Parser.Parser;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        test();
    }

    public static void test() throws FileNotFoundException {
        Parser parse_file = new Parser("test.txt");
        parse_file.testFunc();
//        parse_file.printFile();
    }
}
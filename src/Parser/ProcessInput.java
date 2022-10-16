package Parser;

import java.io.File;

public class ProcessInput {
    File input_file;

    public ProcessInput (String filePath) {
        this.input_file = new File(filePath);
    }

    public String fileToString() {
        String fileString = "";
        return fileString;
    }
}

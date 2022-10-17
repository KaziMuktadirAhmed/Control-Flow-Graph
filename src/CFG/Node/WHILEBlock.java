package CFG.Node;

public class WHILEBlock extends Node{
    public String condition;

    public WHILEBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }
}
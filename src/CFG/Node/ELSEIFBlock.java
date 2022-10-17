package CFG.Node;

public class ELSEIFBlock extends Node{
    public String condition;

    public ELSEIFBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }
}

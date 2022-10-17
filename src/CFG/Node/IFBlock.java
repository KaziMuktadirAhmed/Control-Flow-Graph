package CFG.Node;

public class IFBlock extends Node{
    public String condition;

    public IFBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }
}

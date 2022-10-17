package CFG.Node;

public class FORBlock extends Node{
    public String initialization, condition, increment;
    public Node jumpTO;

    public FORBlock(String line, String initialization, String condition, String increment) {
        super(line);
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
    }

    public void setJumpTO(Node node) {
        this.jumpTO = node;
    }
}

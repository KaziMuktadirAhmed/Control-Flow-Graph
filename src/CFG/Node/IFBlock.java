package CFG.Node;

public class IFBlock extends Node{
    public String condition;
    public boolean hasElse = false;
    public boolean hasElseIf = false;
    private Node jumpTO;

    public IFBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }

    public Node jumpTO() {
        return this.jumpTO;
    }

    public void setJumpTOStatement(Node statement) {
        this.jumpTO = statement;
    }

    public void setJumpTOElse(ELSEBlock elseBlock) {
        hasElse = true;
        this.jumpTO = elseBlock;
    }

    public void setJumpTOElseIf(ELSEIFBlock elseifBlock) {
        hasElseIf = true;
        this.jumpTO = elseifBlock;
    }
}

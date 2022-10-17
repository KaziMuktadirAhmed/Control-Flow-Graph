package CFG.Node;

public class ELSEIFBlock extends Node{
    public String condition;
    public boolean hasElse = false;
    public boolean hasElseIf = false;
    private Node jumpTO;

    public ELSEIFBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }

    public Node jumpTO() {
        return this.jumpTO;
    }

    public void setJumpTOStatement(statement statement) {
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

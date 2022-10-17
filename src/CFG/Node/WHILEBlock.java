package CFG.Node;

public class WHILEBlock extends Node{
    public String condition;
    private boolean isDoWhile = false;
    private Node jumpTO = null;

    public WHILEBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }

    public boolean isDoWhile() {
        return this.isDoWhile;
    }

    public void setJumpToDo(DOPoint DoBlock) {
        this.isDoWhile = true;
        this.jumpTO = DoBlock;
    }

    public void setJumpTOStatement(Node statement){
        this.isDoWhile = false;
        this.jumpTO = statement;
    }

    public Node jumpTO() {
        return this.jumpTO;
    }
}
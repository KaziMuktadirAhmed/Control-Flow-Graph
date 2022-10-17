package CFG.Node;

public class WHILEBlock extends Node{
    public String condition;
    private boolean isDoWhile = false;
    private Node pointToDo = null;

    public WHILEBlock(String line, String condition) {
        super(line);
        this.condition = condition;
    }

    public boolean isDoWhile() {
        return this.isDoWhile;
    }

    public void setPointToDo(Node DoBlock) {
        this.isDoWhile = true;
        this.pointToDo = DoBlock;
    }

    public Node gotoDoBlock() {
        return this.pointToDo;
    }
}
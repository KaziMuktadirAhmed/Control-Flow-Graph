package CFG;

import java.util.ArrayList;

public class Node {
    private String name;
    private String line;
    public ArrayList<Node> childs;

    public Node(String line) {
        this.line = line;
    }

    public void setName(String name) { this.name = name; }
    public String name() {  return this.name;   }
    public String line() {  return this.line;   }
}

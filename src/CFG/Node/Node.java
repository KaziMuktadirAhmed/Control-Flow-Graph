package CFG.Node;

import java.util.ArrayList;

public abstract class Node {
    public String name;
    public final String line;
    public ArrayList<Node> childs = new ArrayList<>();

    public Node(String line) {  this.line = line;   }
    public void setName(String name) { this.name = name; }
    public String name() {  return this.name;  }
    public String line() {  return this.line;  }
}

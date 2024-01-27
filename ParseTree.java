package Phase2;

import java.util.ArrayList;

public class ParseTree {
    private ArrayList<ParseTreeNode> nodes;

    public ParseTree(){
        this.nodes = new ArrayList<ParseTreeNode>();
    }

    public ArrayList<ParseTreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ParseTreeNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(ParseTreeNode parseTreeNode){
        this.nodes.add(parseTreeNode);
    }

    // method to display whole parse tree
    public void show(){
        System.out.println("Program");
        for (ParseTreeNode node : nodes){
            node.show();
        }
    }
}

package Phase2;

public class ParseTreeNode {
    // Data members
    private String content;
    private int indentLevel;

    // constructors
    public ParseTreeNode(String content, int indentLevel){
        this.content = content;
        this.indentLevel = indentLevel;
    }
    public ParseTreeNode(){};

    // setters and getters
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getIndentLevel() {
        return indentLevel;
    }
    public void setIndentLevel(int indentLevel) {
        this.indentLevel = indentLevel;
    }

    // method to print node content and indent it
    public void show(){
        System.out.print("|");
        for (int i = 0; i<indentLevel; i++){
            System.out.print(" |");
        }
        System.out.println("-- " + content);
    }
}

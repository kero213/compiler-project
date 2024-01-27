package Phase2;

import Phase1.Lexeme;

import java.util.ArrayList;

public class Parser {

    // data members
    private ArrayList<Lexeme> lexemes;
    private int currentIndex;
    private int currentIndent;
    private ParseTree parseTree;

    // constructor
    public Parser(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
        this.currentIndex = 0;
        this.currentIndent = 0;
        this.parseTree = new ParseTree();
    }

    // setters and getters
    public ArrayList<Lexeme> getLexemes() {
        return lexemes;
    }

    public void setLexemes(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public ParseTree getParseTree() {
        return parseTree;
    }

    public void setParseTree(ParseTree parseTree) {
        this.parseTree = parseTree;
    }


    // method which starts parsing process
    public void parseCode() {
        ParseTreeNode root = new ParseTreeNode("Text Section", currentIndent);
        parseTree.addNode(root);
        currentIndent += 1;


        // switch case to call suitable parsing method according to instruction token
        Lexeme current = lexemes.get(currentIndex);
        while (currentIndex < lexemes.size()) {
            current = lexemes.get(currentIndex);
            switch (current.getToken()) {
                case "Load" -> parseLoadInstruction();
                case "Add" -> parseAddInstruction();
                case "BranchNotEqual" -> parseBranchNotEqualInstruction();
                case "Branch" -> parseBranchInstruction();
                case "Move" -> parseMoveInstruction();
                default -> throw new RuntimeException("Syntax Error! Expected a Keyword (Instruction) token. Instead found: " + current.getToken() + " Lexeme number: " + ++currentIndex);
            }
        }
        parseTree.addNode(new ParseTreeNode("Exit", 0));
    }

    // method to parse a single identifier
    public void parseIdentifier(){
        Lexeme lexeme = lexemes.get(currentIndex);
        if (!(lexeme.getToken().equals("Identifier"))){
            throw new RuntimeException("Syntax error! Expected an Identifier token. Instead found: " + lexeme.getToken() + " Lexeme number: " + ++currentIndex);
        }
        String content = lexeme.getToken() + ": " + lexeme.getName();
        ParseTreeNode node = new ParseTreeNode(content, currentIndent);
        parseTree.addNode(node);
        currentIndex += 1;
    }

    // method to parse a single register
    public void parseRegister(){
        Lexeme lexeme = lexemes.get(currentIndex);
        if (!(lexeme.getToken().equals("Register"))){
            throw new RuntimeException("Syntax error! Expected a Register token. Instead found: " + lexeme.getToken() + " Lexeme number: " + ++currentIndex);
        }
        String content = lexeme.getToken() + ": " + lexeme.getName();
        ParseTreeNode node = new ParseTreeNode(content, currentIndent);
        parseTree.addNode(node);
        currentIndex += 1;
    }

    // method to parse a single comma
    public void parseComma(){
        Lexeme lexeme = lexemes.get(currentIndex);
        if (!(lexeme.getToken().equals("Comma"))){
            throw new RuntimeException("Syntax error! Expected a Comma token. Instead found: " + lexeme.getToken() + " Lexeme number: " + ++currentIndex);
        }
        String content = lexeme.getToken() + ": " + lexeme.getName();
        ParseTreeNode node = new ParseTreeNode(content, currentIndent);
        parseTree.addNode(node);
        currentIndex += 1;
    }

    // method to parse a label
    public void parseLabel(String label){
        Lexeme lexeme = lexemes.get(currentIndex);
        if (!(lexeme.getToken().equals("Label"))){
            throw new RuntimeException("Syntax error! Expected a Label token. Instead found: " + lexeme.getToken() + " Lexeme number: " + ++currentIndex);
        }
        String content = "Label: " + label;
        ParseTreeNode node = new ParseTreeNode(content, currentIndent);
        parseTree.addNode(node);
        currentIndex += 1;
    }

    // method to parse a lw instruction and its operands
    public void parseLoadInstruction() {
        currentIndex += 1;
        Lexeme lexeme = lexemes.get(currentIndex);
        ParseTreeNode node = new ParseTreeNode("Load Instruction", currentIndent);
        parseTree.addNode(node);
        currentIndent += 1;

        ParseTreeNode node2 = new ParseTreeNode("Load: lw", currentIndent);
        ParseTreeNode node3 = new ParseTreeNode("Operands", currentIndent);

        parseTree.addNode(node2);
        parseTree.addNode(node3);
        currentIndent += 1;

        parseRegister();

        //lexeme = lexemes.get(currentIndex);
        /*if (lexeme.getToken().equals("Comma")){
            parseComma();
            lexeme = lexemes.get(currentIndex);
            parseIdentifier();
        }*/


        currentIndent = 1;
    }

    // method to parse add instruction and its operands
    public void parseAddInstruction() {
        currentIndex += 1;
        Lexeme lexeme = lexemes.get(currentIndex);
        ParseTreeNode node = new ParseTreeNode("Add instruction", currentIndent);
        parseTree.addNode(node);
        currentIndent += 1;

        ParseTreeNode node2 = new ParseTreeNode("Add: add", currentIndent);
        ParseTreeNode node3 = new ParseTreeNode("Operands", currentIndent);

        parseTree.addNode(node2);
        parseTree.addNode(node3);
        currentIndent += 1;

        if (lexeme.getToken().equals("Register")){
            parseRegister();
        }
        else {
            parseIdentifier();
        }

        lexeme = lexemes.get(currentIndex);
        parseComma();

        lexeme = lexemes.get(currentIndex);
        if (lexeme.getToken().equals("Register")){
            parseRegister();
        }
        else {
            parseIdentifier();
        }

        lexeme = lexemes.get(currentIndex);
        parseComma();

        lexeme = lexemes.get(currentIndex);
        if (lexeme.getToken().equals("Register")){
            parseRegister();
        }
        else {
            parseIdentifier();
        }
        currentIndent = 1;
    }

    // method to parse bne instruction and its operands
    public void parseBranchNotEqualInstruction(){
        currentIndex += 1;
        Lexeme lexeme = lexemes.get(currentIndex);
        ParseTreeNode node = new ParseTreeNode("BranchNotEqual instruction", currentIndent);
        parseTree.addNode(node);
        currentIndent += 1;

        ParseTreeNode node2 = new ParseTreeNode("BranchNotEqual: bne", currentIndent);
        ParseTreeNode node3 = new ParseTreeNode("Operands", currentIndent);

        parseTree.addNode(node2);
        parseTree.addNode(node3);
        currentIndent += 1;

        if (lexeme.getToken().equals("Register")){
            parseRegister();
        }
        else {
            parseIdentifier();
        }

        lexeme = lexemes.get(currentIndex);
        parseComma();

        lexeme = lexemes.get(currentIndex);
        if (lexeme.getToken().equals("Register")){
            parseRegister();
        }
        else {
            parseIdentifier();
        }

        lexeme = lexemes.get(currentIndex);
        parseComma();

        lexeme = lexemes.get(currentIndex);
        parseLabel(lexeme.getName());

        currentIndent = 1;
    }

    // method to parse a b instruction
    public void parseBranchInstruction(){
        currentIndex += 1;
        Lexeme lexeme = lexemes.get(currentIndex);
        ParseTreeNode node1 = new ParseTreeNode("Branch", currentIndent);
        parseTree.addNode(node1);

        currentIndent += 1;
        ParseTreeNode node2 = new ParseTreeNode("branch: b", currentIndent);
        parseTree.addNode(node2);

        parseLabel(lexeme.getName());
        currentIndent = 1;
    }

    // method to parse move instruction and its operands
    public void parseMoveInstruction() {
        currentIndex += 1;
        Lexeme lexeme = lexemes.get(currentIndex);
        ParseTreeNode node1 = new ParseTreeNode("Move instruction", currentIndent);
        parseTree.addNode(node1);

        currentIndent += 1;
        ParseTreeNode node2 = new ParseTreeNode("Move: move", currentIndent);
        ParseTreeNode node3 = new ParseTreeNode("Operands", currentIndent);
        parseTree.addNode(node2);
        parseTree.addNode(node3);
        currentIndent += 1;

        if (lexeme.getToken().equals("Register")) {
            parseRegister();
        } else {
            parseIdentifier();
        }

        lexeme = lexemes.get(currentIndex);
        parseComma();

        lexeme = lexemes.get(currentIndex);
        if (lexeme.getToken().equals("Register")) {
            parseRegister();
        } else {
            parseIdentifier();
        }

        currentIndent = 1;
    }
}

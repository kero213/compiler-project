package Phase1;

import java.util.ArrayList;

public class Lexer {

    // Attributes
    private ArrayList<Token> tokens;
    private ArrayList<Lexeme> lexemes;

    // Constructor
    public Lexer(ArrayList<Token> tokens){
        this.tokens = tokens;
        lexemes = new ArrayList<Lexeme>();
    }

    // Getters and setters
    public ArrayList<Lexeme> getLexemes() {
        return lexemes;
    }

    public void setLexemes(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    // findLexemes method to scan source code and separate it into lexemes
    public void findLexemes(String source){
        // Splitting source code into lines and removing comments
        String[] lines = source.split("\n");
        for (String line : lines) {
            if (line.contains("#")) {
                line = line.substring(0, line.indexOf("#"));
            }

            // scanning each line character by character and finding lexemes
            for (int i = 0; i<line.length(); i++){
                char c = line.charAt(i);
                StringBuilder lexeme = new StringBuilder();

                while (Character.isLetter(c) | Character.isDigit(c) | c == '$'){
                    lexeme.append(c);
                    i++;
                    c = line.charAt(i);
                }

                if (!lexeme.isEmpty()) {
                    this.lexemes.add(new Lexeme(lexeme.toString()));
                }

                if (c == '(' | c== ')' | c == ',' ){
                    this.lexemes.add(new Lexeme(Character.toString(c)));
                }
            }
        }
    }

    // identify method to return matching token for a lexeme
    public Token identify(String lexeme){
        for (Token t : tokens){
            if (t.matches(lexeme)){
                return t;
            }
        }
        return new Token("Unknown", null);
    }

    // tokenize method to set matching token for each lexeme found
    public void tokenize(){
        for (Lexeme lexeme : lexemes){
            if (identify(lexeme.getName()).getName().equals("Keyword")){
                switch (lexeme.getName()) {
                    case "lw" -> lexeme.setToken("Load");
                    case "move" -> lexeme.setToken("Move");
                    case "add" -> lexeme.setToken("Add");
                    case "sw" -> lexeme.setToken("Switch");
                    case "mult" -> lexeme.setToken("Multiply");
                    case "bne" -> lexeme.setToken("BranchNotEqual");
                    case "beq" -> lexeme.setToken("BranchEqual");
                    case "sll" -> lexeme.setToken("ShiftLeftLogical");
                    case "slt" -> lexeme.setToken("SetLessThan");
                    case "b" -> lexeme.setToken("Branch");
                }
            }
            else {
                lexeme.setToken(identify(lexeme.getName()).getName());
            }
        }
    }

    // display method to display result of lexical analysis
    public void display(){
        for (Lexeme l : lexemes){
            System.out.println("Token: " + l.getToken() + ", Lexeme: " + l.getName());
        }
    }

    // displaySymbolTable method to display symbol table produced after lexical analysis
    public void displaySymbolTable(){
        System.out.println("\nSymbol Table\n------------------");

        for (Lexeme l : lexemes){
            if (l.getToken().equals("Identifier")){
                System.out.println("-Name: " + l.getName() + ", Type: memory address");
            }
        }
    }
}
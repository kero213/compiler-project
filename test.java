package Phase2;

import Phase1.Lexer;
import Phase1.Token;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class test {
    public static void main (String[] args){
        ArrayList<Token> tokens = new ArrayList<>();

        // Identifying regular expressions for tokens
        Pattern keyword = Pattern.compile("lw|move|add|sw|mult|ori|sll|slt|beq|bne|b", Pattern.CASE_INSENSITIVE);
        Pattern identifier = Pattern.compile("([a-z]|_)([0-9]|[a-z]|_)*");
        Pattern register = Pattern.compile("[$](((t|s)[0-9])|[0-31]|zero|gp)", Pattern.CASE_INSENSITIVE);
        Pattern comma = Pattern.compile(",", Pattern.CASE_INSENSITIVE);
        Pattern leftParenthesis = Pattern.compile("[(]", Pattern.CASE_INSENSITIVE);
        Pattern rightParenthesis = Pattern.compile("[)]", Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);
        Pattern label = Pattern.compile("[A-Z]([0-9]|[a-z]|[A-Z])*");

        // Creating token objects for each token class and adding to tokens arraylist
        Token token = new Token("Keyword", keyword);
        Token token2 = new Token("Identifier", identifier);
        Token token3 = new Token("Register", register);
        Token token4 = new Token("Comma", comma);
        Token token5 = new Token("Left Parenthesis", leftParenthesis);
        Token token6 = new Token("Right Parenthesis", rightParenthesis);
        Token token7 = new Token("Number", number);
        Token token8 = new Token("Label", label);
        tokens.add(token);
        tokens.add(token2);
        tokens.add(token3);
        tokens.add(token4);
        tokens.add(token5);
        tokens.add(token6);
        tokens.add(token7);
        tokens.add(token8);

        String source = "lw $t3 # load register $v0\n" +
                "bne num1, $t5, L1 # branch to L1 if num1 not equal 0\n" +
                "add num2, num3, num4 # save sum of num2 and num3 in num4\n" +
                "b Exit # instantly branch to Exit label\n" +
                "move num2, $t3 # move num2 to t3 register\n" +
                "b Start # instantly branch to start label\n" +
                "lw $t5 # load to $t5\n";

        String source2 = "lw $t0 # Load num1 into register $t0\n" +
                " lw $t1 # Load num2 into register $t1\n" +
                " add $t2, $t0, $t1 # Add num1 and num2, store result in $t2\n" +
                " move sum, $t2 # move sum to $t2\n";

        // applying lexical analysis to source code
        Lexer lexer = new Lexer(tokens);
        lexer.findLexemes(source);
        lexer.tokenize();

        // applying syntax analysis to lexemes obtained from lexical analysis
        // produces a syntax tree if no syntax errors found, else prints error message
        Parser parser = new Parser(lexer.getLexemes());
        parser.parseCode();
        parser.getParseTree().show();
    }
}

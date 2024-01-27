package Phase1;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class testing {

    public static void main (String[] args){
        ArrayList<Token> tokens = new ArrayList<>();

        // Identifying regular expressions for tokens
        Pattern keyword = Pattern.compile("lw|mv|add|sw|mult|ori|sll|slt|beq|bne", Pattern.CASE_INSENSITIVE);
        Pattern identifier = Pattern.compile("([a-z]|_)([0-9]|[a-z]|_)*", Pattern.CASE_INSENSITIVE);
        Pattern register = Pattern.compile("[$](((t|s)[0-9])|[0-31]|zero|gp)", Pattern.CASE_INSENSITIVE);
        Pattern comma = Pattern.compile(",", Pattern.CASE_INSENSITIVE);
        Pattern leftParenthesis = Pattern.compile("[(]", Pattern.CASE_INSENSITIVE);
        Pattern rightParenthesis = Pattern.compile("[)]", Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);

        // Creating token objects for each token class and adding to tokens arraylist
        Token token = new Token("Keyword", keyword);
        Token token2 = new Token("Identifier", identifier);
        Token token3 = new Token("Register", register);
        Token token4 = new Token("Comma", comma);
        Token token5 = new Token("Left Parenthesis", leftParenthesis);
        Token token6 = new Token("Right Parenthesis", rightParenthesis);
        Token token7 = new Token("Number", number);
        tokens.add(token);
        tokens.add(token2);
        tokens.add(token3);
        tokens.add(token4);
        tokens.add(token5);
        tokens.add(token6);
        tokens.add(token7);

        // Testing lexical analysis with different source codes

        /*String source = "lw $t0, 4($gp) # fetch N\n" +
                "mult $t0, $t0, $t0 # N*N\n" +
                "lw $t1, 4($gp) # fetch N\n" +
                "ori $t2, $zero, 3 # 3\n" +
                "mult $t1, $t1, $t2 # 3*N\n" +
                "add $t2, $t0, $t1 # N*N + 3*N\n" +
                "sw $t2, 0 ($gp) # i = ...";*/

        /*String source = "lw $t0, num1 # Load num1 into register $t0\n" +
                " lw $t1, num2 # Load num2 into register $t1\n" +
                " add $t2, $t0, $t1 # Add num1 and num2, store result in $t2\n" +
                " sw $t2, sum # Store the sum in memory location 'sum'\n";*/

        String source = "lw     $t0, 0($gp)        # fetch i\n" +
                "    lw     $t1, 4($gp)        # fetch N\n" +
                "    slt    $t1, $t0, $t1      # set $t1 to 1 if $t0 < $t1, to 0 otherwise\n" +
                "    beq    $t1, $zero, skip   # branch if result of slt is 0 (i.e., !(i<N))\n" +
                "    sll    $t0, $t0, 2        # i as a byte offset\n" +
                "    add    $t0, $t0, $gp      # &A[i] - 28\n" +
                "    sw     $zero, 28($t0)     # A[i] = 0";

        Lexer lexer = new Lexer(tokens);
        lexer.findLexemes(source);
        lexer.tokenize();
        lexer.display();
        lexer.displaySymbolTable();
    }
}

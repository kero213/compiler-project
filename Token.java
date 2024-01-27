package Phase1;

import java.util.regex.*;
public class Token {

    // Attributes
    private String name;
    private Pattern regex;

    // Constructor
    public Token(String name, Pattern regex){
        this.name = name;
        this.regex = regex;
    }

    // Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pattern getRegex() {
        return regex;
    }

    public void setRegex(Pattern regex) {
        this.regex = regex;
    }

    // matches method used to check if lexeme matches regular expression of token class
    public Boolean matches(String lexeme){
        return this.getRegex().matcher(lexeme).matches();
    }
}

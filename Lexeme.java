package Phase1;

public class Lexeme {

    // Attributes
    private String name;
    private String token;

    // Constructor
    public Lexeme (String name){
        this.name = name;
    }

    // Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package Lexer;

public class Token {
    private String id;
    private String type;
    private String content;

    Token(int id,String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return "Token(" + type + ", " + content + ")";
    }
}

public class Token {
    private int id;
    private String type;
    private String content;

    Token(int id,String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public int getId() {
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

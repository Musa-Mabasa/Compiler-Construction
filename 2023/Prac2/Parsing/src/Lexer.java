import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lexer {

    private int id = 0;
    private int row = 1;
    private int col = 1;


    public List<Token> tokenise(){
        List<Token> tokens = new ArrayList<Token>();
        
        String input = "";
        File file = new File("/home/musa/Desktop/COS 341/2023/Compiler-Construction/2023/Prac2/Parsing/src/code.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (reader.hasNextLine()) {
            String input2 = reader.nextLine();
            input = input + input2 + "\n";
        }

        for(int i = 0;i < input.length();i++){
            id++;
            col++;
            if(input.charAt(i) == ' '){
                continue;
            }
            else if(input.charAt(i) == '\n'){
                row++;
                col = 1;
                continue;
            }
            else if(input.charAt(i) == ','){
                Token token = new Token(id, "Symbol", ",");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'p'){
                Token token = new Token(id, "Symbol", "p");
                tokens.add(token);
            }
            else if(input.charAt(i) == '{'){
                Token token = new Token(id, "Symbol", "}");
                tokens.add(token);
            }
            else if(input.charAt(i) == '}'){
                Token token = new Token(id, "Symbol", "}");
                tokens.add(token);
            }
            else if(Character.isDigit(input.charAt(i))){
                if(input.charAt(i) == '0'){
                    if(input.charAt(i+1) == '.'){
                        i++;
                        if(input.charAt(i+1) == '0'){
                            i++;
                            if(input.charAt(i+1) == '0'){
                                Token token = new Token(id, "Symbol", "0.00");
                                tokens.add(token);
                                continue;
                            }
                            else{
                                System.out.println("\u001B[31mError\u001B[0m: Invalid decimal, expected 0 at line " + row + " column " + col + ", recieved " + input.charAt(i+1)+ " instead.");
                                System.exit(0);
                            }
                        }
                        else{
                            System.out.println("\u001B[31mError\u001B[0m: Invalid decimal, expected 0 at line " + row + " column " + col + ", recieved " + input.charAt(i+1)+ " instead.");
                            System.exit(0);
                        }
                    }
                    else{
                        Token token = new Token(id, "Digit", Character.toString(input.charAt(i)));
                        tokens.add(token);
                    }
                }
                Token token = new Token(id, "Digit", Character.toString(input.charAt(i)));
                tokens.add(token);
            }
            else if(input.charAt(i) == ';'){
                Token token = new Token(id, "Symbol", ";");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'h'){
                Token token = new Token(id, "Symbol", "h");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'c'){
                Token token = new Token(id, "Symbol", "c");
                tokens.add(token);
            }
            else if(input.charAt(i) == ':'){
                i++;
                if(i<input.length()-1){
                    if(input.charAt(i) == '='){
                        Token token = new Token(id, "Symbol", ":=");
                        tokens.add(token);
                    }
                    else if(input.charAt(i) == '\n'){
                        i++;
                        row++;
                        col = 1;
                        if(i<input.length()-1){
                            if(input.charAt(i) == '='){
                                Token token = new Token(id, "Symbol", ":=");
                                tokens.add(token);
                            }
                            else{
                                System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                                System.exit(0);
                            }
                        }
                        else{
                            System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                            System.exit(0);
                        }
                    }
                    else{
                        System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                        System.exit(0);
                    }
                } 
                else{
                    System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                    System.exit(0);
                }
            }
            else if(input.charAt(i) == '='){
                Token token = new Token(id, "Symbol", "=");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'w'){
                Token token = new Token(id, "Symbol", "w");
                tokens.add(token);
            }
            else if(input.charAt(i) == '('){
                Token token = new Token(id, "Symbol", "(");
                tokens.add(token);
            }
            else if(input.charAt(i) == ')'){
                Token token = new Token(id, "Symbol", ")");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'i'){
                Token token = new Token(id, "Symbol", "i");
                tokens.add(token);
            }
            else if(input.charAt(i) == 't'){
                Token token = new Token(id, "Symbol", "t");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'e'){
                Token token = new Token(id, "Symbol", "e");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'n'){
                Token token = new Token(id, "Symbol", "n");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'b'){
                Token token = new Token(id, "Symbol", "b");
                tokens.add(token);
            }
            else if(input.charAt(i) == 's'){
                Token token = new Token(id, "Symbol", "s");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'a'){
                Token token = new Token(id, "Symbol", "a");
                tokens.add(token);
            }
            else if(input.charAt(i) == ','){
                Token token = new Token(id, "Symbol", ",");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'm'){
                Token token = new Token(id, "Symbol", "m");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'd'){
                Token token = new Token(id, "Symbol", "d");
                tokens.add(token);
            }
            else if(input.charAt(i) == '-'){
                Token token = new Token(id, "Symbol", "-");
                tokens.add(token);
            }
            else if(input.charAt(i) == '.'){
                Token token = new Token(id, "Symbol", ".");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'T'){
                Token token = new Token(id, "Symbol", "T");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'F'){
                Token token = new Token(id, "Symbol", "F");
                tokens.add(token);
            }
            else if(input.charAt(i) == '^'){
                Token token = new Token(id, "Symbol", "^");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'v'){
                Token token = new Token(id, "Symbol", "v");
                tokens.add(token);
            }
            else if(input.charAt(i) == '!'){
                Token token = new Token(id, "Symbol", "!");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'E'){
                Token token = new Token(id, "Symbol", "E");
                tokens.add(token);
            }
            else if(input.charAt(i) == '<'){
                Token token = new Token(id, "Symbol", "<");
                tokens.add(token);
            }
            else if(input.charAt(i) == '>'){
                Token token = new Token(id, "Symbol", ">");
                tokens.add(token);
            }
            else if(input.charAt(i) == '\"' || input.charAt(i) == '*' ){
                String shortString = checkStringOrComment(input, i);
                if(input.charAt(i) == '\"'){
                    Token token = new Token(id, "String", shortString);
                    tokens.add(token);
                }
                else{
                    Token token = new Token(id, "Comment", shortString);
                    tokens.add(token);
                }
                
                i+=16;
            }
            else if(input.charAt(i) == 'g'){
                Token token = new Token(id, "Symbol", "g");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'o'){
                Token token = new Token(id, "Symbol", "o");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'r'){
                Token token = new Token(id, "Symbol", "r");
                tokens.add(token);
            }
            else{
                System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                System.exit(0);
            }
            
        }

        for(Token token: tokens){
            System.out.println("ID: " +token.getId());
            System.out.println("content: " +token.getContent());
        }
        
        return tokens;
    }

    private String checkStringOrComment(String input, int i){
        int count = 0;
        String retString = "";
        boolean isString=  true;

        if(input.charAt(i) == '*'){
            isString = false;
        }
        i++;
        while(input.charAt(i) != '\"' && input.charAt(i) != '*' ){
            System.out.println(input.charAt(i));
            if(input.charAt(i) == '\n'){
                System.out.println("ins "+input.charAt(i));
                System.out.println("\u001B[31mError\u001B[0m: Invalid string at line " + row + " column " + col );
                System.exit(0);
            }
            retString+=input.charAt(i);
            count++;
            i++;
        }
        System.out.println("count "+count);
        if(count == 15){
            return retString;
        }
        else{
            if(isString){
                System.out.println("\u001B[31mError\u001B[0m: Invalid string at line " + row + " column " + col + ", string should be 15 characters");
                System.exit(0);
            }
            else{
                System.out.println("\u001B[31mError\u001B[0m: Invalid comment at line " + row + " column " + col + ", comment should be 15 characters");
                System.exit(0);
            }
            
            return "";
        }
    }   
}

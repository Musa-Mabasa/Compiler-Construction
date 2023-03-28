package Lexer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lexer {

    private int id = 0;


    public List<Token> tokenise(){
        List<Token> tokens = new ArrayList<Token>();
        int row = 1;
        int col = 0;
        String input = "";
        File file = new File("code.txt");
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
                col = 0;
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
                Token token = new Token(id, "Symbol", ":");
                tokens.add(token);
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
            else if(input.charAt(i) == 'm'){
                Token token = new Token(id, "Symbol", "m");
                tokens.add(token);
            }
            else if(input.charAt(i) == 'd'){
                Token token = new Token(id, "Symbol", "d");
                tokens.add(token);
            }
            
        }
        
        return tokens;
    }
}

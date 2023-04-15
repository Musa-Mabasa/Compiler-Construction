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
                Token token = new Token(id, "Symbol", ",", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'p'){
                Token token = new Token(id, "Symbol", "p", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '{'){
                Token token = new Token(id, "Symbol", "{", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '}'){
                Token token = new Token(id, "Symbol", "}", row, col);
                tokens.add(token);
            }
            else if(Character.isDigit(input.charAt(i))){
                if(input.charAt(i) == '0'){
                    System.out.println("hereeeee");
                    i++;
                    while( i < input.length() && (input.charAt(i) == ' ' || input.charAt(i) == '\n')){
                        if(input.charAt(i) == '\n'){
                            row++;
                            col = 1;
                        }
                        else{
                            col++;
                        }
                        i++;
                    }
                    if(i == input.length()){
                        Token token = new Token(id, "Number", "0", row, col);
                        tokens.add(token);
                        continue;
                    }
                    else{
                        if(input.charAt(i) == '.'){
                            i++;
                            while( i < input.length() && input.charAt(i) != '0'){
                                if(input.charAt(i) != '\n' && input.charAt(i) != ' '){
                                    System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '0' token" );
                                    System.exit(0);
                                }
                                if(input.charAt(i) == '\n'){
                                    row++;
                                    col = 1;
                                }
                                else{
                                    col++;
                                }
                                i++;
                            }
                            if(i == input.length()){
                                System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '0' token" );
                                System.exit(0);
                            }
                            if(input.charAt(i) == '0'){
                                i++;
                                while( i < input.length() && input.charAt(i) != '0'){
                                    if(input.charAt(i) != '\n' && input.charAt(i) != ' '){
                                        System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '0' token" );
                                        System.exit(0);
                                    }
                                    if(input.charAt(i) == '\n'){
                                        row++;
                                        col = 1;
                                    }
                                    else{
                                        col++;
                                    }
                                    i++;
                                }
                                if(i == input.length()){
                                    System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '0' token" );
                                    System.exit(0);
                                }
                                else{
                                    if(input.charAt(i) == '0'){
                                        Token token = new Token(id, "Symbol", "0.00", row, col);
                                        tokens.add(token);
                                    }
                                    else{
                                        System.out.println("\u001B[31mError\u001B[0m: Invalid decimal, expected 0 at line " + row + " column " + col + ", recieved " + input.charAt(i+1)+ " instead.");
                                        System.exit(0);
                                    }
                                }
                                
                            }
                            else{
                                System.out.println("\u001B[31mError\u001B[0m: Invalid decimal, expected 0 at line " + row + " column " + col + ", recieved " + input.charAt(i+1)+ " instead.");
                                System.exit(0);
                            }
                        }
                        else{

                            Token token = new Token(id, "Digit", "0" , row, col);
                            tokens.add(token);
                            i--;
                            continue;
                        }
                    }
                    
                }
                else{
                    Token token = new Token(id, "Digit", Character.toString(input.charAt(i)), row, col);
                    tokens.add(token);
                }
            }
            else if(input.charAt(i) == ';'){
                Token token = new Token(id, "Symbol", ";", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'h'){
                Token token = new Token(id, "Symbol", "h",  row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'c'){
                Token token = new Token(id, "Symbol", "c", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == ':'){
                i++;
                while( i < input.length() && input.charAt(i) != '='){
                    if(input.charAt(i) != '\n' && input.charAt(i) != ' '){
                        System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '=' token" );
                        System.exit(0);
                    }
                    if(input.charAt(i) == '\n'){
                        row++;
                        col = 1;
                    }
                    else{
                        col++;
                    }
                    i++;
                }
                if(i == input.length()){
                    System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", Expected '=' token" );
                    System.exit(0);
                }
                else{
                    if(input.charAt(i) == '='){
                        Token token = new Token(id, "Symbol", ":=", row, col);
                        tokens.add(token);
                    }
                    else{
                        System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +"." );
                    }
                       
                }
            }
            else if(input.charAt(i) == 'w'){
                Token token = new Token(id, "Symbol", "w", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '('){
                Token token = new Token(id, "Symbol", "(", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == ')'){
                Token token = new Token(id, "Symbol", ")", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'i'){
                Token token = new Token(id, "Symbol", "i", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 't'){
                Token token = new Token(id, "Symbol", "t", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'e'){
                Token token = new Token(id, "Symbol", "e", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'n'){
                Token token = new Token(id, "Symbol", "n", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'b'){
                Token token = new Token(id, "Symbol", "b", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 's'){
                Token token = new Token(id, "Symbol", "s", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'a'){
                Token token = new Token(id, "Symbol", "a", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == ','){
                Token token = new Token(id, "Symbol", ",", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'm'){
                Token token = new Token(id, "Symbol", "m", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'd'){
                Token token = new Token(id, "Symbol", "d", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '-'){
                Token token = new Token(id, "Symbol", "-", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '.'){
                Token token = new Token(id, "Symbol", ".", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'T'){
                Token token = new Token(id, "Symbol", "T", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'F'){
                Token token = new Token(id, "Symbol", "F", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '^'){
                Token token = new Token(id, "Symbol", "^", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'v'){
                Token token = new Token(id, "Symbol", "v", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '!'){
                Token token = new Token(id, "Symbol", "!", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'E'){
                Token token = new Token(id, "Symbol", "E", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '<'){
                Token token = new Token(id, "Symbol", "<", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '>'){
                Token token = new Token(id, "Symbol", ">", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == '\"' || input.charAt(i) == '*' ){
                String shortString = checkStringOrComment(input, i);
                if(input.charAt(i) == '\"'){
                    Token token = new Token(id, "String", shortString, row, col);
                    tokens.add(token);
                }
                else{
                    Token token = new Token(id, "Comment", shortString, row, col);
                    tokens.add(token);
                }
                
                i+=16;
            }
            else if(input.charAt(i) == 'g'){
                Token token = new Token(id, "Symbol", "g", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'o'){
                Token token = new Token(id, "Symbol", "o", row, col);
                tokens.add(token);
            }
            else if(input.charAt(i) == 'r'){
                Token token = new Token(id, "Symbol", "r", row, col);
                tokens.add(token);
            }
            else{
                System.out.println("\u001B[31mError\u001B[0m: Invalid symbol at line " + row + " column " + col +", token: "+ input.charAt(i)  );
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

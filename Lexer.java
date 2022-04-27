import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class Lexer{
    public Lexer()
    {

    }

    public void create()
    {
        LinkedList<Token> tokens = new LinkedList<Token>();
        try{
            int id = 1;
            File infile = new File("input.txt");
            int linenum = 0;
            try (Scanner read = new Scanner(infile)) {
                while(read.hasNextLine())
                {
                    linenum++;
                    String data = read.nextLine();

                    for(int i=0;i<data.length();i++)
                    {
                        

                        if(data.charAt(i)=='\"')
                        {
                            String t = "";
                            if(checkForShortString(data,i))
                            {
                                i++;
                                while(data.charAt(i)!='\"')
                                {
                                    t+=data.charAt(i);
                                    i++;
                                }
                            }
                            else{
                                System.out.println("line: "+linenum+", Error: Expected shortstring");
                                i++;
                                while(i < data.length() && data.charAt(i)!='\"')
                                {
                                    i++;
                                }
                                continue;
                                
                            }

                            Token tok = new Token();
                            tok.type = "String";
                            tok.content = t;
                            tok.ID = id; 
                            tokens.add(tok);
                            id++;

                        }
                        else if(Character.isDigit(data.charAt(i)))
                        {
                            if(data.charAt(i)=='0')
                            {
                                System.out.println("line: "+linenum+", Error: invalid number");

                                while(i<data.length() && Character.isDigit(data.charAt(i+1)))
                                {
                                    i++;
                                }
                                continue;
                            }
                            String strNum ="";
                            strNum += data.charAt(i);
                            int next = i+1;
                            while(next < data.length() && Character.isDigit(data.charAt(next)))
                            {
                                if(Character.isDigit(data.charAt(next)))
                                {
                                    strNum += data.charAt(next);
                                }
                                i++;
                                next++;
                            }
                            Token tok = new Token();
                            tok.type = "number";
                            tok.content = strNum;
                            tok.ID = id; 
                            tokens.add(tok);
                            id++;

                        }
                        else if(Character.isLetter(data.charAt(i)))
                        {
                            String word = captureWord(data,i);

                            i = i + word.length()-1;

                            String [] keywords = {"main","halt","proc","return","if","then","else","do","until","while","do","output","call","true","false"};
                            String [] moreKeywords = {"input","not","and","or","eq","larger","add","sub","mult","arr","num","bool","string"};

                            boolean search = searchKeywords(word,keywords,moreKeywords);

                            if(search == true)
                            {
                                Token tok = new Token();
                                tok.type = "keyword";
                                tok.content = word;
                                tok.ID = id; 
                                tokens.add(tok);
                                id++;
                            }
                            else
                            {
                                Token tok = new Token();
                                tok.type = "userDefinedName";
                                tok.content = word;
                                tok.ID = id; 
                                tokens.add(tok);
                                id++;
                            }
                        }
                        else if(data.charAt(i) =='{' || data.charAt(i) ==';' || data.charAt(i) =='}' || data.charAt(i) ==',' || data.charAt(i) =='(' || data.charAt(i) ==')' || data.charAt(i) =='[' || data.charAt(i) ==']' || data.charAt(i) ==':')
                        {
                            if(data.charAt(i) == ':')
                            {
                                if(data.charAt(i+1)== '=')
                                {
                                    String a ="";
                                    a+=data.charAt(i);
                                    a+=data.charAt(i+1);
                                    i++;

                                    Token tok = new Token();
                                    tok.type = "symbols";
                                    tok.content = a;
                                    tok.ID = id; 
                                    tokens.add(tok);
                                    id++;
                                }
                                else
                                {
                                    System.out.println("line: "+linenum+", Error: Expected '=' after ':' ");
                                }
                            }
                            else
                            {
                                String sym ="";
                                sym+=data.charAt(i);
                                Token tok = new Token();
                                tok.type = "symbols";
                                tok.content = sym;
                                tok.ID = id; 
                                tokens.add(tok);
                                id++;
                            }
                            
                        }
                        else if(Character.isSpaceChar(data.charAt(i)))
                        {

                        }
                        else
                        {
                            System.out.println("line: "+linenum+", Error: Unknown symbol");
                        }
                    }

                    
                }
                read.close();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }

        // for(Token toke : tokens)
        // {
        //     System.out.print("Id: "+toke.ID +" | ");
        //     System.out.print("Content: "+toke.content +" | ");
        //     System.out.println(" type: "+toke.type);
        // }
        
    }

    private Boolean checkForShortString(String data, int index)
    {
        int i = 0;
        index++;
        while(index < data.length() && data.charAt(index)!='\"')
        {
            if(Character.isUpperCase(data.charAt(index)) || Character.isDigit(data.charAt(index)) || Character.isSpaceChar(data.charAt(index)))
            {
                index++;
                i++;
            }
            else
            {
                return false;
            }
            
        }
        if(index == data.length()){
            return false;
        }
        if(i>15)
        {
            return false;
        }
        else return true;
    }
    

    private String captureWord(String data, int i)
    {
        String w ="";
        while( i<data.length() && (Character.isLetter(data.charAt(i)) || Character.isDigit(data.charAt(i))))
        {
            w += data.charAt(i);
            i++;
        }

        return w;
    }

    private Boolean searchKeywords(String word,String[] keywords, String[] moreKeywords)
    {
        for(String keys : keywords)
        {
            if(keys.equals(word))
            {
                return true;
            }
        }

        for(String keys : moreKeywords)
        {
            if(keys.equals(word))
            {
                return true;
            }
        }

        return false;

    }
}
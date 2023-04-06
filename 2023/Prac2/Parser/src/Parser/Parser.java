import java.util.*;

import Lexer.Lexer;
import Lexer.Token;

class Parser{
    private List<Token> tokens;
    private int index = 0;
    private int id = 1;

    public Parser(){
        Lexer lex = new Lexer();
        tokens = lex.tokenise(); 
    }

    public void parse(){
        parseProgr();
    }

    private Boolean parseProgr(){
        Node startNode = new Node(id++, "Non-Terminal", "PROGR");
        Boolean algo = parseALGO(startNode);
        if(algo){
            Boolean pd = parsePROCDEFS(startNode);
            if(pd){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            return false;
            // parser failed
        }
    }

    private Boolean parseALGO(Node parent){

        Node algoNode = new Node(id++, "Non-Terminal", "ALGO");
        parent.children.add(algoNode);

        Boolean instr = parseINSTR(algoNode);
        if(instr){
            Boolean comm = parseCOMMENT(algoNode);
            if(comm){
                Boolean seq = parseSEQ(algoNode);
                if(seq){
                    // parser passed
                    return true;
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            return false;
            // parser failed
        }
    }

    private Boolean parseINSTR(Node parent){
        Node instrNode = new Node(id++, "Non-Terminal", "INSTR");
        parent.children.add(instrNode);
        if(tokens.get(index).getContent() == "g"){
            Boolean input = parseINPUT(instrNode);
            if(input){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "o"){
            Boolean output = parseOUTPUT(instrNode);
            if(output){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "r"){
            Boolean output = parseOUTPUT(instrNode);
            if(output){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "n"){
            Boolean assign = parseASSIGN(instrNode);
            if(assign){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "b"){
            Boolean assign = parseASSIGN(instrNode);
            if(assign){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "s"){
            Boolean assign = parseASSIGN(instrNode);
            if(assign){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "c"){
            Boolean call = parseCALL(instrNode);
            if(call){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "w"){
            Boolean loop = parseLOOP(instrNode);
            if(loop){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "i"){
            Boolean branch = parseBRANCH(instrNode);
            if(branch){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "h"){
            return true;
        }
        else{
            // parser failed
            return false;

        }
    }

    private Boolean parseINPUT(Node parent){
        Node inputNode = new Node(id++, "Non-Terminal", "INPUT");
        parent.children.add(inputNode);
        if(tokens.get(index).getContent() == "g"){
            Node gNode = new Node(id++, "Terminal", "g");
            inputNode.children.add(gNode);
            index++;
            Boolean numvar = parseNUMVAR(inputNode);
            if(numvar){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            // parser failed
            return false;
        }
        
    }

    private Boolean parseOUTPUT(Node parent){
        Node outputNode = new Node(id++, "Non-Terminal", "OUTPUT");
        parent.children.add(outputNode);
        if(tokens.get(index).getContent() == "r"){
            Boolean text = parseTEXT(outputNode);
            if(text){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "o"){
            Boolean value = parseVALUE(outputNode);
            if(value){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            // parser failed
            return false;
        }
        
    }

    private Boolean parseASSIGN(Node parent){
        Node assignNode = new Node(id++, "Non-Terminal", "ASSIGN");
        parent.children.add(assignNode);
        if(tokens.get(index).getContent() == "n"){
            Boolean numvar = parseNUMVAR(assignNode);
            if(numvar){
                index++;
                if(tokens.get(index).getContent() == ":="){
                    Node equalNode = new Node(id++, "Terminal", ":=");  
                    assignNode.children.add(equalNode);
                    index++;
                    Boolean numexpr = parseNUMEXPR(assignNode);
                    if(numexpr){
                        // parser passed
                        return true;
                    }
                    else{
                        // parser failed
                        return false;
                    }
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "b"){
            Boolean boolvar = parseBOOLVAR(assignNode);
            if(boolvar){
                index++;
                if(tokens.get(index).getContent() == ":="){
                    Node equalNode = new Node(id++, "Terminal", ":=");  
                    assignNode.children.add(equalNode);
                    index++;
                    Boolean boolexpr = parseBOOLEXPR(assignNode);
                    if(boolexpr){
                        // parser passed
                        return true;
                    }
                    else{
                        // parser failed
                        return false;
                    }
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "s"){
            Boolean strvar = parseSTRINGV(assignNode);
            if(strvar){
                index++;
                if(tokens.get(index).getContent() == ":="){
                    Node equalNode = new Node(id++, "Terminal", ":=");  
                    assignNode.children.add(equalNode);
                    index++;
                    Boolean strval = parseSTRI(assignNode);
                    if(strval){
                        // parser passed
                        return true;
                    }
                    else{
                        // parser failed
                        return false;
                    }
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            // parser failed
            return false;
        }
    }

    private Boolean parseCALL(Node parent){
        Node callNode = new Node(id++, "Non-Terminal", "CALL");
        parent.children.add(callNode);
        if(tokens.get(index).getContent() == "c"){
            Node cNode = new Node(id++, "Terminal", "c");
            callNode.children.add(cNode);
            index++;
            if(tokens.get(index).getContent() == "p"){
                Node pNode = new Node(id++, "Terminal", "p");
                callNode.children.add(pNode);
                index++;
                Boolean digits = parseDIGITS(callNode);
                if(digits){
                    // parser passed
                    return true;
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            // parser failed
            return false;
        }
    }

    private Boolean parseLOOP(Node parent){
        Node loopNode = new Node(id++, "Non-Terminal", "LOOP");
        parent.children.add(loopNode);
        if(tokens.get(index).getContent() == "w"){
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal", "(");
                loopNode.children.add(openNode);
                index++;
                Boolean boolexpr = parseBOOLEXPR(loopNode);
                if(boolexpr){
                    index++;
                    if(tokens.get(index).getContent() == ")"){
                        Node closeNode = new Node(id++, "Terminal", ")");
                        loopNode.children.add(closeNode);
                        index++;
                        if(tokens.get(index).getContent() == "{"){
                            Node openCurlNode = new Node(id++, "Terminal", "{");
                            loopNode.children.add(openCurlNode);
                            index++;
                            Boolean algo = parseALGO(loopNode);
                            if(algo){
                                index++;
                                if(tokens.get(index).getContent() == "}"){
                                    Node closeCurlNode = new Node(id++, "Terminal", "}");
                                    loopNode.children.add(closeCurlNode);
                                    // parser passed
                                    return true;
                                }
                                else{
                                    // parser failed
                                    return false;
                                }
                            }
                            else{
                                // parser failed
                                return false;
                            }
                        }
                        else{
                            // parser failed
                            return false;
                        }
                        
                    }
                    else{
                        // parser failed
                        return false;
                    }
                }
                else{
                    // parser failed
                    return false;
                }
            }
            else{
                // parser failed
                return false;
            }
        }
        else{
            // parser failed
            return false;
        }
    }
        
}

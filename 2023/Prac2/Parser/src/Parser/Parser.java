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
        index++;
        if(instr){
            Boolean comm = parseCOMMENT(algoNode);
            index++;
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
            Node wNode = new Node(id++, "Terminal", "w");
            loopNode.children.add(wNode);
            index++;
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

    private Boolean parseBRANCH(Node parent){
        Node branchNode = new Node(id++, "Non-Terminal", "BRANCH");
        parent.children.add(branchNode);

        if(tokens.get(index).getContent() == "i"){
            Node iNode = new Node(id++, "Terminal", "i");
            branchNode.children.add(iNode);
            index++;
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal","(");
                branchNode.children.add(openNode);
                index++;
                Boolean boolexpr = parseBOOLEXPR(branchNode);
                if(boolexpr){
                    index++;
                    if(tokens.get(index).getContent() == ")"){
                        Node closeNode = new Node(id++, "Terminal", ")");
                        branchNode.children.add(closeNode);
                        index++;
                        if(tokens.get(index).getContent() == "t"){
                            Node tNode = new Node(id++, "Terminal", "t");
                            branchNode.children.add(tNode);
                            index++;
                            if(tokens.get(index).getContent() == "{"){
                                Node openCurlNode = new Node(id++, "Terminal", "t");
                                branchNode.children.add(openCurlNode);
                                index++;
                                Boolean algo = parseALGO(branchNode);
                                if(algo){
                                    index++;
                                    if(tokens.get(index).getContent() == "}"){
                                        Node closeCurlNode = new Node(id++, "Terminal", "}");
                                        branchNode.children.add(closeCurlNode);
                                        index++;
                                        Boolean BElse = parseELSE(branchNode);
                                        if(BElse){
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
    }

    private Boolean parseCOMMENT(Node parent){
        Node commentNode = new Node(id, "Non-Terminal", "COMMENT");
        parent.children.add(commentNode);
        if(tokens.get(index).getType() == "Comment"){
            return true;
        }
        else{
            return false;
        }
    }

    private Boolean parseSEQ(Node parent){
        Node seqNode = new Node(id++, "Non-Terminal", "SEQ");
        parent.children.add(seqNode);

        if(tokens.get(index).getContent() == ";"){
            Node semiNode = new Node(id++, "Terminal", ";");
            seqNode.children.add(semiNode);
            index++;
            Boolean algo = parseALGO(seqNode);
            if(algo){
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

    private Boolean parseNUMVAR(Node parent){
        Node numvarNode = new Node(id++, "Non-Terminal", "NUMVAR");
        parent.children.add(numvarNode);

        if(tokens.get(index).getContent() == "n"){
            Node nNode = new Node(id++, "Terminal", "n");
            numvarNode.children.add(nNode);
            index++;
            Boolean digits = parseDIGITS(numvarNode);
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

    private Boolean parseBOOLVAR(Node parent){
        Node boolvarNode = new Node(id++, "Non-Terminal", "BOOLVAR");
        parent.children.add(boolvarNode);

        if(tokens.get(index).getContent() == "b"){
            Node bNode = new Node(id++, "Terminal", "b");
            boolvarNode.children.add(bNode);
            index++;
            Boolean digits = parseDIGITS(boolvarNode);
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

    private Boolean parseTEXT(Node parent){
        Node textNode = new Node(id++, "Non-Terminal", "TEXT");
        parent.children.add(textNode);

        if(tokens.get(index).getContent() == "r"){
            Node rNode = new Node(id++, "Terminal", "r");
            textNode.children.add(rNode);
            index++;
            Boolean stringv = parseSTRINGV(textNode);
            if(stringv){
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

    private Boolean parseVALUE(Node parent){
        Node valueNode = new Node(id++, "Non-Terminal", "VALUE");
        parent.children.add(valueNode);

        if(tokens.get(index).getContent() == "o"){
            Node oNode = new Node(id++, "Terminal", "o");
            valueNode.children.add(oNode);
            index++;
            Boolean numvar = parseNUMVAR(valueNode);
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

    private Boolean parseNUMEXPR(Node parent){
        Node numexprNode = new Node(id++, "Non-Terminal", "NUMEXPR");
        parent.children.add(numexprNode);

        if(tokens.get(index).getContent() == "a" || tokens.get(index).getContent() == "m" || tokens.get(index).getContent() == "d"){
            Node node = new Node(id++, "Terminal", tokens.get(index).getContent() );
            numexprNode.children.add(node);
            index++;
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal", "(");
                numexprNode.children.add(openNode);
                index++;
                Boolean numexpr = parseNUMEXPR(numexprNode);
                if(numexpr){
                    index++;
                    if(tokens.get(index).getContent() == ","){
                        Node commaNode = new Node(id++, "Terminal", ",");
                        numexprNode.children.add(commaNode);
                        index++;
                        Boolean numexpr2 = parseNUMEXPR(numexprNode);
                        if(numexpr2){
                            index++;
                            if(tokens.get(index).getContent() == ")"){
                                Node closeNode = new Node(id++, "Terminal", ")");
                                numexprNode.children.add(closeNode);
                                index++;
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
        else if(tokens.get(index).getContent() == "n"){
            Boolean numvar = parseNUMVAR(numexprNode);
            if(numvar){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "0.00" || tokens.get(index).getContent() == "-" || (isDigit(tokens.get(index).getContent()) && tokens.get(index).getContent() != "0")){
            Boolean decnum = parseDECNUM(numexprNode);
            if(decnum){
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

    private Boolean parseBOOLEXPR(Node parent){
        // TODO: implement this method APR 8
    }

    private Boolean isDigit(String str){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) < '0' || str.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }
    
}

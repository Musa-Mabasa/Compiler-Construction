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

    private Boolean parseCOMMENT(Node parent){
        Node commentNode = new Node(id, "Non-Terminal", "COMMENT");
        parent.children.add(commentNode);
        if(tokens.get(index).getType() == "Comment"){
            return true;
        }
        else if(tokens.get(index).getContent() == ";" || tokens.get(index).getContent() == "}" || tokens.get(index).getContent() == ","){
            return true;
        }
        else{
            return false;
        }
    }

    private Boolean parseSTRI(Node parent){
        Node striNode = new Node(id, "Non-Terminal", "STRI");
        parent.children.add(striNode);
        if(tokens.get(index).getType() == "String"){
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

    private Boolean parseSTRINGV(Node parent){
        Node stringvNode = new Node(id++, "Non-Terminal", "STRINGV");
        parent.children.add(stringvNode);

        if(tokens.get(index).getContent() == "s"){
            Node sNode = new Node(id++, "Terminal", "s");
            stringvNode.children.add(sNode);
            index++;
            Boolean digits = parseDIGITS(stringvNode);
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
        Node boolexprNode = new Node(id++, "Non-Terminal", "BOOLEXPR");
        parent.children.add(boolexprNode);

        if(tokens.get(index).getContent() == "b" || tokens.get(index).getContent() == "T" || tokens.get(index).getContent() == "F" || tokens.get(index).getContent() == "^" || tokens.get(index).getContent() == "v" || tokens.get(index).getContent() == "!" ){
            Boolean logic = parseLOGIC(boolexprNode);
            if(logic){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "E" || tokens.get(index).getContent() == "<" || tokens.get(index).getContent() == ">"){
            Boolean cmpr = parseCMPR(boolexprNode);
            if(cmpr){
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

    private Boolean parseDIGITS(Node parent){
        Node digitsNode = new Node(id++, "Non-Terminal", "DIGITS");
        parent.children.add(digitsNode);

        if(isDigit(tokens.get(index).getContent())){
            Boolean d = parseD(digitsNode);
            if(d){
                index++;
                if(isDigit(tokens.get(index).getContent())){
                   Boolean more = parseMORE(digitsNode);
                    if(more){
                        // parser passed
                        return true;
                    }
                    else{
                        // parser failed
                        return false;
                    }
                }
                else if(tokens.get(index).getContent() == "." ||tokens.get(index).getContent() == ")" || tokens.get(index).getContent() == "," || tokens.get(index).getContent() == "*" || tokens.get(index).getContent() == "}"){
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
            // parser passed
            return true;
        }
    }

    private Boolean parseELSE(Node parent){
        Node elseNode = new Node(id++, "Non-Terminal", "ELSE");
        parent.children.add(elseNode);

        if(tokens.get(index).getContent() == "e"){
            Node eNode = new Node(id++, "Terminal", "e");
            elseNode.children.add(eNode);
            index++;
            if(tokens.get(index).getContent() == "{"){
                Node openNode = new Node(id++, "Terminal", "{");
                elseNode.children.add(openNode);
                index++;
                Boolean algo = parseALGO(elseNode);
                if(algo){
                    index++;
                    if(tokens.get(index).getContent() == "}"){
                        Node closeNode = new Node(id++, "Terminal", "}");
                        elseNode.children.add(closeNode);
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
        else if(tokens.get(index).getContent() == "*" || tokens.get(index).getContent() == "}" || tokens.get(index).getContent() == "," || tokens.get(index).getContent() == ";" ){
            // parser passed
            return true;
        }
        else{
            // parser failed
            return false;
        }
    }

    private Boolean parseDECNUM(Node parent){
        Node decnumNode = new Node(id++, "Non-Terminal", "DECNUM");
        parent.children.add(decnumNode);

        if(tokens.get(index).getContent() == "0.00"){
            Node zeroNode = new Node(id++, "Terminal", "0.00");
            decnumNode.children.add(zeroNode);
            // parser passed
            return true;
        }
        else if(tokens.get(index).getContent() == "-"){
           Boolean neg = parseNEG(decnumNode);
            if(neg){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(isDigit(tokens.get(index).getContent()) && tokens.get(index).getContent() != "0"){
            Boolean pos = parsePOS(decnumNode);
            if(pos){
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

    private Boolean parseLOGIC(Node parent){
        Node logicNode = new Node(id++, "Non-Terminal", "LOGIC");
        parent.children.add(logicNode);

        if(tokens.get(index).getContent() == "b"){
            Boolean boolvar = parseBOOLVAR(logicNode);
            if(boolvar){
                // parser passed
                return true;
            }
            else{
                // parser failed
                return false;
            }
        }
        else if(tokens.get(index).getContent() == "T" || tokens.get(index).getContent() == "F"){
            Node boolNode = new Node(id++, "Terminal", tokens.get(index).getContent());
            logicNode.children.add(boolNode);

            // parser passed
            return true;
        }
        else if(tokens.get(index).getContent() == "^" || tokens.get(index).getContent() == "v"){
            Node node = new Node(id++, "Terminal", tokens.get(index).getContent() );
            logicNode.children.add(node);
            index++;
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal", "(");
                logicNode.children.add(openNode);
                index++;
                Boolean numexpr = parseBOOLEXPR(logicNode);
                if(numexpr){
                    index++;
                    if(tokens.get(index).getContent() == ","){
                        Node commaNode = new Node(id++, "Terminal", ",");
                        logicNode.children.add(commaNode);
                        index++;
                        Boolean numexpr2 = parseBOOLEXPR(logicNode);
                        if(numexpr2){
                            index++;
                            if(tokens.get(index).getContent() == ")"){
                                Node closeNode = new Node(id++, "Terminal", ")");
                                logicNode.children.add(closeNode);
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
        else if(tokens.get(index).getContent() == "!"){
            Node exNode = new Node(id++, "Terminal", "!");
            logicNode.children.add(exNode);
            index++;
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal", "(");
                logicNode.children.add(openNode);
                index++;
                Boolean numexpr = parseBOOLEXPR(logicNode);
                if(numexpr){
                    index++;
                    if(tokens.get(index).getContent() == ")"){
                        Node closeNode = new Node(id++, "Terminal", ")");
                        logicNode.children.add(closeNode);
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

    private Boolean parseCMPR(Node parent){
        Node cmprNode = new Node(id++, "Non-Terminal", "CMPR");
        parent.children.add(cmprNode);

        if(tokens.get(index).getContent() == "E" || tokens.get(index).getContent() == "<" || tokens.get(index).getContent() == ">"){
            Node node = new Node(id++, "Terminal", tokens.get(index).getContent() );
            cmprNode.children.add(node);
            index++;
            if(tokens.get(index).getContent() == "("){
                Node openNode = new Node(id++, "Terminal", "(");
                cmprNode.children.add(openNode);
                index++;
                Boolean numexpr = parseNUMEXPR(cmprNode);
                if(numexpr){
                    index++;
                    if(tokens.get(index).getContent() == ","){
                        Node commaNode = new Node(id++, "Terminal", ",");
                        cmprNode.children.add(commaNode);
                        index++;
                        Boolean numexpr2 = parseNUMEXPR(cmprNode);
                        if(numexpr2){
                            index++;
                            if(tokens.get(index).getContent() == ")"){
                                Node closeNode = new Node(id++, "Terminal", ")");
                                cmprNode.children.add(closeNode);
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
        else{
            // parser failed
            return false;
        }
    }

    private Boolean parseD(Node parent){
        Node dNode = new Node(id++, "Non-Terminal", "D");
        parent.children.add(dNode);

        if(isDigit(tokens.get(index).getContent())){
            Node node = new Node(id++, "Terminal", tokens.get(index).getContent());
            dNode.children.add(node);
            // parser passed
            return true; 
        }
        else{
            // parser failed
            return false;
        }
    }

    private Boolean parseMORE(Node parent){
        // TODO - check if this is correct
        Node moreNode = new Node(id++, "Non-Terminal", "MORE");
        parent.children.add(moreNode);

        if(isDigit(tokens.get(index).getContent())){
            Boolean digits = parseDIGITS(moreNode);
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
            // parser passed
            return true;
        }
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

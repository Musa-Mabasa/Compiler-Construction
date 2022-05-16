import java.util.LinkedList;
public class Parser{

    private Lexer lexer = new Lexer();
    private LinkedList<Token> lex = lexer.create();
    private LinkedList<Token> storer;
    private int i =0;
    private int id = 1;

    public void parse()
    {
        if(lex.size()<5)
        {
            //error, Parser stumbles
        }
        else
        {
            Node SPL = new Node();
            SPL.nodeType = "Non-Terminal";
            SPL.id =id++;

            if(lex.get(i).content.equals("main"))
            {
                System.out.println("main");
                Node node = new Node();
                node.id=id++;
                node.nodeType = "Terminal";
                node.tokenID = lex.get(i);
                // SPL.children.add(node);

                // nodes.add(node);

                i++;
                if(lex.get(i).content.equals("{"))
                {
                    System.out.println("{");
                    Node node1 = new Node();
                    node1.id=id++;
                    node1.nodeType = "Terminal";
                    node.tokenID = lex.get(i);
                    // SPL.children.add(node1);

                    // nodes.add(node1);

                    i++;
                    Boolean Alg = checkAlgorithm();
                    System.out.println("Alg "+Alg);
                    if(Alg)
                    {
                        System.out.println("Algorithm");
                        Node Algo = new Node();
                        Algo.id = id++;
                        Algo.nodeType = "Non-Terminal";
                        // SPL.children.add(Algo);

                        i++;
                        if(lex.get(i).content.equals("Halt"))
                        {
                            System.out.println("Halt");
                            Node node2 = new Node();
                            node2.id=id++;
                            node2.nodeType = "Terminal";
                            node2.tokenID=lex.get(i);
                            // SPL.children.add(node2);

                            // nodes.add(node2);
                            i++;
                            if(lex.get(i).content.equals(";"))
                            {
                                System.out.println(";");
                                Node node3 = new Node();
                                node3.id=id++;
                                node3.nodeType = "Terminal";
                                node3.tokenID=lex.get(i);
                                // SPL.children.add(node3);

                                // nodes.add(node2);

                                i++;
                                Boolean varDecl = checkVarDecl();
                                if(varDecl)
                                {
                                    Node Var = new Node();
                                    Var.id = id++;
                                    Var.nodeType = "Non-Terminal";
                                    // SPL.children.add(Var);
                                    i++;
                                    if(lex.get(i).content.equals("}"))
                                    {
                                        System.out.println("}");
                                        // Parser Correct
                                        Node node4 = new Node();
                                        node4.id=id++;
                                        node4.nodeType = "Terminal";
                                        node4.tokenID=lex.get(i);
                                        // SPL.children.add(node4);

                                        // nodes.add(node2);
                                    }
                                    else
                                    {
                                        //error, Parser stumbles
                                        System.out.println("Parse error!!");
                                    }
                                }
                                else
                                {
                                    //error, Parser stumbles
                                    System.out.println("Parse error!!");
                                }
                            }
                            else
                            {
                                //error, Parser stumbles
                                System.out.println("Parse error!!");
                            }
                        }
                        else
                        {
                            //error, Parser stumbles
                            System.out.println("Parse error!!");
                        }
                    }
                    else
                    {
                        //error, Parser stumbles
                        System.out.println("Parse error!!");
                    }
                }
                else
                {
                    //error, Parser stumbles
                    System.out.println("Parse error!!");
                }
            }
            else if(lex.get(i).content.equals("proc"))
            {
                Boolean PD = checkProcDefs();
                
            }
            else
            {
                //Error Parser stumbles
                System.out.println("Parse error!!");
            }
            
        }
        
    }

    private Boolean checkProcDefs()
    {
        i++;
        if(lex.get(i).type.equals("userDefinedName"))
        {
            if(lex.get(i).content.equals("{"))
            {
                i++;
                checkInnerProcDefs();
                i++;
                Boolean Alg = checkAlgorithm();
                if(Alg)
                {
                    i++;
                    if(lex.get(i).content.equals("return"))
                    {
                        i++;
                        if(lex.get(i).content.equals(";"))
                        {
                            Boolean varD = checkVarDecl();
                            if(varD)
                            {
                                if(lex.get(i).content.equals("}"))
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private Boolean checkInnerProcDefs()
    {
        i++;
        if(lex.get(i).type.equals("userDefinedName"))
        {
            if(lex.get(i).content.equals("{"))
            {
                i++;
                checkInnerProcDefs();
                i++;
                Boolean Alg = checkAlgorithm();
                if(Alg)
                {
                    i++;
                    if(lex.get(i).content.equals("return"))
                    {
                        i++;
                        if(lex.get(i).content.equals(";"))
                        {
                            Boolean varD = checkVarDecl();
                            if(varD)
                            {
                                if(lex.get(i).content.equals("}"))
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
            return false;
        }
        return false;
    }

    private Boolean checkAlgorithm()
    {
        if(lex.get(i).content.equals("halt") || lex.get(i).content.equals("return") || lex.get(i).content.equals("}"))
        {
            System.out.println("We are true: "+lex.get(i).content);
            return true;
        }
        System.out.println("Instr "+lex.get(i).content);
        Boolean ins = checkInstr();
        if(ins)
        {
            System.out.println("Sym "+lex.get(i).content);
            if(lex.get(i).content.equals(";"))
            {
                System.out.println(";");
                i++;
                checkAlgorithm();
            }
            else
            {
                //error parser stumbles
                return false;
            }

        }
            //error, Parser stumbles
        return false;

    }

    private Boolean checkInstr()
    {
        System.out.println("In Instr: "+lex.get(i).content);
        Boolean ass = checkAssign();
        Boolean bra = checkBranch();
        Boolean loo = checkLoop();
        Boolean pcall = checkPcall();

        if(ass || bra || loo || pcall)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Boolean checkVarDecl() 
    {
        if(lex.get(i).content.equals("}"))
        {
            return true;
        }

        Boolean dec = checkDec();
        if(dec)
        {
            i++;
            if(i == lex.size())
            {
                return false;
            }
            if(lex.get(i).content.equals(";"))
            {
                i++;
                if(i == lex.size())
                {
                    return false;
                }
                checkVarDecl();
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    private Boolean checkDec()
    {
        Boolean typ = checkTyp();
        if(typ)
        {
            i++;
            if(i == lex.size())
            {
                return false;
            }
            Boolean var = checkVar();
            if(var)
            {
                return true;
            }

        }
        else if(lex.get(i).content=="arr")
        {
            i++;
            if(i == lex.size())
            {
                return false;
            }
            Boolean Typ = checkTyp();
            if(Typ)
            {
                i++;
                if(i == lex.size())
                {
                    return false;
                }
                if(lex.get(i).content.equals("["))
                {
                    i++;
                    if(i == lex.size())
                    {
                        Boolean con = checkConst();
                        if(con)
                        {
                            i++;
                            if(i == lex.size())
                            {
                                return false;
                            }
                            if(lex.get(i).content.equals("]"))
                            {
                                i++;
                                Boolean var = checkVar();
                                if(var)
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private Boolean checkTyp()
    {
        if(lex.get(i).content.equals("num"))
        {
            return true;
        }
        else if(lex.get(i).content.equals("bool"))
        {
            return true;
        }
        else if(lex.get(i).content.equals("string"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Boolean checkAssign()
    {
        Boolean lhs = checkLHS();
        if(lhs)
        {
            i++;
            if(lex.get(i).content.equals(":="))
            {
                i++;
                Boolean exr = checkExpr();
                if(exr)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
    
    private Boolean checkLHS()
    {
        if(lex.get(i).content.equals("output"))
        {
            return true;
        }
        Boolean var = checkVar();
        if(var)
        {

            return true;
        }
        return false;
    }

    private Boolean checkExpr()
    {
        Boolean con = checkConst();
        Boolean var = checkVar();
        Boolean field = checkField();
        Boolean unOp = checkUnOP();
        Boolean binOp = checkBinOp();

        if(con || var || field || unOp || binOp)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    private Boolean checkConst()
    {
        if(lex.get(i).type.equals("ShortString"))
        {
            Node cons = new Node();
            cons.nodeType = "Terminal";
            cons.id = id++;
            cons.tokenID = lex.get(i);
            return true;
        }
        if(lex.get(i).type.equals("Number"))
        {
            Node cons = new Node();
            cons.nodeType = "Terminal";
            cons.id = id++;
            cons.tokenID = lex.get(i);
            return true;
        }
        if(lex.get(i).content.equals("true"))
        {
            Node cons = new Node();
            cons.nodeType = "Terminal";
            cons.id = id++;
            cons.tokenID = lex.get(i);
            return true;
        }
        if(lex.get(i).content.equals("false"))
        {
            Node cons = new Node();
            cons.nodeType = "Terminal";
            cons.id = id++;
            cons.tokenID = lex.get(i);
            return true;
        }
        return false;
    }

    private Boolean checkVar()
    {
        if(lex.get(i).type.equals("userDefinedName"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Boolean checkField()
    {
        if(lex.get(i).type.equals("userDefinedName"))
        {
            Node user = new Node();
            user.nodeType = "Terminal";
            user.id=id++;
            user.tokenID = lex.get(i);
            i++;
            if(lex.get(i).content.equals("["))
            {
                i++;
                Boolean var = checkVar();
                Boolean cons = checkConst();
                if(var)
                {
                    i++;
                    if(lex.get(i).content.equals("]"))
                    {
                        return true;
                    }
                }
                if(cons)
                {
                    i++;
                    if(lex.get(i).content.equals("]"))
                    {
                        return true;
                    }
                }
                else
                {
                    return false;
                }
                
            }
            else
            {
                return false;
            }

        }
        
        return false;
        
    }

    private Boolean checkUnOP()
    {
        if(lex.get(i).content.equals("input"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean var = checkVar();
                if(var)
                {
                    i++;
                    if(lex.get(i).content.equals(")"))
                    {
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("not"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(")"))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    private Boolean checkBinOp()
    {
        System.out.println("In BinOp: "+lex.get(i).content);
        if(lex.get(i).content.equals("and"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("or"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("eq"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("larger"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("add"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("sub"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("multi"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++;
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(lex.get(i).content.equals(","))
                    {
                        Boolean expr2 = checkExpr();
                        if(expr2)
                        {
                            i++;
                            if(lex.get(i).content.equals(")"))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    private Boolean checkBranch()
    {
        if(lex.get(i).content.equals("if"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++; 
                Boolean expr = checkExpr();
                if(expr)
                {
                    System.out.println("In Branch: "+lex.get(i).content);
                    if(lex.get(i).content.equals(")"))
                    {
                        if(lex.get(i).content.equals("then"))
                        {
                            i++;
                            if(lex.get(i).content.equals("{"))
                            {
                                i++;
                                Boolean algo = checkAlgorithm();
                                if(algo)
                                {
                                    i++;
                                    if(lex.get(i).content.equals("}"))
                                    {
                                        i++;
                                        Boolean alt = checkAlt();
                                        if(alt)
                                        {
                                            return true;
                                        }
                                        else
                                        {
                                            return false;
                                        }
                                    }
                                    else
                                    {
                                        return false;
                                    }
                                }
                                else
                                {
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    private Boolean checkAlt()
    {
        if(lex.get(i).content.equals("else"))
        {
            i++;
            if(lex.get(i).content.equals("{"))
            {
                i++;
                Boolean algo = checkAlgorithm();
                if(algo)
                {
                    i++;
                    if(lex.get(i).content.equals("}"))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }

        }
        else
        {
            return false;
        }
    }

    private Boolean checkLoop()
    {
        if(lex.get(i).content.equals("do"))
        {
            i++;
            if(i==lex.size())
            {
                return false;
            }
            if(lex.get(i).content.equals("{"))
            {
                i++;
                if(i==lex.size())
                {
                    return false;
                }
                Boolean algo = checkAlgorithm();
                if(algo)
                {
                    i++;
                    if(i==lex.size())
                    {
                        return false;
                    }
                    if(lex.get(i).content.equals("}"))
                    {
                        i++;
                        if(i==lex.size())
                        {
                            return false;
                        }
                        if(lex.get(i).content.equals("until"))
                        {
                            i++;
                            if(i==lex.size())
                            {
                                return false;
                            }
                            if(lex.get(i).content.equals("("))
                            {
                                i++;
                                if(i==lex.size())
                                {
                                    return false;
                                }
                                Boolean expr = checkExpr();
                                if(expr)
                                {
                                    i++;
                                    if(i==lex.size())
                                    {
                                        return false;
                                    }
                                    if(lex.get(i).content.equals(")"))
                                    {
                                        return true;
                                    }
                                    else
                                    {
                                        return false;
                                    }
                                }
                                else
                                {
                                    return false;
                                }
                            }
                            else
                            {

                            }

                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
                
            }
            else
            {
                return false;
            }
        }
        if(lex.get(i).content.equals("while"))
        {
            i++;
            if(i==lex.size())
            {
                return false;
            }
            if(lex.get(i).content.equals("("))
            {
                i++;
                if(i==lex.size())
                {
                    return false;
                }
                Boolean expr = checkExpr();
                if(expr)
                {
                    i++;
                    if(i==lex.size())
                    {
                        return false;
                    }
                    if(lex.get(i).content.equals(")"))
                    {
                        i++;
                        if(i==lex.size())
                        {
                            return false;
                        }
                        if(lex.get(i).content.equals("do"))
                        {
                            i++;
                            if(i==lex.size())
                            {
                                return false;
                            }
                            if(lex.get(i).content.equals("{"))
                            {
                                i++;
                                if(i==lex.size())
                                {
                                    return false;
                                }
                                Boolean algo = checkAlgorithm();
                                if(algo)
                                {
                                    i++;
                                    if(i==lex.size())
                                    {
                                        return false;
                                    }
                                    if(lex.get(i).content.equals("}"))
                                    {
                                        return true;
                                    }
                                    else
                                    {
                                        return false;
                                    }
                                }
                                else
                                {
                                    return false;
                                }
                            }
                            else
                            {

                            }

                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
                
            }
            else
            {
                return false;
            }
        }
            return false;
    }

    private Boolean checkPcall()
    {
        if(lex.get(i).content.equals("call"))
        {
            i++;
            if(i==lex.size())
            {
                return false;
            }
            if(lex.get(i).type.equals("userDefinedName"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

}
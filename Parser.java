import java.util.EnumSet;
import java.util.LinkedList;

import javax.swing.text.StyledEditorKit.BoldAction;
public class Parser{

    private Lexer lexer = new Lexer();
    private LinkedList<Token> lex = lexer.create();
    private LinkedList<Token> storer;
    private int i =0;
    private int id = 1;
    Boolean done = false;

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
                        System.out.println("After Algo "+ lex.get(i).content);
                        if(lex.get(i).content.equals("halt"))
                        {
                            System.out.println("halt");
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
                                done = true;
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
                System.out.println("Check man "+PD+" "+i);
                
            }
            else
            {
                //Error Parser stumbles
                System.out.println("Parse error!!");
            }
            
        }
        
    }

    // private Boolean checkProcDefs()
    // {
    //     if(lex.get(i).content.equals("proc"))
    //     {
    //         i++;
    //     }
    //     if(lex.get(i).type.equals("userDefinedName"))
    //     {
    //         i++;
    //         if(lex.get(i).content.equals("{"))
    //         {
    //             i++;
    //             Boolean prc = checkOutterProcDefs();
    //             if(prc)
    //             {
    //                 System.out.println("prc "+prc);
    //                 Boolean Alg = checkAlgorithm();
    //                 if(Alg)
    //                 {
    //                     if(lex.get(i).content.equals("return"))
    //                     {
    //                         i++;
    //                         if(lex.get(i).content.equals(";"))
    //                         {
    //                             Boolean varD = checkVarDecl();
    //                             if(varD)
    //                             {
    //                                 if(lex.get(i).content.equals("}"))
    //                                 {
    //                                     return true;
    //                                 }
    //                             }
    //                         }
    //                     }
    //                 }
    //             }
               
    //         }
    //     }
    //     else
    //     {
    //         Boolean alg = checkAlgorithm();
    //         if(alg)
    //         {
    //             if(lex.get(i).content.equals("return"))
    //             {
    //                 i++;
    //                 if(lex.get(i).content.equals(";"))
    //                 {
    //                     i++;
    //                     Boolean varD = checkVarDecl();
    //                     if(varD)
    //                     {
    //                         System.out.println("alllg "+lex.get(i).content);
    //                         if(lex.get(i).content.equals("}"))
    //                         {
    //                             return true;
    //                         }
    //                     }
    //                 }
    //             }
    //         }

    //     }
    //     return false;
    // }

    // private Boolean checkOutterProcDefs()
    // {
    //     if(lex.get(i).content.equals("main"))
    //     {
    //         System.out.println("We are true: "+lex.get(i).content);
    //         return true;
    //     }
    //     System.out.println("Before "+lex.get(i).content);
    //     Boolean prc = checkProcDefs();
    //     System.out.println("Procbool "+prc);
    //     if(prc)
    //     {
    //         if(lex.get(i).content.equals(","))
    //         {
    //             System.out.println(",");
    //             i++;
    //             return checkOutterProcDefs();
    //         }
    //         else
    //         {
    //             //error parser stumbles
    //             return false;
    //         }

    //     }
    //         //error, Parser stumbles
    //         System.out.println("WHy man");
    //     return false;
    // }

    private Boolean checkProcDefs()
    { 
        if(lex.get(i).content.equals("main"))
        {
            return true;
        }
        LinkedList<Token> checkList = new LinkedList<Token>();
        while(i<lex.size() && !lex.get(i).content.equals("main"))
        {
            checkList.add(lex.get(i));
            i++;
        }
        // if(i==lex.size() || !lex.get(i).content.equals("main"))
        // {
        //     return false;
        // }
        Boolean status = true;

        System.out.println("i "+checkList.size());

        int last = 0;
        for(int j = 0;j<checkList.size();j++)
        {
            System.out.println("J man "+j);
            int index = i;
            i = j;
            Boolean genAlg = checkAlgorithm();
            if(genAlg)
            {
                j=i;
            }
            i = index;
            if(checkList.get(j).content.equals("proc"))
            {
                if(checkList.get(j+1).type.equals("userDefinedName"))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).type.equals("userDefinedName"))
            {
                if(checkList.get(j+1).content.equals("{"))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).type.equals("{"))
            {
                int index2 = i;
                i = j+1;
                Boolean alg = checkAlgorithm();
                if(alg)
                {
                    j=i;
                }
    
                i = index2;
                if(checkList.get(j+1).type.equals("proc") || alg )
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).type.equals("}"))
            {
                if(checkList.get(j+1).type.equals(","))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).type.equals(","))
            {
                if(checkList.get(j+1).type.equals("proc"))
                {
                    continue;
                }
                if(checkList.get(j+1).type.equals("main"))
                {

                }
                else
                {
                    return false;
                }
            }
            if(genAlg)
            {
                if(checkList.get(j).content.equals("return"))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).content.equals("return"))
            {
                if(checkList.get(j+1).content.equals(";"))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            if(checkList.get(j).content.equals(";"))
            {
                int index3 = i;
                i = j;
                i++;
                Boolean varD = checkVarDecl();
                if(varD)
                {
                    j=i;
                }
                
                i = index3;
                
                if(varD)
                {
                    if(checkList.get(j).content.equals("}"))
                    {
                        if(j+1<checkList.size())
                        {
                            if(checkList.get(j+1).content.equals(",") && j+2==checkList.size())
                            {
                                return true;
                            }
                            else if(checkList.get(j+1).content.equals(","))
                            {
                                continue;
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
        System.out.println("Whats instr "+ ins);
        if(ins)
        {
            System.out.println("Sym "+lex.get(i).content+" "+i);
            if(lex.get(i).content.equals(";"))
            {
                System.out.println(";");
                i++;
                return checkAlgorithm();
            }
            else
            {
                //error parser stumbles
                return false;
            }

        }
            //error, Parser stumbles
            System.out.println("WHy man");
        return false;

    }

    private Boolean checkInstr()
    {
        System.out.println("In Instr: "+lex.get(i).content);
        int index = i;
        Boolean ass = checkAssign();
        Boolean bra = false;
        Boolean loo = false;
        Boolean pcall = false;
        if(!ass)
        {
            i=index;
            bra = checkBranch();
            if(!bra)
            {

                i=index;
                loo = checkLoop();
                if(!loo)
                {
                    i=index;
                    pcall = checkPcall();
                }
            }
        }
        
        
       

        if(ass)
        {
            i++;
            return true;
        }
        if(bra)
        {
            return true;
        }
        if(loo)
        {
            i++;
            return true;
        }
        if(pcall)
        {
            i++;
            return true;
        }
        else
        {
            return false;
        }
    }

    private Boolean checkVarDecl() 
    {
        if(lex.get(i).content.equals("}") && done)
        {
            System.out.println("The parser has passed");
            System.exit(0);
        }
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
        // Boolean con = checkConst();
        // Boolean var = checkVar();
        // Boolean field = checkField();
        // Boolean unOp = checkUnOP();
        // Boolean binOp = checkBinOp();

        // if(con ||)
        // {
        //     return true;
        // }
        // else 
        // {
        //     return false;
        // }

        System.out.println("Second Expr!! "+lex.get(i).content);
        int index = i;
        Boolean con = checkConst();
        Boolean var = false;
        Boolean field = false;
        Boolean unOp = false;
        Boolean binOp = false;

        if(!con)
        {
            i=index;
            var = checkVar();
            if(!var)
            {
                i=index;
                field = checkField();
                if(!field)
                {
                    i=index;
                    unOp = checkUnOP();
                    if(!unOp)
                    {
                        System.out.println("Un failed!!");
                        i=index;
                        binOp = checkBinOp();
                    }
                }
            }
        }
        
        
       

        if(con)
        {
            return true;
        }
        if(var)
        {
            System.out.println("Var passed!!");
            return true;
        }
        if(field)
        {
            return true;
        }
        if(unOp)
        {
            return true;
        }
        if(binOp)
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
            System.out.println("Is it not "+ lex.get(i).content);
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
                        i++;
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
        System.out.println("In branccccc!!!"+ lex.get(i).content);
        if(lex.get(i).content.equals("if"))
        {
            i++;
            if(lex.get(i).content.equals("("))
            {
                i++; 
                Boolean expr = checkExpr();
                if(expr)
                {
                    if(lex.get(i).content.equals(")"))
                    {
                        i++;
                        if(lex.get(i).content.equals(")"))
                        {
                            i++;
                        }
                        if(lex.get(i).content.equals("then"))
                        {

                            i++;
                            if(lex.get(i).content.equals("{"))
                            {   
                                i++;
                                System.out.println("Algo check "+ lex.get(i).content);
                                Boolean algo = checkAlgorithm();
                                
                                if(algo)
                                {
                                    System.out.println("After Algo check "+ lex.get(i).content+" "+i);
                                    if(lex.get(i).content.equals("}"))
                                    {
                                        i++;

                                        Boolean alt = checkAlt();
                                        if(alt)
                                        {
                                            System.out.println("Atl check "+ alt);
                                        
                                            System.out.println("Please hlee "+ lex.get(i).content+" "+i);
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
        return false;
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
                    if(lex.get(i).content.equals("}"))
                    {
                        i++;
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
        if(lex.get(i).content.equals(";"))
        {
            System.out.println("Alterr check "+ lex.get(i).content+" "+i);
            return true;
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
                                    if(lex.get(i).content.equals(")"))
                                    {
                                        i++;
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
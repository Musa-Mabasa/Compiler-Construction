import java.util.*;

public class Scoping {

    private Node root = null;

    private Hashtable<String, String[]> scopeTable = null;

    public Scoping() {
        scopeTable = new Hashtable<String, String[]>();
    }

    public void Scope() {

        System.out.print("Enter file name: ");
        try (Scanner scanner = new Scanner(System.in)) {
            String fileName = scanner.nextLine();
            Parser parser = new Parser(fileName);
            root = parser.parse();

            createTable(root,"global",0);

            printTable();

            checkNaming();

            // checkCalls(root, "global", 0);

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        
    }

    private void createTable(Node node, String currentScope, int currentScopeID) {
        if(node == null) {
            return;
        }


        if(node.getType().equals("Non-Terminal")){

            if(node.getContent().equals("NUMVAR") || node.getContent().equals("BOOLVAR") || node.getContent().equals("STRINGV")){
                String var = getVAR(node, node.getId() , "");

                String[] value = {var, "0", "global"};
                Boolean isDuplicate = false;

                for(String key: scopeTable.keySet()){
                    String[] tableValue = scopeTable.get(key);
                    if(isDuplicate(value, tableValue)){
                        isDuplicate = true;
                        break;
                    }
                }
                if(!isDuplicate){
                    scopeTable.put(String.valueOf(node.getId()), value);
                }

                for(Node child : node.children) {
                    createTable(child, currentScope, currentScopeID);
                }
            }
            else if(node.getContent().equals("PROC")){
                String procName = getProcName(node, node.getId(), "");

                String[] value = {procName, String.valueOf(currentScopeID), currentScope};

                scopeTable.put(String.valueOf(node.getId()), value);


                for(Node child : node.children) {
                    createTable(child, procName, node.getId());
                }
            }
            else{
                for(Node child : node.children) {
                    createTable(child, currentScope, currentScopeID);
                }
            }
            
        }
        else{
            createTable(null, currentScope, currentScopeID);
        }

    }

    private String getVAR(Node node, int id,  String var){
        if(node == null) {
            return "";
        }

        if(node.getType().equals("Non-Terminal")){
           
            if(node.children.size()==2){
                return getVAR(node.children.get(0), id, var) + getVAR(node.children.get(1), id, var);
            }
            else{
                return getVAR(node.children.get(0), id, var);
            }
        }
        else{
            return node.getContent();
        }
        
    }

    private String getProcName(Node node, int id,  String procName){
        if(node == null) {
            return "";
        }

        if(node.getType().equals("Non-Terminal")){

            if(node.getContent().equals("PROC")){
                return getProcName(node.children.get(0), id, procName) + getProcName(node.children.get(1), id, procName);
            }
            else{
                if(node.children.size()==2){
                    return getProcName(node.children.get(0), id, procName) + getProcName(node.children.get(1), id, procName);
                }
                else{
                    return getProcName(node.children.get(0), id, procName);
                }
            }
           
            
        }
        else{
            return node.getContent();
        }
        
    }

    private Boolean isDuplicate(String [] str1, String [] str2){
        if(str1[0].equals(str2[0])){
            return true;
        }

        return false;
    }

    private void checkNaming(){
        for(String key: scopeTable.keySet()){
            String[] value = scopeTable.get(key);
            if(value[0].charAt(0) == 'p'){
                if(checkSiblings(key,value) && checkParent(key, value) && checkUncle(key, value)){
                    continue;
                }
                else{
                    System.out.println("\u001B[31mSementic Error\u001B[0m: invalid procedure declaration");
                    System.exit(0);
                }
            } 
        }
    }

    private Boolean checkSiblings(String key, String [] value){
        String scopeID =  value[1];
        String nodeName = value[0];
        for(String Tablekey: scopeTable.keySet()){
            String[] TableValue = scopeTable.get(Tablekey);
            if(TableValue[0].charAt(0) == 'p'){
                if(Tablekey.equals(key)){
                    continue;
                }
                else if(scopeID.equals(TableValue[1]) && nodeName.equals(TableValue[0]) ){
                    System.out.println("sibling");
                    return false;
                }
            }
            
        }

        return true;

    }

    private Boolean checkParent(String key, String [] value){
        if(value[0].equals(value[2])){
            System.out.println("parent");
            return false;
        }

        return true;
    }

    private Boolean checkUncle(String key, String [] value){
        String parentId = value[1];
        String parentScopeID = "";
        for(String Tablekey: scopeTable.keySet()){
            String[] TableValue = scopeTable.get(Tablekey);
            if(TableValue[0].charAt(0) == 'p'){
                if(Tablekey.equals(parentId)){
                    parentScopeID = TableValue[1];
                }
            }
        }

        for(String Tablekey: scopeTable.keySet()){
            String[] TableValue = scopeTable.get(Tablekey);
            if(TableValue[0].charAt(0) == 'p'){
                if(Tablekey.equals(key)){
                    continue;
                }
                else if(parentScopeID.equals(TableValue[1]) && value[0].equals(TableValue[0]) ){
                    System.out.println("uncle");
                    return false;
                }
            }
        }
        return true;

    }

    private void checkCalls(Node node, String currentScope, int currentScopeID) {
        if(node == null) {
            return;
        }


        if(node.getType().equals("Non-Terminal")){

            if(node.getContent().equals("NUMVAR") || node.getContent().equals("BOOLVAR") || node.getContent().equals("STRINGV")){
                String var = getVAR(node, node.getId() , "");

                String[] value = {var, "0", "global"};
                Boolean isDuplicate = false;

                for(String key: scopeTable.keySet()){
                    String[] tableValue = scopeTable.get(key);
                    if(isDuplicate(value, tableValue)){
                        isDuplicate = true;
                        break;
                    }
                }
                if(!isDuplicate){
                    scopeTable.put(String.valueOf(node.getId()), value);
                }

                for(Node child : node.children) {
                    createTable(child, currentScope, currentScopeID);
                }
            }
            else if(node.getContent().equals("PROC")){
                String procName = getProcName(node, node.getId(), "");

                String[] value = {procName, String.valueOf(currentScopeID), currentScope};

                scopeTable.put(String.valueOf(node.getId()), value);


                for(Node child : node.children) {
                    createTable(child, procName, node.getId());
                }
            }
            else{
                for(Node child : node.children) {
                    createTable(child, currentScope, currentScopeID);
                }
            }
            
        }
        else{
            createTable(null, currentScope, currentScopeID);
        }

    }

    // private Boolean isDigit(String str){
    //     try{
    //         Integer.parseInt(str);
    //         return true;
    //     }
    //     catch(Exception e){
    //         return false;
    //     }

    // }

    private void printTable(){
        for(String key: scopeTable.keySet()){
            String[] value = scopeTable.get(key);
            System.out.println("NodeID: " + key + " | " + "NodeName: " + value[0] + " | " + "ScopeID: " + value[1] + " | " + "ScopeName: " + value[2]);
        }
    }
}

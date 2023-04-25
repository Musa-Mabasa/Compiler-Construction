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

                currentScopeID++;
                currentScope = procName;

                for(Node child : node.children) {
                    createTable(child, currentScope, currentScopeID);
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

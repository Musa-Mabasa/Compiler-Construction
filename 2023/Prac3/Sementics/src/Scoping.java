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

            if(node.getContent().equals("NUMVAR")){
                String var = getNUMVAR(node, node.getId() , "");

                String[] value = {var, "0", "global"};
                scopeTable.put(String.valueOf(node.getId()), value);
            }
            // else if(node.getContent().equals("BOOLVAR")){
            //     node = getBOOLVAR(node, "");
            // }
            // else if(node.getContent().equals("STRINGV")){
            //     node = getSTRINGV(node, "");
            // }
            // else if(node.getContent().equals("PROC")){
            //     node = getProc(node, "");
            // }
            for(Node child : node.children) {
                createTable(child, currentScope, currentScopeID);
            }
        }
        else{
            createTable(null, currentScope, currentScopeID);
        }

    }

    private String getNUMVAR(Node node, int id,  String var){
        if(node == null) {
            return "";
        }

        System.out.println("Node: "+node.getContent());

        if(node.getType().equals("Non-Terminal")){
           
            if(node.children.size()==2){
                return getNUMVAR(node.children.get(0), id, var) + getNUMVAR(node.children.get(1), id, var);
            }
            else{
                return getNUMVAR(node.children.get(0), id, var);
            }
        }
        else{
            return node.getContent();
        }
        
    }

    private Boolean isDigit(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    private void printTable(){
        for(String key: scopeTable.keySet()){
            String[] value = scopeTable.get(key);
            System.out.println("NodeID: " + key + " | " + "NodeName: " + value[0] + " | " + "ScopeID: " + value[1] + " | " + "ScopeName: " + value[2]);
        }
    }
}

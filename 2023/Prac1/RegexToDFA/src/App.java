import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter a regular expression: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        // if(isRegexCorrect(input)){
        //     System.out.println("Regex is correct");
        //     ConvertToNFA(input);
        // }
        // else{
        //     System.out.println("Regex is incorrect");
        // }

        ToNFA nfa = new ToNFA();

        nfa.ConvertToNFA(input);
        nfa.printNFA();
    }
}

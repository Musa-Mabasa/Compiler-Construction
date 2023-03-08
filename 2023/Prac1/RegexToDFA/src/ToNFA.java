import java.util.*;
import java.util.Scanner;
import java.util.Stack;
// import java.util.regex.*;

public class ToNFA {

    Stack<NFA> stack = new Stack<NFA>();


    public static void main(String[] args) {
        // Read in input from console
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

    public  boolean isRegexCorrect(String input){
        // Check if the regex is correct
        // Regex must be a string of characters, numbers, kleene stars, ors, and concatenations
        // Regex can be surrounded by parentheses
      return true;


    }

    public void ConvertToNFA(String input){

        for(int i = 0;i<input.length();i++){
            if(input.charAt(i) == '('){
                stack.push(null);
            }
            else if(input.charAt(i) == ')'){
                List<NFA> nfaList = new ArrayList<NFA>();

                while(stack.peek() != null){
                    System.out.println("Adding to list");
                    if(stack.peek().startState != null)
                        System.out.println(stack.peek().startState.name);
                    nfaList.add(stack.pop());
                }
                stack.pop();

                NFA nfa = new NFA();

                boolean isUnion = false;

                for(NFA nfas : nfaList){
                    if(nfas.isUnion){
                        isUnion = true;
                    }
                }

                if(isUnion){
                    nfa.unionNFA(nfaList);
                    System.out.println(nfa.endState.name);
                    stack.push(nfa);
                }
            }
            else if(input.charAt(i) == '*'){
                NFA nfa = new NFA();
                nfa.kleeNfa(stack.pop());
                stack.push(nfa);
            }
            else if(input.charAt(i) == '|'){
                NFA nfa = new NFA();
                nfa.isUnion = true;
                stack.push(nfa);

            }
            else if(input.charAt(i) == '.'){
                System.out.println("Concatenation");
            }
            else{
                System.out.println("We have a character");
                NFA nfa = new NFA();
                nfa.startState = new State(input.charAt(i), false);
                nfa.endState = new State(input.charAt(i), false);
                nfa.startState.addTransition(nfa.endState, input.charAt(i));
                nfa.states.add(nfa.startState);
                nfa.states.add(nfa.endState);
                stack.push(nfa);
            }
        }

    }

    public void printNFA(){
            System.out.println("Printing NFA");
            NFA nfa = stack.pop();
            
            System.out.println("States: ");

            for(State state : nfa.states){
                System.out.println("State: " + state.name);
            }

            System.out.println("Transitions: ");

            for(State state : nfa.states){
                for(Transition transition : state.transitions){
                    System.out.println("Transition: " +"from " + transition.from.name +" " + transition.symbol + " to " + transition.to.name);
                }
            }
    }
}

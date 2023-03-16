import java.util.*;

public class MinDFAState {
    public char name;
    public boolean isAccepting;
    public List<DFAState> dfaStates;
    public List<Transition> transitions;
    public Boolean isVisited;

    public MinDFAState(char name, boolean isAccepting){
        this.name = name;
        this.isAccepting = isAccepting;
        this.transitions =new ArrayList<Transition>();
        this.isVisited = false;
    }

    public void addTransition(MinDFAState to, Character symbol){
        Transition transition = new Transition(this, to, symbol);
        this.transitions.add(transition);
    } 
}

import java.util.*;


public class DFAState{
    public String name;
    public boolean isAccepting;
    public List<Transition> transitions;
    public List<State> nfaStates;
    public boolean isVisited;

    public DFAState(String name, boolean isAccepting){
        this.name = name;
        this.isAccepting = isAccepting;
        this.transitions = new ArrayList<Transition>();
        this.nfaStates = new ArrayList<State>();
    }

    public void addTransition(DFAState to, Character symbol){
        Transition transition = new Transition(this, to, symbol);
        this.transitions.add(transition);
    } 
}

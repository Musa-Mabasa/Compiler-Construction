import java.util.*;

public class State {

    public String name;
    public boolean isAccepting;
    public List<Transition> transitions;

    public State(String name, boolean isAccepting){
        this.name = name;
        this.isAccepting = isAccepting;
        this.transitions =new ArrayList<Transition>();
    }

    public void addTransition(State to, Character symbol){
        Transition transition = new Transition(this, to, symbol);
        this.transitions.add(transition);
    }

    



    


    
}

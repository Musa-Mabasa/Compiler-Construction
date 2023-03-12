import java.util.*;

public class NFA {
    
    public State startState;
    public State endState;
    public List<State> states;
    public boolean isUnion;

    public NFA(){
        this.startState = null;
        this.endState = null;
        isUnion = false;
        states = new ArrayList<State>();
    }

    public void addEpsilonTransition(State state, State desState){
       Transition outTransition = new Transition(state, desState, '#');
       this.startState.transitions.add(outTransition);
    }

    public NFA unionNFA(List<NFA> nfaList, int count){
        // integer to character
        String label = Integer.toString(count);
        this.startState = new State(label, false);
        count++;
        String label2 = Integer.toString(count);
        this.endState = new State(label2, false);
        count++;
        this.states.add(startState);
        this.states.add(endState);
        for(NFA nfa : nfaList){
            if(!nfa.isUnion){
                addEpsilonTransition(startState, nfa.startState);
                nfa.endState.addTransition(endState, '#');
                for(State state :nfa.states){
                    this.states.add(state);
                }
            }
        }
        return this;
    }

    public NFA kleeNfa(NFA nfa, int count){
        String label = Integer.toString(count);
        this.startState = new State(label, false);
        count++;
        String label2 = Integer.toString(count);
        this.endState = new State(label2, false);
        count++;
        this.states.add(startState);
        this.states.add(endState);
        addEpsilonTransition(startState,nfa.startState);
        nfa.endState.addTransition(startState, '#');
        addEpsilonTransition(startState, endState);
        for(State state :nfa.states){
            this.states.add(state);
        }
        
        return this;
    }

    public NFA plus(NFA nfa, int count){
        String label = Integer.toString(count);
        this.startState = new State(label, false);
        count++;
        String label2 = Integer.toString(count);
        this.endState = new State(label2, false);
        count++;
        this.states.add(startState);
        this.states.add(endState);
        startState.addTransition(nfa.startState, '#');
        nfa.endState.addTransition(endState, '#');
        endState.addTransition(startState, '#');
        for(State state :nfa.states){
            this.states.add(state);
        }
        return this;
    }

    public NFA optional(NFA nfa, int count){
        String label = Integer.toString(count);
        this.startState = new State(label, false);
        count++;
        String label2 = Integer.toString(count);
        this.endState = new State(label2, false);
        count++;
        startState.addTransition(endState, '#');
        startState.addTransition(nfa.startState, '#');
        nfa.endState.addTransition(endState, '#');
        this.states.add(startState);
        this.states.add(endState);
        for(State state :nfa.states){
            this.states.add(state);
        }
        return this;
    }
}

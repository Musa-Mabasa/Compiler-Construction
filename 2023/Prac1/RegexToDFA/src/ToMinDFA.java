import java.util.*;


public class ToMinDFA {

    private char stateCount = 'A';

    public MinDFA convertToMinDFA(DFA dfa){
        boolean isDone = false;

        List<DFAState> isNotAcceptList = new ArrayList<DFAState>();
        List<DFAState> isAcceptList = new ArrayList<DFAState>();

        MinDFA minDFA = new MinDFA();

        for(DFAState state: dfa.states){
            if(state.isAccepting){
                isAcceptList.add(state);
            }
            else{
                isNotAcceptList.add(state);
            }
        }

        if(isAcceptList.size() == 1 && isNotAcceptList.size() == 1){
            MinDFAState minDFAState1 = new MinDFAState(stateCount++, true);
            MinDFAState minDFAState2 = new MinDFAState(stateCount++, true);

            minDFAState1.dfaStates.add(isNotAcceptList.get(0));
            minDFAState2.dfaStates.add(isAcceptList.get(0));

            for(Transition transition : isNotAcceptList.get(0).transitions){
                if(transition.dfaTo == isAcceptList.get(0)){
                    minDFAState1.addTransition(minDFAState2, transition.symbol);
                }
                else{
                    minDFAState1.addTransition(minDFAState1, transition.symbol);
                }
            }

            for(Transition transition : isAcceptList.get(0).transitions){
                if(transition.dfaTo == isNotAcceptList.get(0)){
                    minDFAState2.addTransition(minDFAState1, transition.symbol);
                }
                else{
                    minDFAState2.addTransition(minDFAState2, transition.symbol);
                }
            }

            minDFA.states.add(minDFAState1);
            minDFA.states.add(minDFAState2);

        }

        else if(isNotAcceptList.size()==0){
            MinDFAState minDFAState = new MinDFAState(stateCount++, true);

            for(DFAState state : isAcceptList){
                minDFAState.dfaStates.add(state);
            }
            for(DFAState state : minDFAState.dfaStates){
                for(Transition transition : state.transitions){
                    minDFAState.addTransition(minDFAState, transition.symbol);
                }
            }
        }

        else if(isAcceptList.size()==0){
            MinDFAState minDFAState = new MinDFAState(stateCount++, false);

            for(DFAState state : isNotAcceptList){
                minDFAState.dfaStates.add(state);
            }
            for(DFAState state : minDFAState.dfaStates){
                for(Transition transition : state.transitions){
                    minDFAState.addTransition(minDFAState, transition.symbol);
                }
            }
        }

        else if(isAcceptList.size() > 1 && isNotAcceptList.size() > 1){
            MinDFAState minDFAState1 = new MinDFAState(stateCount++, true);
            MinDFAState minDFAState2 = new MinDFAState(stateCount++, true);

            for(DFAState state : isNotAcceptList){
                minDFAState1.dfaStates.add(state);
            }

            for(DFAState state : isAcceptList){
                minDFAState2.dfaStates.add(state);
            }

            // Do the Equivalence
        }








        
        



        return new MinDFA();
    }
    
}

class MinDFA{
    public List<MinDFAState> states;
    public MinDFAState startState;
    public MinDFAState endState;

    public MinDFA(){
        this.states = new ArrayList<MinDFAState>();
    }
}
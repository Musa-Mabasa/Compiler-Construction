public class Transition {

    public State from;
    public State to;
    public DFAState dfaFrom;
    public DFAState dfaTo;
    public Character symbol;
    public MinDFAState minDfaFrom;
    public MinDFAState minDfaTo;

    public Transition(State from, State to, Character symbol){
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }

    public Transition(DFAState from, DFAState to, Character symbol){
        this.dfaFrom = from;
        this.dfaTo = to;
        this.symbol = symbol;
    }

    public Transition(MinDFAState from, MinDFAState to, Character symbol){
        this.minDfaFrom = from;
        this.minDfaTo = to;
        this.symbol = symbol;
    }
}

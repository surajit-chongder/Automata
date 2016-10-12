import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class DFA {
    private ArrayList<State> states;
    private ArrayList<Alphabet> alphabetSet;
    private TransitionFunction transitionFunction;
    private State initialState;
    private ArrayList<State> finalStates;

    public DFA(ArrayList<State> states, ArrayList<Alphabet> alphabetSet, TransitionFunction transitionFunction, State state, ArrayList<State> finalStates) {
        this.states = states;
        this.alphabetSet = alphabetSet;
        this.transitionFunction = transitionFunction;
        this.initialState = state;
        this.finalStates = finalStates;
    }
    private List<String> getAllAlphabets(String determinationString) {
        return asList(determinationString.split(""));
    }

    public boolean isInLanguage(String determinationString) {
        ArrayList<Alphabet> deteministicAlphabetSet = new ArrayList<>();
        getAllAlphabets(determinationString).forEach(alphabet -> deteministicAlphabetSet.add(new Alphabet(alphabet)));
        State currentState = this.initialState;
        for (Alphabet alphabet : deteministicAlphabetSet) {
            currentState = this.transitionFunction.getNextState(currentState, alphabet);
        }
        return isFinalState(currentState);
    }

    private boolean isFinalState(State state) {
        return this.finalStates.contains(state);
    }
}

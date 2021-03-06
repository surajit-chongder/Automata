import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@EqualsAndHashCode
@AllArgsConstructor
public class DFA {
    private ArrayList<State> states;
    private ArrayList<Alphabet> alphabetSet;
    private TransitionTable transitionTable;
    private State initialState;
    private ArrayList<State> finalStates;

    private List<String> getAllAlphabets(String determinationString) {
        return asList(determinationString.split(""));
    }

    public boolean isInLanguage(String determinationString) {
        ArrayList<Alphabet> deteministicAlphabetSet = new ArrayList<>();
        getAllAlphabets(determinationString).forEach(alphabet -> deteministicAlphabetSet.add(new Alphabet(alphabet)));
        State currentState = this.initialState;
        for (Alphabet alphabet : deteministicAlphabetSet) {
            currentState = this.transitionTable.getNextState(currentState, alphabet);
        }
        return isFinalState(currentState);
    }

    private boolean isFinalState(State state) {
        return this.finalStates.contains(state);
    }
}

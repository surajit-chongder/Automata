import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class TransitionFunction {
    private List<Transition> transitions;

    public TransitionFunction(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public State getNextState(State state, Alphabet alphabet){
        for (Transition transition : transitions) {
            if (transition.isSameCurrentStateAndAlphabet(state,alphabet))
                return transition.getNextState();
        }
        return null;
    }
}

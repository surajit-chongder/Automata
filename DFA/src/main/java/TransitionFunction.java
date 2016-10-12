import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
public class TransitionFunction {
    private List<Transition> transitions;

    public State getNextState(State state, Alphabet alphabet){
        for (Transition transition : transitions) {
            if (transition.isSameCurrentStateAndAlphabet(state,alphabet))
                return transition.getNextState();
        }
        return null;
    }
}

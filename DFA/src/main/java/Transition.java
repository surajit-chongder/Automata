import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class Transition {
    private State currentState;
    private Alphabet alphabet;
    @Getter
    private State nextState;

    public boolean isSameCurrentStateAndAlphabet(State currentState, Alphabet alphabet) {
        return this.currentState.equals(currentState) && this.alphabet.equals(alphabet);
    }
}

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Transition {
    private State currentState;
    private Alphabet alphabet;
    @Getter
    private State nextState;

    public Transition(State currentState, Alphabet alphabet, State nextState) {
        this.currentState = currentState;
        this.alphabet = alphabet;
        this.nextState = nextState;
    }

    public boolean isSameCurrentStateAndAlphabet(State currentState, Alphabet alphabet) {
        return this.currentState.equals(currentState) && this.alphabet.equals(alphabet);
    }
}

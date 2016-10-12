import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DFATupleGeneratorTest {
    private DFATupleGenerator DFATupleGenerator;

    @Before
    public void setUp() throws Exception {
        String jsonText = "{\"states\":[\"q1\",\"q2\"],\"alphabet_set\":[\"0\",\"1\"],\"transition_function\":[[\"q1\",\"1\",\"q1\"],[\"q1\",\"0\",\"q2\"]],\"initial_state\":\"q1\",\"final_states\":[\"q2\"]}";
        DFATupleGenerator = new DFATupleGenerator(jsonText);

    }

    @Test
    public void shouldReturnInitialState() throws Exception {
        State expected = new State("q1");
        assertEquals(DFATupleGenerator.getInitialState(), expected);
    }

    @Test
    public void shouldReturnStatesOfDfa() throws Exception {
        State q1 = new State("q1");
        State q2 = new State("q2");
        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);
        assertEquals(DFATupleGenerator.getStates(), states);
    }

    @Test
    public void shouldReturnTransitionsOfDfa() throws Exception {
        State q1 = new State("q1");
        State q2 = new State("q2");
        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");
        Transition transition1 = new Transition(q1, one, q1);
        Transition transition2 = new Transition(q1, zero, q2);
        TransitionFunction transitionFunction = new TransitionFunction(asList(transition1,transition2));
        assertEquals(DFATupleGenerator.getTransitions(), transitionFunction);
    }

    @Test
    public void shouldReturnFinalStatesOfDfa() throws Exception {
        State q2 = new State("q2");
        ArrayList<State> finalState = new ArrayList<>();
        finalState.add(q2);
        assertEquals(DFATupleGenerator.getFinalState(), finalState);
    }

    @Test
    public void shouldReturnAlphabetsOfDfa() throws Exception {
        Alphabet one = new Alphabet("1");
        Alphabet zero = new Alphabet("0");
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        alphabets.add(zero);
        alphabets.add(one);

        assertEquals(DFATupleGenerator.getAlphabetSet(), alphabets);
    }
}
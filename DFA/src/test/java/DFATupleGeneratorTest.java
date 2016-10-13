import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DFATupleGeneratorTest {
    private DFATupleGenerator DFATupleGenerator;
    private State q1,q2;
    private Alphabet one,zero;
    private Transition transition1,transition2,transition3,transition4;
    private TransitionFunction transitionFunction;

    @Before
    public void setUp() throws Exception {
        String jsonText = "[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";
        DFATupleGenerator = new DFATupleGenerator(jsonText);

        q1 = new State("q1");
        q2 = new State("q2");
        one = new Alphabet("1");
        zero = new Alphabet("0");
        transition1 = new Transition(q1, zero, q2);
        transition2 = new Transition(q1, one, q1);
        transition3 = new Transition(q2, zero, q1);
        transition4 = new Transition(q2, one, q2);
        transitionFunction = new TransitionFunction(asList(transition1,transition2,transition3,transition4));
    }
    @Test
    public void shouldReturnDfas() throws Exception {
        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);

        ArrayList<Alphabet> alphabetSet = new ArrayList<>();
        alphabetSet.add(one);
        alphabetSet.add(zero);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q2);

        ArrayList<DFA> expected = new ArrayList<>();
        expected.add(new DFA(states,alphabetSet,transitionFunction,q1,finalStates));

        assertEquals(DFATupleGenerator.getTuple(), expected);
    }

}
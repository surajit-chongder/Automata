import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest {
    private DFA DFA;
    private State q1,q2,q3,q4,q5,q6;
    private Alphabet zero,one;
    private State initialState;

    @Before
    public void setUp() throws Exception {
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        q4 = new State("q4");
        q5 = new State("q5");
        q6 = new State("q6");

        initialState = q1;

        zero = new Alphabet("0");
        one = new Alphabet("1");

    }

    @Test
    public void shouldReturnTrueWhenAStringInThatLanguage(){
        Transition transition1= new Transition(q1, one, q1);
        Transition transition2 = new Transition(q1, zero, q2);

        List<Transition> transitions1 = asList(transition1, transition2);
        TransitionFunction transitionFunction = new TransitionFunction(transitions1);

        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);

        ArrayList<Alphabet> alphabet_set = new ArrayList<>();
        alphabet_set.add(zero);
        alphabet_set.add(one);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q2);

        DFA = new DFA(states,alphabet_set,transitionFunction,initialState,finalStates);

        assertTrue(DFA.isInLanguage("0"));
    }

    @Test
    public void shouldReturnFalseWhenAStringNotInThatLanguage(){
        Transition transition1= new Transition(q1, one, q1);
        Transition transition2 = new Transition(q1, zero, q2);

        ArrayList<Transition> transitions = new ArrayList<>();
        transitions.add(transition1);
        transitions.add(transition2);
        TransitionFunction transitionFunction = new TransitionFunction(transitions);

        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);

        ArrayList<Alphabet> alphabet_set = new ArrayList<>();
        alphabet_set.add(zero);
        alphabet_set.add(one);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q2);

        DFA = new DFA(states,alphabet_set,transitionFunction,initialState,finalStates);

        assertFalse(DFA.isInLanguage("1"));
    }
    @Test
    public void shouldReturnFalseWhenAStringNotEndWithOne(){
        Transition transition1= new Transition(q1, one, q2);
        Transition transition2= new Transition(q1, zero, q6);
        Transition transition3= new Transition(q6, one, q6);
        Transition transition4= new Transition(q6, zero, q6);
        Transition transition5 = new Transition(q2, zero, q3);
        Transition transition6 = new Transition(q3, zero, q4);
        Transition transition7 = new Transition(q4, one, q5);
        Transition transition8 = new Transition(q5, zero, q5);
        Transition transition9 = new Transition(q5, one, q5);
        Transition transition10 = new Transition(q4, zero, q4);
        Transition transition11 = new Transition(q3, one, q2);
        Transition transition12 = new Transition(q2, one, q2);


        List<Transition> transitions = asList(transition1, transition2, transition3, transition4,
                transition5, transition6, transition7, transition8, transition9, transition10, transition11, transition12);

        TransitionFunction transitionFunction = new TransitionFunction(transitions);

        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);
        states.add(q5);
        states.add(q6);

        ArrayList<Alphabet> alphabet_set = new ArrayList<>();
        alphabet_set.add(zero);
        alphabet_set.add(one);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q5);

        DFA = new DFA(states,alphabet_set,transitionFunction,initialState,finalStates);

        assertFalse(DFA.isInLanguage("01001"));
    }

    @Test
    public void shouldReturnTrueWhenAStringStartAndEndWithOne(){
        Transition transition1= new Transition(q1, one, q2);
        Transition transition2= new Transition(q1, zero, q6);
        Transition transition3= new Transition(q6, one, q6);
        Transition transition4= new Transition(q6, zero, q6);
        Transition transition5 = new Transition(q2, zero, q3);
        Transition transition6 = new Transition(q3, zero, q4);
        Transition transition7 = new Transition(q4, one, q5);
        Transition transition8 = new Transition(q5, zero, q5);
        Transition transition9 = new Transition(q5, one, q5);
        Transition transition10 = new Transition(q4, zero, q4);
        Transition transition11 = new Transition(q3, one, q2);
        Transition transition12 = new Transition(q2, one, q2);


        List<Transition> transitions = asList(transition1, transition2, transition3, transition4,
                transition5, transition6, transition7, transition8, transition9, transition10, transition11, transition12);

        TransitionFunction transitionFunction = new TransitionFunction(transitions);

        ArrayList<State> states = new ArrayList<>();
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);
        states.add(q5);
        states.add(q6);

        ArrayList<Alphabet> alphabetSet = new ArrayList<>();
        alphabetSet.add(zero);
        alphabetSet.add(one);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q5);

        DFA = new DFA(states,alphabetSet,transitionFunction,initialState,finalStates);

        assertTrue(DFA.isInLanguage("11001"));
    }
}
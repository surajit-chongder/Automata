import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

@EqualsAndHashCode
@AllArgsConstructor
public class DFAsGenerator {

    private String jsonText;

    private JSONArray parseJson() throws ParseException {
        JSONParser parser = new JSONParser();
        Object parse = parser.parse(jsonText);
        return (JSONArray) parse;
    }
    public ArrayList<DFA> getDfas() throws ParseException {
        ArrayList<DFA> dfas = new ArrayList<DFA>();
        for (int index = 0; index < parseJson().size(); index++) {
            HashMap hashJson = (HashMap) parseJson().get(index);
            HashMap tuple = (HashMap)hashJson.get("tuple");
            State initialState = getInitialState(tuple);
            ArrayList<State> states = getStates(tuple);
            TransitionFunction transitions = getTransitions(tuple);
            ArrayList<State> finalStates = getFinalStates(tuple);
            ArrayList<Alphabet> alphabetSet = getAlphabetSet(tuple);
            dfas.add(new DFA(states,alphabetSet,transitions,initialState,finalStates));
        }
        return dfas;
    }

    private State getInitialState(HashMap tuple) throws ParseException {
        String state = (String) tuple.get("start-state");
        return new State(state);
    }

    private ArrayList<State> getStates(HashMap tuple) throws ParseException {
        JSONArray jsonStates = (JSONArray) tuple.get("states");
        ArrayList<State> states = new ArrayList<>();
        jsonStates.forEach(state -> states.add(new State((String) state)));
        return  states;
    }

    private TransitionFunction getTransitions(HashMap tuple) throws ParseException {
        HashMap delta = (HashMap) tuple.get("delta");
        ArrayList<Transition> transitions = new ArrayList<Transition>();
        delta.forEach((key, value) -> {
            State currentState = new State((String) key);
            HashMap hash = (HashMap) value;
            hash.forEach((alpha,state)->{
                Alphabet alphabet = new Alphabet((String) alpha);
                State nextState = new State((String) state);
                transitions.add(new Transition(currentState,alphabet,nextState));
            });
        });
        return new TransitionFunction(transitions);
    }

    private ArrayList<State> getFinalStates(HashMap tuple) throws ParseException {
        JSONArray jsonStates = (JSONArray) tuple.get("final-states");
        ArrayList<State> states = new ArrayList<>();
        jsonStates.forEach(state -> states.add(new State((String) state)));
        return  states;
    }

    private ArrayList<Alphabet> getAlphabetSet(HashMap tuple) throws ParseException {
        JSONArray alphabetSet = (JSONArray) tuple.get("alphabets");
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        alphabetSet.forEach(alphabet -> alphabets.add(new Alphabet((String) alphabet)));
        return  alphabets;
    }
}

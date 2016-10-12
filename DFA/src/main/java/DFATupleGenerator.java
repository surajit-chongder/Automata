import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DFATupleGenerator {

    private String jsonText;

    public DFATupleGenerator(String jsonText) {
        this.jsonText = jsonText;
    }
    private JSONObject parseJson() throws ParseException {
        JSONParser parser = new JSONParser();
        Object parse = parser.parse(jsonText);
        return (JSONObject) parse;
    }
    public State getInitialState() throws ParseException {
        String state = (String) parseJson().get("initial_state");
        return new State(state);
    }

    public ArrayList<State> getStates() throws ParseException {
        JSONArray jsonStates = (JSONArray) parseJson().get("states");
        ArrayList<State> states = new ArrayList<>();
        jsonStates.forEach(state ->{
            states.add(new State((String) state));
        });
        return  states;
    }

    public TransitionFunction getTransitions() throws ParseException {
        JSONArray transitionFunction = (JSONArray) parseJson().get("transition_function");
        List<Transition> transitions =new ArrayList<>();
        transitionFunction.forEach(transition -> {
            ArrayList transitionList = (ArrayList) transition;
            State currentState = new State((String) transitionList.get(0));
            State nextState = new State((String) transitionList.get(2));
            Alphabet alphabet = new Alphabet((String) transitionList.get(1));
            transitions.add(new Transition(currentState,alphabet,nextState));
        });
        return new TransitionFunction(transitions);
    }

    public ArrayList<State> getFinalState() throws ParseException {
        JSONArray jsonStates = (JSONArray) parseJson().get("final_states");
        ArrayList<State> states = new ArrayList<>();
        jsonStates.forEach(state ->{
            states.add(new State((String) state));
        });
        return  states;
    }

    public ArrayList<Alphabet> getAlphabetSet() throws ParseException {
        JSONArray alphabetSet = (JSONArray) parseJson().get("alphabet_set");
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        alphabetSet.forEach(alphabet ->{
            alphabets.add(new Alphabet((String) alphabet));
        });
        return  alphabets;
    }

    public DFA generateDfa() throws ParseException {
        return new DFA(getStates(),getAlphabetSet(),getTransitions(),getInitialState(),getFinalState());
    }

}

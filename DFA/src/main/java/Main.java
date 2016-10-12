import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String json = "{\"states\":[\"q1\",\"q2\"],\"alphabet_set\":[\"0\",\"1\"],\"transition_function\":[[\"q1\",\"1\",\"q1\"],[\"q1\",\"0\",\"q2\"]],\"initial_state\":\"q1\",\"final_states\":[\"q2\"]}";
        JSONParser parser = new JSONParser();
        Object parse = parser.parse(json);
        JSONObject jsonObject = (JSONObject) parse;

        JSONArray states = (JSONArray) jsonObject.get("states");
        JSONArray alphabetSet = (JSONArray) jsonObject.get("alphabet_set");
        JSONArray transitionFunction = (JSONArray) jsonObject.get("transition_function");
        String initialState = (String) jsonObject.get("initial_state");
        JSONArray finalStates = (JSONArray) jsonObject.get("final_states");

    }
}

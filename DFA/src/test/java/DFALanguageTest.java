import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFALanguageTest {
    private String json;
    @Before
    public void setUp() throws Exception {
        json = "[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"]," +
                "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
                "\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"]," +
                "\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}," +
                "{\"name\":\"even number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"]," +
                "\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
                "\"start-state\":\"q1\",\"final-states\":[\"q1\"]},\"fail-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"]," +
                "\"pass-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";
        
    }

    @Test
    public void shouldCheckTrueForAllPassCases() throws Exception {
        DFAsGenerator DFAsGenerator = new DFAsGenerator(json);
        ArrayList<ArrayList<String>> passCases = getCases("pass-cases");
        ArrayList<DFA> dfas = DFAsGenerator.getDfas();
        assertPassCasesTest(passCases, dfas);
    }

    @Test
    public void shouldCheckFalseForAllPassCases() throws Exception {
        DFAsGenerator DFAsGenerator = new DFAsGenerator(json);
        ArrayList<ArrayList<String>> failCases = getCases("fail-cases");
        ArrayList<DFA> dfas = DFAsGenerator.getDfas();
        assertFailCasesTest(failCases, dfas);

    }

    private void assertPassCasesTest(ArrayList<ArrayList<String>> passCases, ArrayList<DFA> dfas) {
        for (int index = 0; index < dfas.size(); index++) {
            DFA dfa = dfas.get(index);
            ArrayList<String> passCase = passCases.get(index);
            passCase.forEach(pass-> assertTrue(dfa.isInLanguage(pass)));
        }
    }

    private void assertFailCasesTest(ArrayList<ArrayList<String>> failCases, ArrayList<DFA> dfas) {
        for (int index = 0; index < dfas.size(); index++) {
            DFA dfa = dfas.get(index);
            ArrayList<String> failCase = failCases.get(index);
            failCase.forEach(fail-> assertFalse(dfa.isInLanguage(fail)));
        }
    }

    public ArrayList<ArrayList<String>> getCases(String cases) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray parse = (JSONArray) parser.parse(json);
        ArrayList<ArrayList<String>> pass = new ArrayList<>();
        for (Object aParse : parse) {
            HashMap hashJson = (HashMap) aParse;
            ArrayList<String> passCases = new ArrayList<>();
            ArrayList passCasesJson = (ArrayList) hashJson.get(cases);
            passCasesJson.forEach(passCase -> passCases.add((String) passCase));
            pass.add(passCases);
        }
        return pass;
    }
}

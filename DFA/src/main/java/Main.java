import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String jsonContent = new String(Files.readAllBytes(Paths.get("./data/dfaExamples.json")));
        DFAsGenerator dfAsGenerator = new DFAsGenerator(jsonContent);
    }
}

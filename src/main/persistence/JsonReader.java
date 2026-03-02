package persistence;

import model.Question;
import model.PersonalityTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads personality test data from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads personality test data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PersonalityTest read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePersonalityTest(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
   private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses personality test data from JSON object and returns it
    private PersonalityTest parsePersonalityTest(JSONObject jsonObject) {
        PersonalityTest pt = new PersonalityTest();
        addQuestions(pt, jsonObject);
        return pt;
    }


    // MODIFIES: pt
    // EFFECTS: parses questions from JSON object and adds them to personality test
     private void addQuestions(PersonalityTest pt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("personality");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addQuestion(pt, nextThingy);
        }
    }

    // MODIFIES: pt
    // EFFECTS: parses question from JSON object and adds it to personality test
    private void addQuestion(PersonalityTest pt, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String category = jsonObject.getString("category");
        Question newQuestion = new Question(question, category);
        pt.addQuestion(newQuestion);
    }

}

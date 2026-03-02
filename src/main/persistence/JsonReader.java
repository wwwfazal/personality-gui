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

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads personality test data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PersonalityTest read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses personality test data from JSON object and returns it
    private PersonalityTest parsePersonalityTest(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: pt
    // EFFECTS: parses questions from JSON object and adds them to personality test
    private void addQuestions(PersonalityTest pt, JSONObject jsonObject) {

    }

    // MODIFIES: pt
    // EFFECTS: parses question from JSON object and adds it to personality test
    private void addQuestion(PersonalityTest pt, JSONObject jsonObject) {
    }

}

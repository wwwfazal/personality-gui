package persistence;

import model.PersonalityTest;
import model.Question;
import model.Question;
import model.PersonalityTest;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class TestJsonReader extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PersonalityTest pt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPersonalityTest() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPersonalityTest.json");
        try {
            PersonalityTest pt = reader.read();
            assertEquals("None", pt.getDominantFunction());
            assertTrue(pt.getQuestions().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPersonalityTest.json");
        try {
           PersonalityTest pt = reader.read();
            assertEquals("None", pt.getDominantFunction());
            List<Question> questions = pt.getQuestions();
            assertEquals(2, questions.size());
            checkQuestion("Do you enjoy reminiscing the past?", "sensing", questions.get(0));
            checkQuestion("Do you imagine numerous possibilities when working on a project?", "intuition", questions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

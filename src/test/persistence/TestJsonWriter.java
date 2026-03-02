package persistence;

import model.Question;
import model.PersonalityTest;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class TestJsonWriter extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPersonalityTest() {
        try {
            PersonalityTest pt = new PersonalityTest();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPersonalityTestjson");
            writer.open();
            writer.write(pt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPersonalityTest.json");
            pt = reader.read();
            assertEquals("None", pt.getDominantFunction());
            assertTrue(pt.getQuestions().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPersonalityTest() {
        try {
            PersonalityTest pt = new PersonalityTest();
            pt.addQuestion(new Question("Do you enjoy reminiscing the past?", "sensing"));
            pt.addQuestion(new Question("Do you imagine numerous possibilities when working on a project?", "intuition"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPersonalityTest.json");
            writer.open();
            writer.write(pt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPersonalityTest.json");
            pt = reader.read();
            assertEquals("None", pt.getDominantFunction());
            List<Question> questions = pt.getQuestions();
            assertEquals(2, questions.size());
            checkQuestion("Do you enjoy reminiscing the past?", "sensing", questions.get(0));
            checkQuestion("Do you imagine numerous possibilities when working on a project?", "intuition", questions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

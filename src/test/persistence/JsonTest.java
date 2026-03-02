package persistence;

import model.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkQuestion(String text, String category, Question question) {
        assertEquals(text, question.getText());
        assertEquals(category, question.getCategory());
    }
}
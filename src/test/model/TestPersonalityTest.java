package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestPersonalityTest {
    PersonalityTest pt;
    Question q1;
    Question q2;

    @BeforeEach
    void runBefore() {

        pt = new PersonalityTest();
        q1 = new Question("I always make a plan", "Thinking");
        q2 = new Question("I can think of multiple possibilities", "Intuition");

    }

    @Test
    void testConstructor() {
        assertEquals(0, pt.getQuestions().size());
        assertEquals(0, pt.getIntuitionIndex());
        assertEquals(0, pt.getThinkingIndex());
        assertEquals(0, pt.getSensingIndex());
        assertEquals(0, pt.getFeelingIndex());

    }

    @Test
    void testAddQuestion() {
        List<Question> questions = pt.getQuestions();
        questions.add(q1);
        assertEquals(1, questions.size());
        assertTrue(questions.contains(q1));
    }

    @Test
    void testTrackAnswer() {
        pt.addQuestion(q1);
        pt.trackAnswer(q1);

        pt.calculatePercentage();

        assertEquals(0, pt.getIntuitionPercentage());
        assertEquals(100, pt.getThinkingPercentage());
        assertEquals(0, pt.getSensingPercentage());
        assertEquals(0, pt.getFeelingPercentage());

        pt.addQuestion(q2);
        pt.trackAnswer(q2);
        pt.calculatePercentage();

        assertEquals(50, pt.getIntuitionPercentage());
        assertEquals(50, pt.getThinkingPercentage());
        assertEquals(0, pt.getSensingPercentage());
        assertEquals(0, pt.getFeelingPercentage());

    }

    @Test
    void testGetDominantFunction() {
        pt.addQuestion(q1);
        pt.trackAnswer(q1);
        assertEquals("Thinking", pt.getDominantFunction());
    }

    @Test
    void testTrackAnswerWithNullQuestion() {
        pt.trackAnswer(null);
        assertEquals(0, pt.getIntuitionIndex());
        assertEquals(0, pt.getThinkingIndex());
        assertEquals(0, pt.getSensingIndex());
        assertEquals(0, pt.getFeelingIndex());
    }

    @Test
    void testTrackAnswerWithInvalidCategory() {
        Question invalidQuestion = new Question("Invalid", "Unknown");
        pt.addQuestion(invalidQuestion);
        pt.trackAnswer(invalidQuestion);
        assertEquals(0, pt.getIntuitionIndex());
        assertEquals(0, pt.getThinkingIndex());
        assertEquals(0, pt.getSensingIndex());
        assertEquals(0, pt.getFeelingIndex());
    }

    @Test
    void testDominantFunctionTie() {
        Question q3 = new Question("I enjoy details", "Sensing");
        pt.addQuestion(q1); // T
        pt.addQuestion(q3); // S
        pt.trackAnswer(q1);
        pt.trackAnswer(q3);
        String dominant = pt.getDominantFunction();
        assertTrue(dominant.equals("Thinking") || dominant.equals("Sensing"));
    }

    @Test
    void testAddNullQuestion() {
        pt.addQuestion(null);
        assertTrue(pt.getQuestions().isEmpty());
    }

}

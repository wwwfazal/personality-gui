package ui;

import model.Question;
import java.util.*;

// Represents a personality test that contains questions 
// records answers, and calculates percentages and the dominant function
public class PersonalityTest {

    private List<Question> questions;
    private int intuitionIndex;
    private int thinkingIndex;
    private int sensingIndex;
    private int feelingIndex;

    // EFFECTS: Constructs an empty personality test with no questions and all index scores == 0
    public PersonalityTest() {

    }


    // REQUIRES: question != null, and question.getCategory() == "INTUITION" | "THINKING" | "SENSING" | "FEELING"
    // MODIFIES: this
    // EFFECTS: adds the given question to the personality test
    public void addQuestion(Question question) {

    }

    // EFFECTS: returns all questions in a list
    public List<Question> getQuestions() {
        return null;
    }

    // REQUIRES: questions.size() > 0,
    // EFFECTS: returns the function with the highest index 
    public String getDominantFunction() {
        return "";
    }


    // REQUIRES: question in personality test
    // MODIFIES: corresponding index
    // EFFECTS: incremements the score by 1 for the corresponding index of the question
    public void trackAnswer(Question question) {

    }

    // EFFECTS: calculates the percentage of each function based on the number of answers tracked
    //          stores the results in a new percent value for each function 
    public int calculatePercentage() {
        return 0;
    }

    public int getIntuitionIndex() {
        return 0;
    }

     public int getThinkingIndex() {
        return 0;
    }

     public int getSensingIndex() {
        return 0;
    }

     public int getFeelingIndex() {
        return 0;
    }




}

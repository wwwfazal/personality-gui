package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a personality test that contains questions 
// records answers, and calculates percentages and the dominant function
public class PersonalityTest implements Writable {

    private List<Question> questions;
    private int intuitionIndex;
    private int thinkingIndex;
    private int sensingIndex;
    private int feelingIndex;

    private int intuitionPercent;
    private int thinkingPercent;
    private int sensingPercent;
    private int feelingPercent;
    private int totalAnswers;

    // EFFECTS: Constructs an empty personality test with no questions and all index
    // scores == 0
    public PersonalityTest() {
        totalAnswers = 0;

        questions = new ArrayList<>();
        intuitionIndex = 0;
        thinkingIndex = 0;
        sensingIndex = 0;
        feelingIndex = 0;

        intuitionPercent = 0;
        thinkingPercent = 0;
        sensingPercent = 0;
        feelingPercent = 0;

    }

    // REQUIRES: question != null, and question.getCategory() == "INTUITION" |
    // "THINKING" | "SENSING" | "FEELING"
    // MODIFIES: this
    // EFFECTS: adds the given question to the personality test
    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }

    }

    // EFFECTS: returns all questions in a list
    public List<Question> getQuestions() {
        return questions;
    }

    // REQUIRES: questions.size() > 0,
    // EFFECTS: returns the function with the highest index. 
    //          If multiple functions have the same index, return the first function.
    public String getDominantFunction() {
        int cumulative = intuitionIndex + thinkingIndex + sensingIndex + feelingIndex;
        if (cumulative == 0) {
            return "None";
        }

        int highestIndex = intuitionIndex;
        String dominantFunction = "Intuition";

        if (thinkingIndex > highestIndex) {
            highestIndex = thinkingIndex;
            dominantFunction = "Thinking";
        }

        if (sensingIndex > highestIndex) {
            highestIndex = sensingIndex;
            dominantFunction = "Sensing";
        }

        if (feelingIndex > highestIndex) {
            highestIndex = feelingIndex;
            dominantFunction = "Feeling";
        }

        EventLog.getInstance().logEvent(
                new Event("User's dominant function calculated:" + dominantFunction)
            );
        return dominantFunction;

    }

    // REQUIRES: question in personality test
    // MODIFIES: corresponding index
    // EFFECTS: incremements the score by 1 for the corresponding index of the
    // question
    public void trackAnswer(Question question) {
        if (question != null) {
            totalAnswers++;
            EventLog.getInstance().logEvent(
                new Event("User's total answers updated: " + totalAnswers)        
            );
            String category = question.getCategory();
            if (category.equals("Intuition")) {
                intuitionIndex += 1;
            } else if (category.equals("Thinking")) {
                thinkingIndex += 1;
            } else if (category.equals("Sensing")) {
                sensingIndex += 1;
            } else if (category.equals("Feeling")) {
                feelingIndex += 1;
            }
            
            EventLog.getInstance().logEvent(
                new Event("Tracked user's answer for category: " + category 
                             + ". User's total answers updated: " + totalAnswers)
                
            );
        }

    }

    // EFFECTS: calculates the percentage of each function based on the number of
    // answers tracked
    // stores the results in a new percent value for each function
    public void calculatePercentage() {
        int cumulative = intuitionIndex + sensingIndex + thinkingIndex + feelingIndex;
        if (cumulative == 0) {
            intuitionPercent = 0;
            thinkingPercent = 0;
            sensingPercent = 0;
            feelingPercent = 0;
        } else {
            intuitionPercent = (intuitionIndex * 100) / cumulative;
            thinkingPercent = (thinkingIndex * 100) / cumulative;
            sensingPercent = (sensingIndex * 100) / cumulative;
            feelingPercent = (feelingIndex * 100) / cumulative;

             EventLog.getInstance().logEvent(
                new Event("User's current function percentages calculated: (I/T/S/F) " 
                + intuitionPercent + "/" + thinkingPercent + "/" + sensingPercent + "/" + feelingPercent)
            );
        }

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Question q : questions) {
            JSONObject questionJson = new JSONObject();
            questionJson.put("question", q.getText());
            questionJson.put("category", q.getCategory());
            jsonArray.put(questionJson);
        }

        json.put("personality", jsonArray);

        // Save tracked indexes
        json.put("intuitionIndex", intuitionIndex);
        json.put("thinkingIndex", thinkingIndex);
        json.put("sensingIndex", sensingIndex);
        json.put("feelingIndex", feelingIndex);
        json.put("totalAnswers", totalAnswers);

        return json;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public int getIntuitionIndex() {
        return intuitionIndex;
    }

    public int getThinkingIndex() {
        return thinkingIndex;
    }

    public int getSensingIndex() {
        return sensingIndex;
    }

    public int getFeelingIndex() {
        return feelingIndex;
    }

    public int getIntuitionPercentage() {
        return intuitionPercent;
    }

    public int getThinkingPercentage() {
        return thinkingPercent;
    }

    public int getSensingPercentage() {
        return sensingPercent;
    }

    public int getFeelingPercentage() {
        return feelingPercent;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public void setIntuitionIndex(int value) {
        intuitionIndex = value;
    }

    public void setThinkingIndex(int value) {
        thinkingIndex = value;
    }

    public void setSensingIndex(int value) {
        sensingIndex = value;
    }

    public void setFeelingIndex(int value) {
        feelingIndex = value;
    }

}

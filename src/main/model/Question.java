package model;

// Represents a question having a text String value questionText and a String category (one of intuition, sensing, feeling, judging)
public class Question {
    private String text;
    private String category;
    
    // REQUIRES: text != null && text.length() > 0, and category != null 
    // EFFECTS: text of question is set to questionText
    public Question(String text, String category) {
    }

// EFFECTS: returns text, null if empty
    public String getText() {
        return "";

    }

// EFFECTS: returns category
    public String getCategory() {
        return "";

    }
}

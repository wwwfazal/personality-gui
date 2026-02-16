package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestQuestion {
    Question q1;
    Question q2;
    
    @BeforeEach
    void runBefore() {
        q1 = new Question("I always make a plan", "Thinking");
        q2 = new Question("I can think of multiple possibilities", "Intuition");

    }

    @Test
    void testConstructor() {
        assertEquals("I always make a plan", q1.getText());
        assertEquals("I can think of multiple possibilities", q2.getText());
        assertEquals("Thinking", q1.getCategory());
        assertEquals("Intuition", q2.getCategory());
    }

}

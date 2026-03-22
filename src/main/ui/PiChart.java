package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import model.PersonalityTest;

// A visual representation of the data of a user's personality profile,
// with components for intuition, thinking, sensing, and feeling breakdowns
// and a graphic for the dominant function. 
public class PiChart extends JPanel {
    private PersonalityTest personalityTest;

    public PiChart(PersonalityTest personalityTest) {
        this.personalityTest = personalityTest;
        setPreferredSize(new Dimension(400, 300));
    }

    public void setTest(PersonalityTest personalityTest) {
        this.personalityTest = personalityTest;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (personalityTest == null) {
            return;
        }

        int intuition = personalityTest.getIntuitionPercentage();
        int thinking = personalityTest.getThinkingPercentage();
        int sensing = personalityTest.getThinkingPercentage();
        int feeling = personalityTest.getFeelingPercentage();

        int[] values = {intuition, thinking, sensing, feeling};
        Color[] colours = {Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK};
        String[] labels = {"Intuition", "Thinking", "Sensing", "Feeling"};

        int startPi = 0;
    }

}

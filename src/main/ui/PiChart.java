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
        setBackground(Color.WHITE);
    }

    public void setTest(PersonalityTest personalityTest) {
        this.personalityTest = personalityTest;
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (personalityTest == null  || personalityTest.getTotalAnswers() == 0) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(100, 50, 200, 200);
            return;
        }

        int intuition = personalityTest.getIntuitionPercentage();
        int thinking = personalityTest.getThinkingPercentage();
        int sensing = personalityTest.getSensingPercentage();
        int feeling = personalityTest.getFeelingPercentage();

        int[] values = {intuition, thinking, sensing, feeling};
        Color[] colours = {Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK};
        String[] labels = {"Intuition", "Thinking", "Sensing", "Feeling"};

        int startPi = 0;

        for (int i = 0; i < values.length; i++) {
            int angle = (int) Math.round(values[i] * 3.6);
            g.setColor(colours[i]);
            g.fillArc(100, 50, 200, 200, startPi, angle);
            startPi += angle;
        }

        g.setColor(Color.GREEN);
        g.drawString("Intuition", 320, 80);
        g.setColor(Color.BLUE);
        g.drawString("Thinking", 320, 100);
        g.setColor(Color.ORANGE);
        g.drawString("Sensing", 320, 120);
        g.setColor(Color.PINK);
        g.drawString("Feeling", 320, 140);

    }

}

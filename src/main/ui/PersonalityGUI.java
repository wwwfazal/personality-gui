package ui;

import javax.swing.*;

import model.PersonalityTest;
import model.Question;

import java.awt.FlowLayout;

public class PersonalityGUI extends JFrame {
    PersonalityTest personalityTest;

    public PersonalityGUI() {
        personalityTest = new PersonalityTest();


        setTitle("Personality Test Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton test = new JButton("Take personality test");
        JButton results = new JButton("View your results");

        panel.add(test);
        panel.add(results);
        panel.add(save);
        panel.add(load);

        add(panel);
        
        setVisible(true);

        JTextArea area = new JTextArea(10, 50);
        area.setEditable(false);

        panel.add(area);

        test.addActionListener(e -> {
            for (Question q : personalityTest.getQuestions()) {
                int response = JOptionPane.showConfirmDialog(this, q.getText(), "Answer y or n", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    personalityTest.trackAnswer(q);
                }
            }

            personalityTest.calculatePercentage();
            JOptionPane.showMessageDialog(this, "Your test has been updated.");
        });


        results.addActionListener(e -> {
            String result = "Dominant Function: " + personalityTest.getDominantFunction() + "\n" +
            "Intuition: " + personalityTest.getIntuitionPercentage() + "%\n" +
            "Thinking: " + personalityTest.getThinkingPercentage() + "%\n" +
            "Sensing: " + personalityTest.getSensingPercentage() + "%\n" +
            "Feeling: " + personalityTest.getFeelingPercentage() + "%\n";

            area.setText(result);

        });



    }

    

}

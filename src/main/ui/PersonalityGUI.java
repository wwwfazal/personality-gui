package ui;

import javax.swing.*;

import model.PersonalityTest;
import model.Question;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a GUI (Graphical User Interface) for the personality test. Includes a button to take the test,
// an area to view results, and respective save and load buttons using JFrame. 
public class PersonalityGUI extends JFrame {
    PersonalityTest personalityTest;
    private static final String JSON_STORE = "./data/personality.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private PiChart piChart;

    // EFFECTS: Starts the personality GUI and creates the visual Pi Chart
    public PersonalityGUI() {
        personalityTest = new PersonalityTest();
        addQuestions();

        setTitle("Personality Test Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton test = new JButton("Take personality test");
        JButton results = new JButton("View your results");


        piChart = new PiChart(personalityTest);
        panelAdder(panel, piChart, test, results, save, load);

        add(panel);
        
        setVisible(true);

        JTextArea area = new JTextArea(10, 50);
        area.setEditable(false);

        panel.add(area);

        addActionListener(test);

        resultsActionListener(results, area);

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        saveActionListener(save);

        loadActionListener(load, area);
    }

    public void addQuestions() {
        personalityTest.addQuestion(new Question("I enjoy exploring new scenarios and ideas", "Intuition"));
        personalityTest.addQuestion(new Question("I focus on sensory and tangible details", "Sensing"));
        personalityTest.addQuestion(new Question("I make decisions logically and efficiently", "Thinking"));
        personalityTest.addQuestion(new Question("I value emotions and interpersonal relationships to make decisions",
                                     "Feeling"));

    }

    public void saveActionListener(JButton save) {
        save.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(personalityTest);
                jsonWriter.close();
                JOptionPane.showMessageDialog(this, "Saved to " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Your file was not saved successfully.");
            }
        });
    }

    public void loadActionListener(JButton load, JTextArea area) {
        load.addActionListener(e -> {
            try {
                personalityTest = jsonReader.read();
                JOptionPane.showMessageDialog(this, "Loaded from " + JSON_STORE);
                area.setText(
                                    "Dominant Function: " + personalityTest.getDominantFunction() + "\n" 
                                    + "Intuition: " + personalityTest.getIntuitionPercentage() + "%\n" 
                                    + "Thinking: " + personalityTest.getThinkingPercentage() + "%\n" 
                                    + "Sensing: " + personalityTest.getSensingPercentage() + "%\n" 
                                    + "Feeling: " + personalityTest.getFeelingPercentage() + "%\n" 
                                    + "Total questions answered: " + personalityTest.getTotalAnswers()
                );
                piChart.setTest(personalityTest);
                piChart.repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Your file was not loaded successfully.");
            }

            piChart.setTest(personalityTest);
        });
    }

    public void panelAdder(JPanel panel, PiChart piChart, JButton test, JButton results, JButton save, JButton load) {
        panel.setLayout(new FlowLayout());
        panel.add(piChart);
        panel.add(test);
        panel.add(results);
        panel.add(save);
        panel.add(load);
    }

    public void resultsActionListener(JButton results, JTextArea area) {
        results.addActionListener(e -> {
            String result = "Dominant Function: " + personalityTest.getDominantFunction() + "\n" 
                                            + "Intuition: " + personalityTest.getIntuitionPercentage() + "%\n" 
                                            + "Thinking: " + personalityTest.getThinkingPercentage() + "%\n" 
                                            + "Sensing: " + personalityTest.getSensingPercentage() + "%\n" 
                                            + "Feeling: " + personalityTest.getFeelingPercentage() + "%\n"
                                            + "Total questions answered: " + personalityTest.getTotalAnswers();

            area.setText(result);

        });
    }

    public void addActionListener(JButton test) {
        test.addActionListener(e -> {
            for (Question q : personalityTest.getQuestions()) {
                int response = JOptionPane.showConfirmDialog(this, q.getText(), "Answer yes or no", 
                                                JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    personalityTest.trackAnswer(q);
                }
            }

            personalityTest.calculatePercentage();
            JOptionPane.showMessageDialog(this, "Your test has been updated.");
            piChart.setTest(personalityTest);
        });
    }

}

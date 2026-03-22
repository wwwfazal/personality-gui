package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;


// Represents a command interface application for taking a personality test.
// A user can create a user profile with a name, age, and ID, 
// take a personality test, view the results including dominant function and 
// percentages, view information about the questions on the test, and 
// save and load the personality test using a JSON file.
@ExcludeFromJacocoGeneratedReport
public class PersonalityTestApp {
    private PersonalityTest test;
    private Scanner input;
    private String name;
    private int age;
    private String id;
    private static final String JSON_STORE = "./data/personality.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts and runs the personality test app
    public PersonalityTestApp() {
        {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonReader = new JsonReader(JSON_STORE);
            runApp();
        }
    }

    // MODIFIES: this
    // EFFECTS: puts user input in a menu
    private void runApp() {
        boolean keepRunning = true;
        String command = null;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("You quit!");
    }

    // MODIFIES: this
    // EFFECTS: initialises personality test
    private void init() {
        test = new PersonalityTest();
        input = new Scanner(System.in);
        // input.useDelimiter("\r?\n|\r");

        test.addQuestion(new Question("I enjoy exploring new scenarios and ideas", "Intuition"));
        test.addQuestion(new Question("I focus on sensory and tangible details", "Sensing"));
        test.addQuestion(new Question("I make decisions logically and efficiently", "Thinking"));
        test.addQuestion(new Question("I value emotions and interpersonal relationships to make decisions", "Feeling"));
    }

    // EFFECTS: menu display
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add user profile");
        System.out.println("\tp -> take the personality test");
        System.out.println("\tr -> view results");
        System.out.println("\tv -> view categories of all questions");
        System.out.println("\ts -> save personality test");
        System.out.println("\tl -> load personality test");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addUserProfile();
        } else if (command.equals("p")) {
            takePersonalityTest();
        } else if (command.equals("r")) {
            viewPersonalityResults();
        } else if (command.equals("v")) {
            listPersonalityQuestions();
        } else if (command.equals("s")) {
            savePersonalityTest();
        } else if (command.equals("l")) {
            loadPersonalityTest();
        } else {
            System.out.println("Sorry, that's not an option.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a user profile with name, age, ID to system
    private void addUserProfile() {
        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Enter your age: ");
        age = input.nextInt();
        input.nextLine();

        System.out.print("Enter your ID: ");
        id = input.nextLine();
        System.out.print("Profile created for " + name + ", age: " + age + ", ID: " + id);
    }

    // MODIFIES: this
    // EFFECTS: calculates percentage breakdown and dominant function
    private void takePersonalityTest() {
        System.out.println("\n*** Take the Personality Test ***");
        for (Question q : test.getQuestions()) {
            System.out.println("\n" + q.getText());
            System.out.println("Answer (y/n)");
            String answer = input.nextLine().toLowerCase();

            if (answer.equals("y")) {
                test.trackAnswer(q);
            }
        }
        test.calculatePercentage();
        System.out.println("The test has been completed.");
    }

    // EFFECTS: prints test results
    private void viewPersonalityResults() {
        System.out.println("\n*** Results ***");
        System.out.println("Dominant Function: " + test.getDominantFunction());
        System.out.println("Intuition: " + test.getIntuitionPercentage() + "%");
        System.out.println("Thinking: " + test.getThinkingPercentage() + "%");
        System.out.println("Sensing: " + test.getSensingPercentage() + "%");
        System.out.println("Feeling: " + test.getFeelingPercentage() + "%");
    }

    // EFFECTS: lists all questions in the test
    private void listPersonalityQuestions() {
        System.out.println("\n***All Questions ***");
        for (Question q : test.getQuestions()) {
            System.out.println("- " + q.getText() + " (" + q.getCategory() + ")");
        }
    }

    // EFFECTS: saves the workroom to file
    private void savePersonalityTest() {
        try {
            jsonWriter.open();
            jsonWriter.write(test);
            jsonWriter.close();
            System.out.println("Saved personality to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPersonalityTest() {
        try {
            test = jsonReader.read();

            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: runs the application
    public static void main(String[] args) {
        new PersonalityGUI();
        new PersonalityTestApp();
    }
}

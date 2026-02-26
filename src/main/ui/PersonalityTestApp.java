package ui;

import java.util.Scanner;
import model.*;

public class PersonalityTestApp {
    private PersonalityTest test;
    private Scanner input;
    private String name;
    private int age;
    private String id;

    // EFFECTS: starts and runs the personality test app
    public PersonalityTestApp() {
        {
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

    private void init() {
        test = new PersonalityTest();
        input = new Scanner(System.in);
     //   input.useDelimiter("\r?\n|\r");

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
        System.out.println("\tl -> list all questions");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            addUserProfile();
        } else if (command.equals("p")) {
            takePersonalityTest();
        } else if (command.equals("r")) {
            viewPersonalityResults();
        } else if (command.equals("l")) {
            listPersonalityQuestions();
        } else {
            System.out.println("Sorry, that's not an option.");
        }
    }

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

    public static void main(String[] args) {
        new PersonalityTestApp();
    }
}

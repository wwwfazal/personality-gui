# Projekt PersonalityType

## Description

This is a personality type tracking application in a psychology clinic. It lets you create a user with a name, age, and ID. Then you can take a multiple-choice personality test of an arbitrary amount of questions that will determine whether you are an *Intuition*, *Sensing*, *Thinking*, or *Feeling* dominant individual (you can keep answering random questions until you choose to end the test). During the test, you can choose to change an answer by pressing "undo". After you end test, you can see a chart with your percentages for each category. The program will also return your dominant function (e.g *Intuition*). You can then view the results of other users in the program by pressing "users", but not their name, age, and ID.  

The intended audience of this project are the **clients of the psychology clinic**. The test creates a profile for them with some simple, basic information for the psychologist to refer to. This project is of interest to me because it involves calculating the dominant function of the user, and creating unique questions and storing them in a list. It can also show people something new that they might not have already known about themselves.  

User Stories:
- As a user, I want to be able to add my name, ID, and age to my Personality Profile.
- As a user, I want to see my dominant function calculated from my personality test results.
- As a user, I want to see a chart of the percentage breakdowns of all four functions.
- As a user, I want to be able to view information about what each function means.
- As a user, during the application I want to have the option to save my personality results from the file  
- As a user, when I start the application I want to have the option to load my personality data from file

# Instructions for End User

- You can view the panel that displays the Xs that have already been added to the Y by looking at the pie chart to see the results of all the questions added to your personality profile.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the button Take the Personality Test.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the button View my Results.
- You can locate my visual component by taking the test and viewing the updated pie chart.
- You can save the state of my application by pressing the save button
- You can reload the state of my application by pressing the load button.

# Phase 4: Task 2
Sun Mar 29 15:51:28 PDT 2026
Added user's answer for category to personality profile: Intuition. User's total answers updated: 1
Sun Mar 29 15:51:29 PDT 2026
Added user's answer for category to personality profile: Thinking. User's total answers updated: 2
Sun Mar 29 15:51:30 PDT 2026
User's current function percentages calculated: (I/T/S/F) 50/50/0/0
Sun Mar 29 15:51:31 PDT 2026
User's dominant function calculated:Intuition
Sun Mar 29 15:51:33 PDT 2026
User's current function percentages calculated: (I/T/S/F) 50/50/0/0
Sun Mar 29 15:51:34 PDT 2026
User's dominant function calculated:Intuition

# Phase 4: Task 3
If I were to have more time to refactor this project, there are a few changes that I would like to have implemented. For one, the PersonalityTest class currently has too much functionality. It stores a list of defined Questions but also records the answers as indices and performs calculations on these indices. It would make the class more cohesive if I refactored the PersonalityTest class into two separate classes. One class would be for storing Questions and their respective categories of Intuition, Thinking, Feeling, and Sensing, and would likely be called PersonalityQuestions. The other class would be for recording user answers and performing calculations to get the function percentages and dominant function, and would likely be called PersonalityAnswers. This way, I could separate these two distinct functionalities and make the program easier to understand or change.

Rather than an index based calculation, I think I would also restructure the program to contain a map with a key for the category of the question and a value for the string of the question. This way, instead of calculating the percentages after every question I could update the entire personality profile at the very end of the test. If there were many questions in the test, this might be a more efficient method. This also provides the opportuntity to change previous answers as a possible extension to this project.

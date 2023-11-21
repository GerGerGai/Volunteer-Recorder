package ui;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import persistance.JsonReader;
import persistance.JsonWriter;

// Represents our club application
// Codes inspired by "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo"
public class ClubApp {

    private static final String JSON_STORE = "./data/Education.json";

    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Education education;

    Director director1;

    UniversityVolunteer volunteer1;

    KenyaStudent student1;



    // EFFECTS: constructs the club and runs the club application
    public ClubApp() throws FileNotFoundException {
        String command111 = null;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        Scanner inputLoad = new Scanner(System.in);
        System.out.println("\n Do you want to: ");
        System.out.println("\tg -> Open the Volunteers Manager.");
        System.out.println("\tc -> Work in the Console");

        command111 = inputLoad.next();
        command111 = command111.toLowerCase();

        if (command111.equals("g")) {
            new ClubAppGUI();
        } else {
            runClubApp();
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runClubApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        loadHistory();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        saveWork();

        System.out.println("\nThank you for using the app!");
    }

    // MODIFIES: this, education
    // EFFECTS: initialize the app system
    private void init() {
        education = new Education("Education Department");

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        director1 = new Director("Jack","math",10000,3);
        volunteer1 = new UniversityVolunteer("Emma","math",
                10003,3);
        student1 = new KenyaStudent(10000,"Mark",3);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat identifies you:");
        System.out.println("\td -> Director");
        System.out.println("\ts -> KenyaStudent");
        System.out.println("\tv -> UBC Volunteer");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            doDirector();
        } else if (command.equals("s")) {
            doStudent();
        } else if (command.equals("v")) {
            doVolunteer();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: load the history of the system
    private void loadHistory() {
        String command111 = null;
        Scanner inputLoad = new Scanner(System.in);
        System.out.println("\n Do you want to start from last time?");
        System.out.println("\ty -> Yes, please!");
        System.out.println("\tn -> Nope.");

        command111 = inputLoad.next();
        if (command111.equals("y")) {
            try {
                education = jsonReader.read();
                System.out.println("Loaded " + education.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    //EFFECTS: prompt the user to save the work.
    private void saveWork() {
        String command111 = null;
        Scanner inputSave = new Scanner(System.in);
        System.out.println("\n Do you want to save everything so far?");
        System.out.println("\ty -> Yes, please save it!");
        System.out.println("\tn -> Nope.");

        command111 = inputSave.next();
        if (command111.equals("y")) {
            saveEducation();
            System.out.println("Save successfully!");
        }
    }

    // EFFECTS: saves the education to file
    private void saveEducation() {
        try {
            jsonWriter.open();
            jsonWriter.write(education);
            jsonWriter.close();
            System.out.println("Saved " + education.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: start what students can do
    public void doStudent() {
        String command1 = null;
//        education.addQuestions(ac4);
//        education.addVolunteers(volunteer4);
//        education.addVolunteers(volunteer3);
//
//        student1.setAcademicConfusion(ac1);
//
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        System.out.println("\tn -> I'm a new Student for this club");
        System.out.println("\tv -> view a list of volunteers");
        System.out.println("\ta -> Update my Academic Confusion and update the new"
                + " one to the club's question list");

        command1 = input.next();
        command1 = command1.toLowerCase();

        if (command1.equals("v")) {
            ArrayList<Volunteer> volunteers = student1.viewVolunteer(education);
            viewVolunteers(volunteers);
        } else if (command1.equals("a")) {
            updateStudentConfusion();
            System.out.println("Your question has been added!");
            viewQuestionsNotAnswered(education.getQuestions());
        } else if (command1.equals("n")) {
            setupNewStudent();
            System.out.println("You have been added to our system!");
            System.out.println("We have added your academic question to the system!");
        } else {
            System.out.println("Invalid input ...");
        }

    }

    //EFFECTS: start the functions of director
    public void doDirector() {
        String command1 = null;

        ArrayList<Volunteer> volunteers = education.getVolunteerList();


        System.out.println("\nWhat do you want to do:");
        System.out.println("\ta -> know the number of volunteers");
        System.out.println("\tb -> know the number of students");
        System.out.println("\tc -> add a UBC volunteer");

        command1 = input.next();
        command1 = command1.toLowerCase();

        if (command1.equals("a")) {
            int num = director1.numVolunteers(education);
            System.out.println("Number of volunteers: " + num);
        } else if (command1.equals("b")) {
            int num1 = director1.numKenyaStudents(education);
            System.out.println("Number of Students: " + num1);
        } else if (command1.equals("c")) {
//            director1.addVolunteer(education,volunteer1);
//            viewVolunteers(volunteers);
            addVolunteers();
            System.out.println("The new volunteer has been added!");
            viewVolunteers(volunteers);
        }
    }

    //EFFECTS: start the functions of volunteers
    private void doVolunteer() {
        String command1 = null;


        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        ArrayList<AcademicConfusion> questions = education.getQuestions();




        System.out.println("\nWhat do you want to do:");
        System.out.println("\tv -> view a list of all Kenya Students");
        System.out.println("\tn -> work on a new question");
        System.out.println("\tr -> remove a question I resolved");

        command1 = input.next();
        command1 = command1.toLowerCase();

        if (command1.equals("v")) {
            viewStudents(students);
        } else if (command1.equals("r")) {
            removeQuestionAnswered(beingAnswered);
        } else if (command1.equals("n")) {
            workNewQuestion(questions, beingAnswered);
        } else {
            System.out.println("Invalid input...");
        }

    }

    // EFFECTS: print out the list of Academic Confusions not being answered
    private void viewQuestionsNotAnswered(ArrayList<AcademicConfusion> confusions) {
        System.out.println("Confusions we have so far not being answered: ");
        for (AcademicConfusion confusion : confusions) {
            System.out.println("id: " + confusion.getId());
            System.out.println("subject: " + confusion.getSubject());
            System.out.println("description: " + confusion.getDescription());
            System.out.println(" ");
        }
    }

    // EFFECTS: print out the list of Academic Confusions being answered
    private void viewQuestionsAnswered(ArrayList<AcademicConfusion> confusions) {
        System.out.println("Confusions we have so far being answered: ");
        for (AcademicConfusion confusion : confusions) {
            System.out.println("id: " + confusion.getId());
            System.out.println("subject: " + confusion.getSubject());
            System.out.println("description: " + confusion.getDescription());
            System.out.println(" ");
        }
    }

    // EFFECTS: print out the list of Volunteers we have
    private void viewVolunteers(ArrayList<Volunteer> volunteers) {
        System.out.println("Volunteers we have so far: ");
        for (Volunteer volunteer : volunteers) {
            System.out.println("Name: " + volunteer.getName());
            System.out.println("id: " + volunteer.getId());
            System.out.println("major: " + volunteer.getMajor());
            System.out.println("year: " + volunteer.getYear());
            System.out.println(" ");
        }
    }

    //EFFECTS: view a list of students
    private void viewStudents(ArrayList<KenyaStudent> students) {
        System.out.println("Kenya Students we have so far: ");
        for (KenyaStudent student : students) {
            System.out.println("Name: " + student.getName());
            System.out.println("id: " + student.getId());
            System.out.println("Grade: " + student.getGrade());
            AcademicConfusion confusion = student.getAcademicConfusion();

            if (confusion != null) {
                System.out.println("The student's academic confusion: ");
                System.out.println("id: " + confusion.getId());
                System.out.println("subject: " + confusion.getSubject());
                System.out.println("description: " + confusion.getDescription());
            } else {
                System.out.println("The student currently has no questions");
            }

            System.out.println(" ");
        }
    }

    //MODIFIES: Kenya student
    //EFFECTS: update the student's question
    private AcademicConfusion updateConfusion(int id) {
        String command1 = null;
        Scanner inputThis = new Scanner(System.in);
        AcademicConfusion newConfusion = new AcademicConfusion(id);
        System.out.println("\n Please select one of the four subjects:");
        System.out.println("\tb -> Biology");
        System.out.println("\tm -> Math");
        System.out.println("\tp -> Physics");
        System.out.println("\tc -> Chemistry");

        command1 = inputThis.next();
        command1 = command1.toLowerCase();

        setSubject(command1,newConfusion);

        inputThis = new Scanner(System.in);
        System.out.println("\n Please type your problem description: ");
        String description = inputThis.nextLine();

        newConfusion.setDescription(description);

        return newConfusion;
    }

    //MODIFIES: Academic Confusion
    //EFFECTS: set the subject of the ac
    private void setSubject(String command1, AcademicConfusion ac) {
        if (command1.equals("b")) {
            ac.setSubject("biology");
        } else if (command1.equals("m")) {
            ac.setSubject("math");
        } else if (command1.equals("p")) {
            ac.setSubject("physics");
        } else if (command1.equals("c")) {
            ac.setSubject("chemistry");
        } else {
            System.out.println("Invalid input...");
        }
    }

    //EFFECTS: find the studnet with the same id in the system
    private KenyaStudent checkStudent(Education education, int id) {
        ArrayList<KenyaStudent> students = education.getStudentList();
        for (KenyaStudent student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    //MODIFIES: this
    //EFFECTS: remove old questions from the system
    private void removeOldQuestion(Education education, AcademicConfusion ac) {

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> confusions1 = education.getQuestionsBeingAnswered();

        if (confusions.contains(ac)) {
            confusions.remove(ac);
        } else {
            confusions1.remove(ac);
        }

    }

    //MODIFIES: this, Kenya Student
    //EFFECTS: update student's confusion
    private void updateStudentConfusion() {
        String command33 = null;
        Scanner inputThis = new Scanner(System.in);
        System.out.println("\n What is your id? ");
        command33 = inputThis.nextLine();
        int id = Integer.parseInt(command33);
        AcademicConfusion newAc = updateConfusion(id);
        KenyaStudent student = checkStudent(education,id);
        removeOldQuestion(education, student.getAcademicConfusion());
        student.setAcademicConfusion(newAc);
        student.addToAcademicList(education);
    }

    //REQUIRES: the student was not in our education
    //MODIFIES: education
    //EFFECTS: add a new student to education department, together with
    //          his/her academic confusion.
    private void setupNewStudent() {
        KenyaStudent newStudent;

        String command33 = null;
        Scanner inputThis = new Scanner(System.in);

        System.out.println("\n What is your id? ");
        command33 = inputThis.nextLine();
        int id = Integer.parseInt(command33);

        inputThis = new Scanner(System.in);
        String command44 = null;

        System.out.println("\n What is your name? ");
        command44 = inputThis.nextLine();
        String name = command44;

        inputThis = new Scanner(System.in);
        String command55 = null;

        System.out.println("\n What is your grade?");
        command55 = inputThis.nextLine();
        int grade = Integer.parseInt(command55);

        System.out.println("Now we are going to set you academic confusion");

        AcademicConfusion firstConfusion = updateConfusion(id);
        newStudent = new KenyaStudent(id,name,grade);
        newStudent.setAcademicConfusion(firstConfusion);
        director1.addStudent(education,newStudent);
        newStudent.addToAcademicList(education);
    }

    //MODIFIES: education, beingAnswered
    //EFFECTS: remove a question resolved from the questions being answered list
    private void removeQuestionAnswered(ArrayList<AcademicConfusion> beingAnswered) {
        String command111 = null;
        Scanner inputThis = new Scanner(System.in);
        System.out.println("Here are the questions being answered: ");
        viewQuestionsAnswered(beingAnswered);
        System.out.println(" ");
        System.out.println("What's the position of the question you are removing?");
        System.out.println("For example, if it's the second question, type 2 here.");
        command111 = inputThis.next();
        int i = Integer.parseInt(command111) - 1;
        volunteer1.removeConfusion(education,beingAnswered.get(i));
        System.out.println("Question removed successfully!");
        viewQuestionsAnswered(beingAnswered);
    }

    //MODIFIES: education
    //EFFECTS: let the volunteer work on a new question
    private void workNewQuestion(ArrayList<AcademicConfusion> questions,
                                 ArrayList<AcademicConfusion> beingAnswered) {
        String command000 = null;
        Scanner inputThis = new Scanner(System.in);

        viewQuestionsNotAnswered(questions);
        System.out.println(" ");
        System.out.println("What's the position of the question you want to answer?");
        System.out.println("For example, if it's the second question, type 2 here");

        command000 = inputThis.nextLine();
        int i = Integer.parseInt(command000) - 1;

        boolean success = volunteer1.workingOnConfusion(education,questions.get(i));

        if (success) {
            System.out.println("The question has been removed from the original list!");
            System.out.println(" ");
            viewQuestionsNotAnswered(questions);
            System.out.println(" ");
            viewQuestionsAnswered(beingAnswered);
        } else {
            System.out.println("Sorry! Someone was working on that question! "
                    + "You may choose another one!");
        }
    }

    //REQUIRES: the volunteer was not in our system
    //MODIFIES: education
    //EFFECTS: add a new volunteer to the system
    private void addVolunteers() {
        String command1 = null;
        Scanner input1 = new Scanner(System.in);
        System.out.println("What's the id of the volunteer?: ");
        command1 = input1.nextLine();
        int id = Integer.parseInt(command1);

        String command2 = null;
        Scanner input2 = new Scanner(System.in);
        System.out.println("What's the name of the volunteer?: ");
        command2 = input2.nextLine();
        String name = command2;

        String command3 = null;
        Scanner input3 = new Scanner(System.in);
        System.out.println("What's the major of the volunteer?: ");
        command3 = input3.nextLine();
        String major = command3;

        String command4 = null;
        Scanner input4 = new Scanner(System.in);
        System.out.println("What's the year of the volunteer?: ");
        command4 = input4.nextLine();
        int year = Integer.parseInt(command4);

        UniversityVolunteer newVolunteer = new UniversityVolunteer(name,major,id,year);
        director1.addVolunteer(education,newVolunteer);
    }

}

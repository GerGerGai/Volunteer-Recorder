package ui;

import model.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ClubApp {

    Education education;

    private Scanner input;

    Director director1;
    Director director2;
    Director director3;

    UniversityVolunteer volunteer1;
    UniversityVolunteer volunteer2;
    UniversityVolunteer volunteer3;
    UniversityVolunteer volunteer4;

    KenyaStudent student1;
    KenyaStudent student2;
    KenyaStudent student3;
    KenyaStudent student4;

    AcademicConfusion ac1;
    AcademicConfusion ac2;
    AcademicConfusion ac3;
    AcademicConfusion ac4;

    // EFFECTS: runs the club application
    public ClubApp() {
        runClubApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runClubApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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

        System.out.println("\nThank you for using the app!");
    }

    private void init() {
        education = new Education();

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        director1 = new Director("Jack","math",10000,3);
        director2 = new Director("Mark","math",10001,2);
        director3 = new Director("Tom","physice",10002,2);

        volunteer1 = new UniversityVolunteer("Emma","math",
                10003,3);
        volunteer2 = new UniversityVolunteer("Mia","biology",
                10004,2);
        volunteer3 = new UniversityVolunteer("Jerry","math",
                10005,4);
        volunteer4 = new UniversityVolunteer("Hao","chemistry",
                10006,2);

        student1 = new KenyaStudent(10000,"Kahan",3);
        student2 = new KenyaStudent(10001,"Gyn",2);
        student3 = new KenyaStudent(10002,"Ismael",4);
        student4 = new KenyaStudent(10003,"Molan",2);

        ac1 = new AcademicConfusion(10000);
        ac2 = new AcademicConfusion(10001);
        ac3 = new AcademicConfusion(10002);
        ac4 = new AcademicConfusion(10003);
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

    public void doStudent() {
        String command1 = null;
        education.addQuestions(ac4);
        education.addVolunteers(volunteer4);
        education.addVolunteers(volunteer3);

        student1.setAcademicConfusion(ac1);

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        System.out.println("Confusions we have so far: " + confusions);

        System.out.println("\nWhat do you want to do:");
        System.out.println("\tv -> view a list of volunteers");
        System.out.println("\ta -> Add my confusion to the questions list");

        command1 = input.next();
        command1 = command1.toLowerCase();

        if (command1.equals("v")) {
            ArrayList<Volunteer> volunteers =
                    student1.viewVolunteer(education);

            System.out.println("Our volunteers: " + volunteers);
        } else {
            student1.addToAcademicList(education);
            System.out.println("Your Question has been added!: " + confusions);
        }

    }

    public void doDirector() {
        String command1 = null;
        education.addVolunteers(volunteer4);

        education.addStudents(student1);

        ArrayList<KenyaStudent> studnets = education.getStudentList();
        ArrayList<Volunteer> volunteers = education.getVolunteerList();

        System.out.println("Current Student list: ");
        viewVolunteers(volunteers);

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
        } else {
            director1.addVolunteer(education,volunteer1);
            System.out.println("New volunteer has been added!: " + volunteers);
        }
    }

    private void doVolunteer() {
        String command1 = null;

        education.addStudents(student1);
        education.addStudents(student2);

        education.addQuestionsBeingAnswered(ac1);
        education.addQuestionsBeingAnswered(ac2);

        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        System.out.println("Students we have so far:" + students);
        System.out.println("Questions being answered: " + beingAnswered);

        System.out.println("\nWhat do you want to do:");
        System.out.println("\ta -> know the number of Kenya students");
        System.out.println("\tb -> remove a question");

        command1 = input.next();
        command1 = command1.toLowerCase();

        if (command1.equals("a")) {
            int num = students.size();
            System.out.println("Number of volunteers: " + num);
        } else if (command1.equals("b")) {
            volunteer1.removeConfusion(education,ac1);
            System.out.println("The question has been removed "
                    + beingAnswered);
        }

    }


    private void viewVolunteers(ArrayList<Volunteer> volunteers) {
        System.out.println("Current Volunteer List: ");
        for (Volunteer volunteer : volunteers) {
            String result = "Name:" + volunteer.getName() + " id:"
                    + volunteer.getId() + " Year:" + volunteer.getYear()
                    + " Major:" + volunteer.getMajor();

            System.out.println(result);
        }
    }
}

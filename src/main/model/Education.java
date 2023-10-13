package model;

import java.util.ArrayList;

// represents all the information we have in our education department
public class Education {
    private ArrayList<Volunteer> volunteerList;
    private ArrayList<KenyaStudent> studentList;
    private ArrayList<AcademicConfusion> questions;
    private ArrayList<AcademicConfusion> questionsBeingAnswered;

    // EFFECTS: creates an education department object
    //          with the four lists needed.
    public Education() {
        volunteerList = new ArrayList<>();
        studentList = new ArrayList<>();
        questions = new ArrayList<>();
        questionsBeingAnswered = new ArrayList<>();

    }

    // EFFECTS: get all the volunteers we have so far.
    public ArrayList<Volunteer> getVolunteerList() {
        return volunteerList;
    }

    // EFFECTS: get all the Kenya students we have so far.
    public ArrayList<KenyaStudent> getStudentList() {
        return studentList;
    }

    // EFFECTS: get all the academic questions we have so far.
    public ArrayList<AcademicConfusion> getQuestions() {
        return questions;
    }

    // EFFECTS: get all the questions being answered so far.
    public ArrayList<AcademicConfusion> getQuestionsBeingAnswered() {
        return questionsBeingAnswered;
    }

    //MODIFIES: this
    //EFFECTS: add a volunteer to the volunteer list;
    //          same volunteer can only be added once.
    public void addVolunteers(Volunteer volunteer) {
        if (!volunteerList.contains(volunteer)) {
            volunteerList.add(volunteer);
        }
    }

    //REQUIRES: the volunteer must be in the volunteer list
    //MODIFIES: this
    //EFFECTS: remove the volunteer from the volunteer list
    public void removeVolunteers(Volunteer volunteer) {
        volunteerList.remove(volunteer);
    }

    //MODIFIES: this
    //EFFECTS: add a Kenya student to the student list; same student can
    //          only be added once.
    public void addStudents(KenyaStudent student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
        }
    }

    //REQUIRES: the student must be in the student list
    //MODIFIES: this
    //EFFECTS: remove the student from the student list
    public void removeStudents(KenyaStudent student) {
        studentList.remove(student);
    }

    //MODIFIES: this
    //EFFECTS: add an Academic Confusion to the questions list;
    //          same question can only be added once.
    public void addQuestions(AcademicConfusion confusion) {
        if (!questions.contains(confusion)) {
            questions.add(confusion);
        }
    }

    //REQUIRES: the question must be in the questions list
    //MODIFIES: this
    //EFFECTS: remove the question from the questions list
    public void removeQuestions(AcademicConfusion confusion) {
        questions.remove(confusion);
    }

    //MODIFIES: this
    //EFFECTS: add an Academic Confusion to the QuestionsBeingAnswered list;
    //          same question can only be added once.
    public void addQuestionsBeingAnswered(AcademicConfusion confusion) {
        if (!questionsBeingAnswered.contains(confusion)) {
            questionsBeingAnswered.add(confusion);
        }
    }

    //REQUIRES: the question must be in the list already
    //MODIFIES: this
    //EFFECTS: remove the question from the QuestionsBeingAnswered list
    public void removeQuestionsBeingAnswered(AcademicConfusion confusion) {
        questionsBeingAnswered.remove(confusion);
    }
}

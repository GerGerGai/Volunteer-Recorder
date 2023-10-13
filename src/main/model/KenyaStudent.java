package model;

import java.util.ArrayList;

// Represents all the information of a Kenyan student.
public class KenyaStudent {
    private int id; // a 5-digit number to identify each student.
    private String name;
    private int grade; // Grade 1 - Grade 4.
    private AcademicConfusion academicConfusion;

    // REQUIRES: id must be 5-digit, grade must be an integer from [1,4].
    // EFFECTS: construct a KenyaStudent object with given id, name and grade.
    public KenyaStudent(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.academicConfusion = null;
    }

    // REQUIRES: each student can only have one academic confusion.
    // MODIFIES: this.
    // EFFECTS: set the student's academic confusion.
    //          can also be used to update the academic confusion.
    public void setAcademicConfusion(AcademicConfusion academicConfusion) {
        this.academicConfusion = academicConfusion;
    }


    // EFFECTS: get the student's name
    public String getName() {

        return name;
    }

    // EFFECTS: get the student's grade
    public int getGrade() {
        return grade;
    }

    // EFFECTS: get the student's id
    public int getId() {
        return id;
    }

    // EFFECTS: get the student's academic confusion
    public AcademicConfusion getAcademicConfusion() {
        return academicConfusion;
    }


    // MODIFIES: this
    // EFFECTS: update the student's grade level
    public void updateGrade(int grade) {
        this.grade = grade;
    }

    // REQUIRES: student's academic confusion cannot be null
    // MODIFIES: education
    // EFFECTS: add the current academic confusion to the questions list
    //          in our Education department; the same question can only be
    //          added once.
    public void addToAcademicList(Education education) {
        education.addQuestions(academicConfusion);
    }


    // EFFECTS: view the list of all volunteers stored in Education department
    public ArrayList<Volunteer> viewVolunteer(Education education) {
        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        return volunteers;
    }



}

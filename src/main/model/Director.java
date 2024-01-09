package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// represents all the information we have about a club director
public class Director extends Volunteer {

    public Director(String name, String major, int id, int year) {
        this.name = name;
        this.major = major;
        this.id = id;
        this.year = year;
    }

    //EFFECTS: return the number of volunteers in our system
    public int numVolunteers(Education education) {
        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        return volunteers.size();
    }

    //EFFECTS: return the number of Kenyan Students in our system
    public int numKenyaStudents(Education education) {
        ArrayList<KenyaStudent> students = education.getStudentList();
        return students.size();
    }

    //MODIFIES: education
    //EFFECTS: add volunteer to our system,
    //          each volunteer can only be added once
    public void addVolunteer(Education education, Volunteer volunteer) {
        education.addVolunteers(volunteer);
    }

    //MODIFIES: education
    //EFFECTS: add Kenyan student to our system,
    //          each student can only be added once
    public void addStudent(Education education, KenyaStudent student) {
        education.addStudents(student);
    }

    //REQUIRES: the volunteer is already in our system
    //MODIFIES: education
    //EFFECTS: remove volunteer from our system
    public void removeVolunteer(Education education, Volunteer volunteer) {
        education.removeVolunteers(volunteer);
    }

    //REQUIRES: the student is already in our system
    //MODIFIES: education
    //EFFECTS: remove student from our system
    public void removeStudent(Education education, KenyaStudent student) {
        education.removeStudents(student);
    }

    //MODIFIES: education,KenyaStudent
    //EFFECTS: set a Kenya student's academic confusion to null if
    //          it has been answered
    public void checkQuestionsAnswered(Education education) {
        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        for (KenyaStudent student : students) {
            AcademicConfusion confusion = student.getAcademicConfusion();
            if (confusion != null) {
                if (!confusions.contains(confusion)
                        & !beingAnswered.contains(confusion)) {
                    student.setAcademicConfusion(null,null);
                }
            }
        }
    }





}

package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collection;

// Abstract class of a volunteer
public abstract class Volunteer implements Writable {

    String name;
    String major;
    int year;
    int id; // 5-digit number to identify each volunteer

    //EFFECTS: get the name of the volunteer
    public String getName() {
        return name;
    }

    //EFFECTS: get the major of the volunteer
    public String getMajor() {
        return major;
    }

    //EFFECTS: get the year of the volunteer
    public int getYear() {
        return year;
    }

    //EFFECTS: get the id of the volunteer
    public int getId() {
        return id;
    }

    //REQUIRES: newyear >= 1
    //MODIFIES: this
    //EFFECTS: update the volunteer's year level
    public void updateYear(int newYear) {
        this.year = newYear;
    }

    //EFFECTS: view the list of Kenyan students we have
    public ArrayList<KenyaStudent> viewListOfStudents(Education education) {
        return education.getStudentList();
    }

    //EFFECTS: view the list of academic confusions we have
    public ArrayList<AcademicConfusion> viewConfusions(Education education) {
        return education.getQuestions();
    }

    //REQUIRES: the question must come from the questions list
    //MODIFIES: education
    //EFFECTS:
    //         if the question is already in the questionsBeingAnswered list,
    //              only remove it from the questions list and return false
    //         if the question is not in the questionsBeingAnswered list,
    //         remove the academic confusion from the questions list, and
    //            add it to the questionsBeingAnswered list, return true;
    public boolean workingOnConfusion(Education education,
                                      AcademicConfusion ac) {
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        if (beingAnswered.contains(ac)) {
            confusions.remove(ac);
            return false;
        } else {
            confusions.remove(ac);
            beingAnswered.add(ac);
            return true;
        }
    }

    //REQUIRES: the Academic Confusion is
    //          already in the questionsBeingAnswered list
    //MODIFIES: education
    //EFFECTS: remove the academic confusion from the system
    public void removeConfusion(Education education, AcademicConfusion ac) {
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        beingAnswered.remove(ac);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("major", major);
        json.put("year", year);
        return json;
    }


}

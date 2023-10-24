package model;

import org.json.JSONObject;
import persistance.Writable;

// Represents the academic confusion each student has.
public class AcademicConfusion implements Writable {
    private int id; //the id of the student asking this question
    private String subject;
    private String description;

    //EFFECTS: creates an Academic Confusion with no
    //          subject and description assigned
    public AcademicConfusion(int id) {
        this.id = id;
        subject = null;
        description = null;
    }

    //REQUIRES: subject must come from ["biology","math","physics","chemistry"]
    //MODIFIES: this
    //EFFECTS: set the subject of this academic confusion
    public void setSubject(String subject) {
        this.subject = subject;
    }

    //MODIFIES: this
    //EFFECTS: set the description of this academic confusion
    public void setDescription(String description) {
        this.description
                = description;
    }

    //EFFECTS: get the subject of this academic confusion
    public String getSubject() {
        return subject;
    }

    //EFFECTS: get the description of this academic confusion
    public String getDescription() {
        return description;
    }

    //EFFECTS: get the id of the student asking this question
    public int getId() {
        return id;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("subject", subject);
        json.put("description", description);
        return json;
    }
}

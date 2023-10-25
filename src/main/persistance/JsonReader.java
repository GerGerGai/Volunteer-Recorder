package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads education from JSON data stored in file
// Codes inspired by "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo"
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Education from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Education read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEducation(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Education from JSON object and returns it
    private Education parseEducation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Education education = new Education(name);
        addVolunteers(education, jsonObject);
        addStudents(education,jsonObject);
        addQuestions(education,jsonObject);
        addBeingAnswered(education,jsonObject);
        return education;
    }

    // MODIFIES: education
    // EFFECTS: parses Volunteers from JSON object and adds them to education
    private void addVolunteers(Education education, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("volunteerList");
        for (Object json : jsonArray) {
            JSONObject nextVolunteer = (JSONObject) json;
            addVolunteer(education, nextVolunteer);
        }
    }

    // MODIFIES: education
    // EFFECTS: parses volunteer from JSON object and adds it to education
    private void addVolunteer(Education education, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        int year = jsonObject.getInt("year");
        String major = jsonObject.getString("major");
        Volunteer volunteer = new UniversityVolunteer(name, major,id,year);
        education.addVolunteers(volunteer);
    }

    // MODIFIES: education
    // EFFECTS: parses Students from JSON object and adds them to education
    private void addStudents(Education education, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("studentList");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(education, nextStudent);
        }
    }

    // MODIFIES: education
    // EFFECTS: parses student from JSON object and adds it to education
    private void addStudent(Education education, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        int grade = jsonObject.getInt("grade");
        AcademicConfusion ac = academicConfusionForStudent((JSONObject) jsonObject.get("Academic Confusion"));
        KenyaStudent student = new KenyaStudent(id,name,grade);
        student.setAcademicConfusion(ac);
        education.addStudents(student);
    }

    //EFFECTS: add Academic confusion for student
    private AcademicConfusion academicConfusionForStudent(JSONObject jsonObject) {
        String subject = jsonObject.getString("subject");
        String description = jsonObject.getString("description");
        int id = jsonObject.getInt("id");

        AcademicConfusion ac = new AcademicConfusion(id);
        ac.setDescription(description);
        ac.setSubject(subject);

        return ac;
    }


    // MODIFIES: education
    // EFFECTS: parses Questions from JSON object and adds them to education
    private void addQuestions(Education education, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questionList");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(education, nextQuestion);
        }
    }

    // MODIFIES: education
    // EFFECTS: parses question from JSON object and adds it to education
    private void addQuestion(Education education, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String subject = jsonObject.getString("subject");
        String description = jsonObject.getString("description");
        AcademicConfusion ac = new AcademicConfusion(id);
        ac.setSubject(subject);
        ac.setDescription(description);
        education.addQuestions(ac);
    }

    // MODIFIES: education
    // EFFECTS: parses Questions Being Answered
    //          from JSON object and adds them to education
    private void addBeingAnswered(Education education, JSONObject jsonObject) {
        JSONArray jsonArray =
                jsonObject.getJSONArray("questionBeingAnsweredList");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestionBeingAnswered(education, nextQuestion);
        }
    }

    // MODIFIES: education
    // EFFECTS: parses question Being Answered
    //          from JSON object and adds it to education
    private void addQuestionBeingAnswered(Education education,
                                          JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String subject = jsonObject.getString("subject");
        String description = jsonObject.getString("description");
        AcademicConfusion ac = new AcademicConfusion(id);
        ac.setSubject(subject);
        ac.setDescription(description);
        education.addQuestionsBeingAnswered(ac);
    }





}

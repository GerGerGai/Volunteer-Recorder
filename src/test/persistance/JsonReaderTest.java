package persistance;

import model.AcademicConfusion;
import model.Director;
import model.Education;
import model.KenyaStudent;
import model.UniversityVolunteer;
import model.Volunteer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Codes inspired by "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo"
public class JsonReaderTest extends JsonTest{

    private Education education;

    private KenyaStudent student1;
    private KenyaStudent student2;
    private KenyaStudent student3;

    private UniversityVolunteer volunteer1;
    private UniversityVolunteer volunteer2;
    private UniversityVolunteer volunteer3;

    private AcademicConfusion ac1;
    private AcademicConfusion ac2;
    private AcademicConfusion ac3;

    @BeforeEach
    public void setup() {
        education = new Education("Education Department");

        volunteer1 = new UniversityVolunteer("Peter","math",
                10000,3);
        volunteer2 = new UniversityVolunteer("Jerry","biology",
                10001,2);
        volunteer3 = new UniversityVolunteer("Matt","physics",
                10002,3);

        student1 = new KenyaStudent(10000,"Kahan",3);
        student2 = new KenyaStudent(10001,"Mark",4);
        student3 = new KenyaStudent(10002,"Carylon",2);

        ac1 = new AcademicConfusion(10000);
        ac2 = new AcademicConfusion(10001);
        ac3 = new AcademicConfusion(10002);

        ac1.setSubject("biology");
        ac1.setDescription("aaa");
        student1.setAcademicConfusion(ac1);

        ac2.setSubject("math");
        ac2.setDescription("bbb");
        student2.setAcademicConfusion(ac2);

        ac3.setSubject("chemistry");
        ac3.setDescription("ccc");
        student3.setAcademicConfusion(ac3);

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Education education1 = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyEducation() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEducation.json");
        try {
            Education education1 = reader.read();
            assertEquals("Education Department", education1.getName());

            ArrayList<Volunteer> volunteers = education1.getVolunteerList();
            ArrayList<KenyaStudent> students = education1.getStudentList();
            ArrayList<AcademicConfusion> questions1 = education1.getQuestions();
            ArrayList<AcademicConfusion> questions2 =
                    education1.getQuestionsBeingAnswered();

            assertEquals(0, volunteers.size());
            assertEquals(0, students.size());
            assertEquals(0, questions1.size());
            assertEquals(0, questions2.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralEducation() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEducation.json");
        try {
            Education education1 = reader.read();
            assertEquals("Education Department", education1.getName());

            ArrayList<Volunteer> volunteers = education1.getVolunteerList();
            ArrayList<KenyaStudent> students = education1.getStudentList();
            ArrayList<AcademicConfusion> questions1 = education1.getQuestions();
            ArrayList<AcademicConfusion> questions2 =
                    education1.getQuestionsBeingAnswered();

            assertEquals(3, volunteers.size());
            assertEquals(3, students.size());
            assertEquals(2, questions1.size());
            assertEquals(1, questions2.size());

            checkVolunteer(volunteer1,volunteers.get(0));
            checkVolunteer(volunteer2,volunteers.get(1));
            checkVolunteer(volunteer3,volunteers.get(2));

            checkStudent(student1,students.get(0));
            checkStudent(student2,students.get(1));
            checkStudent(student3,students.get(2));

            checkQuestion(ac1,questions1.get(0));
            checkQuestion(ac2,questions1.get(1));

            checkQuestion(ac3,questions2.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

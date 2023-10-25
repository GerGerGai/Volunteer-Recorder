package persistance;

import model.AcademicConfusion;
import model.Director;
import model.Education;
import model.KenyaStudent;
import model.UniversityVolunteer;
import model.Volunteer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkVolunteer(Volunteer volunteer1, Volunteer volunteer2) {
        assertEquals(volunteer1.getName(),volunteer2.getName());
        assertEquals(volunteer1.getMajor(),volunteer2.getMajor());
        assertEquals(volunteer1.getYear(),volunteer2.getYear());
        assertEquals(volunteer1.getId(),volunteer2.getId());

    }

    protected void checkStudent(KenyaStudent student1, KenyaStudent student2) {
        assertEquals(student1.getId(), student2.getId());
        assertEquals(student1.getName(), student2.getName());
        assertEquals(student1.getGrade(), student2.getGrade());
        checkQuestion(student1.getAcademicConfusion(),
                        student2.getAcademicConfusion());
    }

    protected void checkQuestion(AcademicConfusion ac1,
                                 AcademicConfusion ac2) {

        assertEquals(ac1.getId(),ac2.getId());
        assertEquals(ac1.getSubject(),ac2.getSubject());
        assertEquals(ac1.getDescription(),ac2.getDescription());

    }
}

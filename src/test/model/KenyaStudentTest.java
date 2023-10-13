package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class KenyaStudentTest {

    KenyaStudent student1;
    KenyaStudent student2;
    KenyaStudent student3;

    Education education;

    AcademicConfusion ac1;
    AcademicConfusion ac2;
    AcademicConfusion ac3;

    UniversityVolunteer volunteer1;
    UniversityVolunteer volunteer2;

    @BeforeEach
    public void setup() {
        student1 = new KenyaStudent(10001,"Peter",3);
        student2 = new KenyaStudent(10002,"Jack",2);
        student3 = new KenyaStudent(10003,"Mark",4);

        education = new Education();

        ac1 = new AcademicConfusion(10001);
        ac2 = new AcademicConfusion(10002);
        ac3 = new AcademicConfusion(10003);

        volunteer1 = new UniversityVolunteer("Mark",
                "math",10000,3);
        volunteer2 = new UniversityVolunteer("Ben",
                "chemistry",10001,2);
    }

    @Test
    public void testSetAcademicConfusion() {
        assertNull(student1.getAcademicConfusion());

        student1.setAcademicConfusion(ac1);

        assertEquals(ac1,student1.getAcademicConfusion());
    }

    @Test
    public void testGetName() {
        assertEquals("Peter",student1.getName());
        assertEquals("Jack",student2.getName());
    }

    @Test
    public void testUpdateGrade() {
        assertEquals(2,student2.getGrade());

        student2.updateGrade(3);

        assertEquals(3,student2.getGrade());
    }

    @Test
    public void testGetId() {
        assertEquals(10003,student3.getId());
    }

    @Test
    public void testAddToList() {
        student1.setAcademicConfusion(ac1);
        student2.setAcademicConfusion(ac2);
        student3.setAcademicConfusion(ac3);

        student1.addToAcademicList(education);
        student2.addToAcademicList(education);
        student3.addToAcademicList(education);

        student1.addToAcademicList(education);

        ArrayList<AcademicConfusion> confusions = education.getQuestions();

        assertEquals(3,confusions.size());
        assertEquals(ac1,confusions.get(0));
        assertEquals(ac2,confusions.get(1));
        assertEquals(ac3,confusions.get(2));


    }

    @Test
    public void testViewVolunteers() {
        education.addVolunteers(volunteer1);
        ArrayList<Volunteer> viewVolunteer1 =
                student1.viewVolunteer(education);
        assertEquals(education.getVolunteerList(),viewVolunteer1);

        education.addVolunteers(volunteer2);
        ArrayList<Volunteer> viewVolunteer2 =
                student2.viewVolunteer(education);
        assertEquals(education.getVolunteerList(),viewVolunteer2);
    }
}

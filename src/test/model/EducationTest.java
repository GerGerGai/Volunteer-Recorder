package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class EducationTest {

    Education education;
    UniversityVolunteer volunteer1;
    UniversityVolunteer volunteer2;
    UniversityVolunteer volunteer3;
    KenyaStudent student1;
    KenyaStudent student2;
    KenyaStudent student3;
    AcademicConfusion ac1;
    AcademicConfusion ac2;
    AcademicConfusion ac3;

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

    }

    @Test
    public void testGetName() {
        assertEquals("Education Department",education.getName());
    }


    @Test
    public void testAddVolunteerNot() {
        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        assertEquals(0, volunteers.size());

        education.addVolunteers(volunteer1);

        assertEquals(1, volunteers.size());
        assertTrue(volunteers.contains(volunteer1));
    }



    @Test
    public void testAddVolunteerAlready() {

        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        assertEquals(0,volunteers.size());

        education.addVolunteers(volunteer1);

        assertEquals(1,volunteers.size());
        assertTrue(volunteers.contains(volunteer1));

        education.addVolunteers(volunteer1);

        assertTrue(volunteers.contains(volunteer1));
        assertEquals(1,volunteers.size());

    }

    @Test
    public void testAddThreeVolunteers() {

        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        assertEquals(0,volunteers.size());

        education.addVolunteers(volunteer1);
        education.addVolunteers(volunteer2);
        education.addVolunteers(volunteer3);

        assertEquals(3,volunteers.size());
        assertTrue(volunteers.contains(volunteer1));
        assertTrue(volunteers.contains(volunteer2));
        assertTrue(volunteers.contains(volunteer3));
        assertEquals(volunteer1,volunteers.get(0));
        assertEquals(volunteer2,volunteers.get(1));
        assertEquals(volunteer3,volunteers.get(2));



    }

    @Test
    public void testRemoveVolunteers() {

        ArrayList<Volunteer> volunteers = education.getVolunteerList();
        assertEquals(0,volunteers.size());

        education.addVolunteers(volunteer1);
        education.addVolunteers(volunteer2);
        education.addVolunteers(volunteer3);

        education.removeVolunteers(volunteer2);
        assertEquals(2,volunteers.size());
        assertTrue(volunteers.contains(volunteer1));
        assertFalse(volunteers.contains(volunteer2));
        assertTrue(volunteers.contains(volunteer3));
        assertEquals(volunteer1,volunteers.get(0));
        assertEquals(volunteer3,volunteers.get(1));

        education.removeVolunteers(volunteer1);
        assertEquals(1,volunteers.size());
        assertFalse(volunteers.contains(volunteer1));
        assertTrue(volunteers.contains(volunteer3));
        assertEquals(volunteer3,volunteers.get(0));


    }

    @Test
    public void testAddStudentNot() {
        ArrayList<KenyaStudent> students = education.getStudentList();
        assertEquals(0,students.size());

        education.addStudents(student1);

        assertEquals(1,students.size());
        assertTrue(students.contains(student1));
    }

    @Test
    public void testAddStudentAlready() {

        ArrayList<KenyaStudent> students = education.getStudentList();
        assertEquals(0,students.size());

        education.addStudents(student1);

        assertEquals(1,students.size());
        assertTrue(students.contains(student1));

        education.addVolunteers(volunteer1);

        assertTrue(students.contains(student1));
        assertEquals(1,students.size());

    }

    @Test
    public void testAddThreeStudents() {

        ArrayList<KenyaStudent> students = education.getStudentList();
        assertEquals(0,students.size());

        education.addStudents(student1);
        education.addStudents(student2);
        education.addStudents(student3);

        assertEquals(3,students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
        assertTrue(students.contains(student3));
        assertEquals(student1,students.get(0));
        assertEquals(student2,students.get(1));
        assertEquals(student3,students.get(2));



    }

    @Test
    public void testRemoveStudents() {

        ArrayList<KenyaStudent> students = education.getStudentList();
        assertEquals(0,students.size());

        education.addStudents(student1);
        education.addStudents(student2);
        education.addStudents(student3);

        education.removeStudents(student2);
        assertEquals(2,students.size());
        assertTrue(students.contains(student1));
        assertFalse(students.contains(student2));
        assertTrue(students.contains(student3));
        assertEquals(student1,students.get(0));
        assertEquals(student3,students.get(1));

        education.removeStudents(student1);
        assertEquals(1,students.size());
        assertFalse(students.contains(student1));
        assertTrue(students.contains(student3));
        assertEquals(student3,students.get(0));


    }

    @Test
    public void testAddConfusionNot() {
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        assertEquals(0, confusions.size());

        education.addQuestions(ac1);

        assertEquals(1, confusions.size());
        assertTrue(confusions.contains(ac1));
    }

    @Test
    public void testAddQuestionAlready() {

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        assertEquals(0, confusions.size());

        education.addQuestions(ac1);

        assertEquals(1, confusions.size());
        assertTrue(confusions.contains(ac1));

        education.addQuestions(ac1);

        assertTrue(confusions.contains(ac1));
        assertEquals(1,confusions.size());

    }

    @Test
    public void testAddThreeQuestions() {

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        assertEquals(0,confusions.size());

        education.addQuestions(ac1);
        education.addQuestions(ac2);
        education.addQuestions(ac3);

        assertEquals(3,confusions.size());
        assertTrue(confusions.contains(ac1));
        assertTrue(confusions.contains(ac2));
        assertTrue(confusions.contains(ac3));
        assertEquals(ac1,confusions.get(0));
        assertEquals(ac2,confusions.get(1));
        assertEquals(ac3,confusions.get(2));



    }

    @Test
    public void testRemoveQuestions() {

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        assertEquals(0, confusions.size());

        education.addQuestions(ac1);
        education.addQuestions(ac2);
        education.addQuestions(ac3);

        education.removeQuestions(ac2);
        assertEquals(2, confusions.size());
        assertTrue(confusions.contains(ac1));
        assertFalse(confusions.contains(ac2));
        assertTrue(confusions.contains(ac3));
        assertEquals(ac1, confusions.get(0));
        assertEquals(ac3, confusions.get(1));

        education.removeQuestions(ac1);
        assertEquals(1, confusions.size());
        assertFalse(confusions.contains(ac1));
        assertTrue(confusions.contains(ac3));
        assertEquals(ac3, confusions.get(0));
    }

    @Test
    public void testBeingAnsweredNot() {
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        assertEquals(0, beingAnswered.size());

        education.addQuestionsBeingAnswered(ac1);

        assertEquals(1, beingAnswered.size());
        assertTrue(beingAnswered.contains(ac1));
    }

    @Test
    public void testBeingAnsweredAlready() {

        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        assertEquals(0, beingAnswered.size());

        education.addQuestionsBeingAnswered(ac1);

        assertEquals(1, beingAnswered.size());
        assertTrue(beingAnswered.contains(ac1));

        education.addQuestionsBeingAnswered(ac1);

        assertTrue(beingAnswered.contains(ac1));
        assertEquals(1,beingAnswered.size());

    }

    @Test
    public void testThreeBeingAnswered() {

        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        assertEquals(0,beingAnswered.size());

        education.addQuestionsBeingAnswered(ac1);
        education.addQuestionsBeingAnswered(ac2);
        education.addQuestionsBeingAnswered(ac3);

        assertEquals(3,beingAnswered.size());
        assertTrue(beingAnswered.contains(ac1));
        assertTrue(beingAnswered.contains(ac2));
        assertTrue(beingAnswered.contains(ac3));
        assertEquals(ac1,beingAnswered.get(0));
        assertEquals(ac2,beingAnswered.get(1));
        assertEquals(ac3,beingAnswered.get(2));



    }

    @Test
    public void testRemoveBeingAnswered() {

        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        assertEquals(0,beingAnswered.size());

        education.addQuestionsBeingAnswered(ac1);
        education.addQuestionsBeingAnswered(ac2);
        education.addQuestionsBeingAnswered(ac3);

        education.removeQuestionsBeingAnswered(ac2);
        assertEquals(2, beingAnswered.size());
        assertTrue(beingAnswered.contains(ac1));
        assertFalse(beingAnswered.contains(ac2));
        assertTrue(beingAnswered.contains(ac3));
        assertEquals(ac1, beingAnswered.get(0));
        assertEquals(ac3, beingAnswered.get(1));

        education.removeQuestionsBeingAnswered(ac1);
        assertEquals(1, beingAnswered.size());
        assertFalse(beingAnswered.contains(ac1));
        assertTrue(beingAnswered.contains(ac3));
        assertEquals(ac3, beingAnswered.get(0));
    }

}

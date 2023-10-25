package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DirectorTest extends VolunteerTest{

    Director director1;
    Director director2;


    @BeforeEach
    public void setup() {

        volunteer1 = new UniversityVolunteer("Jack","math",
                10000,2);
        volunteer2 = new UniversityVolunteer("Tom","chemistry",
                10001,3);
        volunteer3 = new UniversityVolunteer("Amy","biology",
                10002,3);

        director1 = new Director("Charity","biology",
                10003,3);
        director2 = new Director("Gahan","math",
                10004,2);


        education = new Education("aa");

        student1 = new KenyaStudent(10000,"Peter",3);
        student2 = new KenyaStudent(10001,"Emma",2);

        ac1 = new AcademicConfusion(10000);
        ac2 = new AcademicConfusion(10001);
        ac3 = new AcademicConfusion(10003);
    }

    @Test
    public void testNumVolunteers() {

        assertEquals(0,director1.numVolunteers(education));

        education.addVolunteers(volunteer1);
        assertEquals(1,director2.numVolunteers(education));

        education.addVolunteers(volunteer2);
        assertEquals(2,director2.numVolunteers(education));

        education.addVolunteers(director2);
        assertEquals(3,director1.numVolunteers(education));

        education.removeVolunteers(volunteer1);
        assertEquals(2,director1.numVolunteers(education));

    }

    @Test
    public void testNumKenyaStudents() {

        assertEquals(0,director1.numKenyaStudents(education));

        education.addStudents(student1);
        assertEquals(1,director1.numKenyaStudents(education));

        education.addStudents(student2);
        assertEquals(2,director1.numKenyaStudents(education));

        education.removeStudents(student1);
        assertEquals(1,director2.numKenyaStudents(education));
    }

    @Test
    public void testAddVolunteer() {

        ArrayList<Volunteer> volunteers = education.getVolunteerList();

        assertEquals(0,volunteers.size());

        director1.addVolunteer(education,volunteer1);

        assertEquals(1,volunteers.size());
        assertTrue(volunteers.contains(volunteer1));

        director1.addVolunteer(education,volunteer2);

        assertEquals(2,volunteers.size());
        assertEquals(volunteer1,volunteers.get(0));
        assertEquals(volunteer2,volunteers.get(1));

    }

    @Test
    public void testRemoveVolunteer() {
        ArrayList<Volunteer> volunteers = education.getVolunteerList();

        assertEquals(0,volunteers.size());

        director1.addVolunteer(education,volunteer1);

        assertEquals(1,volunteers.size());
        assertTrue(volunteers.contains(volunteer1));

        director1.addVolunteer(education,volunteer2);

        assertEquals(2,volunteers.size());
        assertEquals(volunteer1,volunteers.get(0));
        assertEquals(volunteer2,volunteers.get(1));

        director2.removeVolunteer(education,volunteer1);
        assertEquals(1,volunteers.size());
        assertFalse(volunteers.contains(volunteer1));
        assertTrue(volunteers.contains(volunteer2));

    }

    @Test
    public void testAddStudent() {

        ArrayList<KenyaStudent> students = education.getStudentList();

        assertEquals(0,students.size());

        director1.addStudent(education,student1);

        assertEquals(1,students.size());
        assertTrue(students.contains(student1));

        director2.addStudent(education,student2);

        assertEquals(2,students.size());
        assertEquals(student1,students.get(0));
        assertEquals(student2,students.get(1));

    }

    @Test
    public void testRemoveStudents() {

        ArrayList<KenyaStudent> students = education.getStudentList();

        assertEquals(0,students.size());

        director1.addStudent(education,student1);

        assertEquals(1,students.size());
        assertTrue(students.contains(student1));

        director2.addStudent(education,student2);

        assertEquals(2,students.size());
        assertEquals(student1,students.get(0));
        assertEquals(student2,students.get(1));

        director2.removeStudent(education,student1);
        assertEquals(1,students.size());
        assertFalse(students.contains(student1));
        assertTrue(students.contains(student2));


    }

    @Test
    public void testAllStudentsNull() {

        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        students.add(student1);
        students.add(student2);
        AcademicConfusion confusion1 = student1.getAcademicConfusion();
        AcademicConfusion confusion2 = student2.getAcademicConfusion();
        education.addQuestions(ac1);
        education.addQuestions(ac2);

        assertNull(confusion1);
        assertNull(confusion2);

        director2.checkQuestionsAnswered(education);

        assertNull(confusion1);
        assertNull(confusion2);


    }

    @Test
    public void testAllNotChange1() {
        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        students.add(student1);
        students.add(student2);
        student1.setAcademicConfusion(ac1);
        student2.setAcademicConfusion(ac2);
        AcademicConfusion confusion1 = student1.getAcademicConfusion();
        AcademicConfusion confusion2 = student2.getAcademicConfusion();

        education.addQuestions(ac1);
        education.addQuestions(ac2);

        assertEquals(ac1,student1.getAcademicConfusion());
        assertEquals(ac2,student2.getAcademicConfusion());

        director2.checkQuestionsAnswered(education);

        assertEquals(ac1,student1.getAcademicConfusion());
        assertEquals(ac2,student2.getAcademicConfusion());
    }

    @Test
    public void testAllNotChange2() {
        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        students.add(student1);
        students.add(student2);
        student1.setAcademicConfusion(ac1);
        student2.setAcademicConfusion(ac2);
        AcademicConfusion confusion1 = student1.getAcademicConfusion();
        AcademicConfusion confusion2 = student2.getAcademicConfusion();

        education.addQuestionsBeingAnswered(ac1);
        education.addQuestionsBeingAnswered(ac2);

        assertEquals(ac1,student1.getAcademicConfusion());
        assertEquals(ac2,student2.getAcademicConfusion());

        director2.checkQuestionsAnswered(education);

        assertEquals(ac1,student1.getAcademicConfusion());
        assertEquals(ac2,student2.getAcademicConfusion());
    }

    @Test
    public void testAllChange() {
        ArrayList<KenyaStudent> students = education.getStudentList();
        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();
        students.add(student1);
        students.add(student2);
        student1.setAcademicConfusion(ac1);
        student2.setAcademicConfusion(ac2);
        AcademicConfusion confusion1 = student1.getAcademicConfusion();
        AcademicConfusion confusion2 = student2.getAcademicConfusion();

        education.addQuestions(ac3);
        education.addQuestionsBeingAnswered(ac3);

        assertEquals(ac1,student1.getAcademicConfusion());
        assertEquals(ac2,student2.getAcademicConfusion());

        director2.checkQuestionsAnswered(education);

        assertNull(student1.getAcademicConfusion());
        assertNull(student2.getAcademicConfusion());
    }





}

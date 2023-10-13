package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public abstract class VolunteerTest {

    Volunteer volunteer1;
    Volunteer volunteer2;
    Volunteer volunteer3;

    Education education;

    KenyaStudent student1;
    KenyaStudent student2;

    AcademicConfusion ac1;
    AcademicConfusion ac2;
    AcademicConfusion ac3;

    @Test
    public void testGetName() {
        assertEquals(volunteer1.name,volunteer1.getName());
    }

    @Test
    public void testGetMajor() {
        assertEquals(volunteer2.major,volunteer2.getMajor());
    }

    @Test
    public void testGetYear() {
        assertEquals(volunteer3.year,volunteer3.getYear());
    }

    @Test
    public void testGetId() {
        assertEquals(volunteer1.id,volunteer1.getId());
    }

    @Test
    public void testUpdateYear() {
        int originalYear = volunteer1.year;

        volunteer1.updateYear(originalYear - 1);

        assertEquals(originalYear-1,volunteer1.getYear());
    }

    @Test
    public void testViewStudents() {
        education.addStudents(student1);
        ArrayList<KenyaStudent> viewList1 =
                volunteer1.viewListOfStudents(education);
        assertEquals(education.getStudentList(),viewList1);

        education.addStudents(student2);
        ArrayList<KenyaStudent> viewList2 =
                volunteer2.viewListOfStudents(education);
        assertEquals(education.getStudentList(),viewList2);

        education.removeStudents(student1);
        ArrayList<KenyaStudent> viewList3 =
                volunteer3.viewListOfStudents(education);
        assertEquals(education.getStudentList(),viewList3);

    }

    @Test
    public void testViewConfusions() {
        education.addQuestions(ac1);
        ArrayList<AcademicConfusion> viewList1 =
                volunteer1.viewConfusions(education);
        assertEquals(education.getQuestions(),viewList1);

        education.addQuestions(ac2);
        ArrayList<AcademicConfusion> viewList2 =
                volunteer2.viewConfusions(education);
        assertEquals(education.getQuestions(),viewList2);

        education.removeQuestions(ac2);
        ArrayList<AcademicConfusion> viewList3 =
                volunteer3.viewConfusions(education);
        assertEquals(education.getQuestions(),viewList3);

    }

    @Test
    public void testWorkingQuestionAnswered() {

        education.addQuestions(ac1);
        education.addQuestions(ac2);
        education.addQuestionsBeingAnswered(ac1);

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        assertEquals(2,confusions.size());
        assertEquals(ac1,confusions.get(0));
        assertEquals(ac2,confusions.get(1));
        assertEquals(1,beingAnswered.size());
        assertEquals(ac1,beingAnswered.get(0));


        boolean already = volunteer1.workingOnConfusion(education,ac1);

        assertFalse(already);
        assertFalse(confusions.contains(ac1));
        assertTrue(confusions.contains(ac2));
        assertEquals(1,confusions.size());
        assertEquals(1,beingAnswered.size());
        assertTrue(beingAnswered.contains(ac1));


    }

    @Test
    public void testWorkingQuestionsNot(){
        education.addQuestions(ac1);
        education.addQuestions(ac2);
        education.addQuestionsBeingAnswered(ac1);

        ArrayList<AcademicConfusion> confusions = education.getQuestions();
        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        assertEquals(2,confusions.size());
        assertEquals(ac1,confusions.get(0));
        assertEquals(ac2,confusions.get(1));
        assertEquals(1,beingAnswered.size());
        assertEquals(ac1,beingAnswered.get(0));

        boolean notThere = volunteer2.workingOnConfusion(education,ac2);

        assertTrue(notThere);
        assertEquals(1,confusions.size());
        assertTrue(confusions.contains(ac1));
        assertFalse(confusions.contains(ac2));
        assertEquals(2,beingAnswered.size());
        assertEquals(ac1,beingAnswered.get(0));
        assertEquals(ac2,beingAnswered.get(1));
    }

    @Test
    public void testRemoveConfusion() {
        education.addQuestionsBeingAnswered(ac1);
        education.addQuestionsBeingAnswered(ac2);
        education.addQuestionsBeingAnswered(ac3);

        ArrayList<AcademicConfusion> beingAnswered =
                education.getQuestionsBeingAnswered();

        volunteer2.removeConfusion(education,ac2);

        assertEquals(2,beingAnswered.size());
        assertFalse(beingAnswered.contains(ac2));
        assertEquals(ac1,beingAnswered.get(0));
        assertEquals(ac3,beingAnswered.get(1));

        volunteer1.removeConfusion(education,ac1);
        assertEquals(1,beingAnswered.size());
        assertFalse(beingAnswered.contains(ac1));
        assertEquals(ac3,beingAnswered.get(0));

    }


}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityVolunteerTest extends VolunteerTest{

    @BeforeEach
    public void setup() {
        volunteer1 = new UniversityVolunteer("Jack","math",
                10000,2);
        volunteer2 = new UniversityVolunteer("Tom","chemistry",
                10001,3);
        volunteer3 = new UniversityVolunteer("Amy","biology",
                10002,3);

        education = new Education("dd");

        student1 = new KenyaStudent(10000,"Peter",3);
        student2 = new KenyaStudent(10001,"Emma",2);

        ac1 = new AcademicConfusion(10000);
        ac2 = new AcademicConfusion(10001);
        ac3 = new AcademicConfusion(10003);
    }

}

package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// test the methods in AcademicConfusion class
class AcademicConfusionTest {

    AcademicConfusion ac;

    @BeforeEach
    public void setup() {
        ac = new AcademicConfusion(10000);
    }

    @Test
    public void testSetSubject() {
        ac.setSubject("math");
        assertEquals("math",ac.getSubject());
    }

    @Test
    public void testSetDescription() {
        ac.setDescription("What's the derivative of tan(x)");
        assertEquals("What's the derivative of tan(x)",
                ac.getDescription());
    }

    @Test
    public void testGetId() {
        assertEquals(10000,ac.getId());
    }

}
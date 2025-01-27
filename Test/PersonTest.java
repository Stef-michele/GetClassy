import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp()
    {
        p1 = new Person("00000A", "Bob", "Tester1", "Mr.", 1955);
        p2 = new Person("00000B", "Sally", "Tester2", "Mrs.", 1975);
        Person.setIDSeed(0);
        p3 = new Person("Bob", "Tester3", "Mr.", 1960);
        p4 = new Person("Sally", "Tester4", "Mrs.", 1965);
        p5 = new Person("Fred", "Tester5", "Mr.", 1970);
        p6 = new Person("Cindy", "Tester6", "Ms.", 1975);
    }

    @Test
    void fullName() {
        assertEquals("Bob Tester1", p1.fullName());
    }

    @Test
    void formalName() {
        assertEquals("Mr. Bob Tester1", p1.formalName());
    }

    @Test
    void getAge() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        assertEquals(currentYear - 1955, p1.getAge());
    }

    @Test
    void testGetAge() {
        assertEquals(70, p1.getAge(2025)); // Assuming 2025 is the year you want to test
    }

    @Test
    void toCSVRecord() {
        assertEquals("00000A, Bob,Tester1,Mr.,1955", p1.toCSVRecord());
    }

    @Test
    void toJSONRecord() {
        String expected = "{\"IDNum\":\"00000A\",\"firstName\":\"Bob\", \"lastName\":\"Tester1\", \"title\":\"Mr.\", \"YOB\":1955}";
        assertEquals(expected, p1.toJSONRecord());
    }

    @Test
    void toXMLRecord() {
        String expected = "<Person>00000A</IDNum><firstName>Bob</firstName><lastName>Tester1</lastName><title>Mr.</title><YOB>1955</YOB></Person>";
        assertEquals(expected, p1.toXMLRecord());
    }

    @Test
    void testToString() {
        assertEquals("Person{IDNum='00000A', firstName='Bob', lastName='Tester1', title='Mr.', YOB=1955}", p1.toString());
    }

    @Test
    void testEquals() {
        // Test if p1 and p2 are considered equal (same properties)
        p1.setIDNum("00000B");
        p1.setFirstName("Sally");
        p1.setLastName("Tester2");
        p1.setTitle("Mrs.");
        p1.setYOB(1975);
        assertTrue(p1.equals(p2));

        // Modify p1 and test it is not equal to p2
        p1.setFirstName("Bob");
        p1.setLastName("Tester1");
        p1.setYOB(1955);
        assertFalse(p1.equals(p2));

        // Test if p1 is not equal to a different class
        assertFalse(p1.equals(new Object()));

        // Test if p1 is not equal to null
        assertFalse(p1.equals(null));
    }
    @Test
    void testFullConstructor() {
        // Arrange & Act
        Person person = new Person("123456", "John", "Doe", "Mr.", 1985);

        // Assert
        assertEquals("John", person.getFirstName());  // Ensure firstName is correct
        assertEquals("Doe", person.getLastName());    // Ensure lastName is correct
        assertEquals("123456", person.getIDNum());        // Ensure ID is correct
        assertEquals("Mr.", person.getTitle());       // Ensure title is correct
        assertEquals(1985, person.getYOB());          // Ensure Year of Birth is correct
    }
    @Test
    void testOverloadedConstructor() {
        // Arrange & ActgetTitle
        Person person = new Person("Jane", "Smith", "Ms.", 1990);

        // Assert
        assertNotNull(person.getIDNum());  // Ensure ID was automatically generated
        assertEquals("Jane", person.getFirstName());  // Ensure firstName is correct
        assertEquals("Smith", person.getLastName());  // Ensure lastName is correct
        assertEquals("Ms.", person.getTitle());       // Ensure title is correct
        assertEquals(1990, person.getYOB());          // Ensure Year of Birth is correct
    }
}

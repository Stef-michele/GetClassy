import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ObjInputTest {

    private SafeInputObj safeInputObj;

    @BeforeEach
    void setUp() {

        safeInputObj = new SafeInputObj();

    }

    @Test
    void getNonZeroLenString() {
        Scanner scanner = new Scanner("TestString\n");
        safeInputObj = new SafeInputObj(scanner);
        String result = safeInputObj.getNonZeroLenString("Enter a non-empty string:");
        assertEquals("TestString", result);

        scanner = new Scanner("\nValidInput\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getNonZeroLenString("Enter a non-empty string:");
        assertEquals("ValidInput", result);

    }

    @Test
    void getRangedInt() {
        Scanner scanner = new Scanner("5\n");
        safeInputObj = new SafeInputObj(scanner);
        int result = safeInputObj.getRangedInt("Enter a number:", 1, 10);
        assertEquals(5, result);

        // Simulate out-of-range input followed by valid input
        scanner = new Scanner("15\n3\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getRangedInt("Enter a number:", 1, 10);
        assertEquals(3, result);

    }

    @Test
    void getInt() {
        // Simulate valid integer input
        Scanner scanner = new Scanner("42\n");
        safeInputObj = new SafeInputObj(scanner);
        int result = safeInputObj.getInt("Enter a number:");
        assertEquals(42, result);

        // Simulate invalid input followed by valid integer
        scanner = new Scanner("abc\n24\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getInt("Enter a number:");
        assertEquals(24, result);

    }

    @Test
    void getRangedDouble() {
        // Simulate valid double input within range
        Scanner scanner = new Scanner("7.5\n");
        safeInputObj = new SafeInputObj(scanner);
        double result = safeInputObj.getRangedDouble("Enter a double:", 1.0, 10.0);
        assertEquals(7.5, result, 0.001);

        // Simulate out-of-range input followed by valid input
        scanner = new Scanner("15.5\n8.3\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getRangedDouble("Enter a double:", 1.0, 10.0);
        assertEquals(8.3, result, 0.001);

    }

    @Test
    void getDouble() {
        // Simulate valid double input
        Scanner scanner = new Scanner("3.14159\n");
        safeInputObj = new SafeInputObj(scanner);
        double result = safeInputObj.getDouble("Enter a number:");
        assertEquals(3.14159, result, 0.001);

        // Simulate invalid input followed by valid double input
        scanner = new Scanner("invalid\n2.71828\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getDouble("Enter a number:");
        assertEquals(2.71828, result, 0.001);

    }

    @Test
    void getYNConfirm() {
        // Simulate "Yes" input
        Scanner scanner = new Scanner("Y\n");
        safeInputObj = new SafeInputObj(scanner);
        boolean result = safeInputObj.getYNConfirm("Continue?");
        assertTrue(result);

        // Simulate "No" input
        scanner = new Scanner("N\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getYNConfirm("Continue?");
        assertFalse(result);

        // Simulate invalid input followed by "Yes"
        scanner = new Scanner("maybe\nY\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getYNConfirm("Continue?");
        assertTrue(result);

    }

    @Test
    void getRegExString() {
        // Simulate a valid input matching the regex
        Scanner scanner = new Scanner("12345\n");
        safeInputObj = new SafeInputObj(scanner);
        String result = safeInputObj.getRegExString("Enter a 5-digit number:", "\\d{5}");
        assertEquals("12345", result);

        // Simulate invalid input followed by valid input
        scanner = new Scanner("abc\n67890\n");
        safeInputObj = new SafeInputObj(scanner);
        result = safeInputObj.getRegExString("Enter a 5-digit number:", "\\d{5}");
        assertEquals("67890", result);

    }
}
import java.util.Scanner;

/**
 * SafeInputObj: Provides safe input methods for primitive types and Strings,
 * ensuring valid input and preventing common user input errors.
 *
 * This is an object-oriented version of the static SafeInput class.
 * It utilizes a single `Scanner pipe` instance as a class field, shared among all methods.
 */
public class SafeInputObj {
    private Scanner pipe; // Class-level Scanner instance for all input methods.

    /**
     * Default constructor: Initializes `pipe` with `System.in`.
     */
    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    /**
     * Constructor: Allows a custom Scanner instance to be passed in (not used in this lab).
     *
     * @param scanner A pre-configured Scanner instance
     */
    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    /**
     * Get a String which contains at least one character.
     *
     * @param prompt The prompt for the user
     * @return A String response that is not zero length
     */
    public String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0); // Repeat until we have non-zero length input

        return retString;
    }

    /**
     * Get an int value within a specified numeric range.
     *
     * @param prompt The input prompt message (should not include range info)
     * @param low    The low end of the inclusive range
     * @param high   The high end of the inclusive range
     * @return An int value within the inclusive range
     */
    public int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // Clear input buffer
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get an int value with no constraints.
     *
     * @param prompt The input prompt message
     * @return An unconstrained int value
     */
    public int getInt(String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // Clear input buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a double value within an inclusive range.
     *
     * @param prompt The input prompt message
     * @param low    The low end of the inclusive range
     * @param high   The high end of the inclusive range
     * @return A double value within the specified inclusive range
     */
    public double getRangedDouble(String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // Clear input buffer
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get an unconstrained double value.
     *
     * @param prompt The input prompt message
     * @return An unconstrained double value
     */
    public double getDouble(String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a [Y/N] confirmation from the user.
     *
     * @param prompt The input prompt message
     * @return true for "yes", false for "no"
     */
    public boolean getYNConfirm(String prompt) {
        boolean retVal = true;
        String response = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                done = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                done = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]! You entered: " + response);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a string that matches a RegEx pattern.
     *
     * @param prompt       The input prompt message
     * @param regExPattern Java-style RegEx pattern to constrain the input
     * @return A String that matches the RegEx pattern
     */
    public String getRegExString(String prompt, String regExPattern) {
        String response = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                done = true;
            } else {
                System.out.println("\nInput must match the pattern: " + regExPattern);
                System.out.println("Try again!");
            }
        } while (!done);

        return response;
    }
}
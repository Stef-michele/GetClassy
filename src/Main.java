import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {
        // Create an ArrayList to store Person objects
        ArrayList<Person> folks = new ArrayList<>();

        // Create and add Person objects
        Person sally = new Person("000001", "Sally", "Williams", "Ms.", 2005);
        System.out.println(sally);
        folks.add(sally);

        // Modify and print a Person object
        sally.setLastName("Smith");
        System.out.println(sally);

        Person fred = new Person("Fred", "Williams", "Mr.", 2010);
        System.out.println(fred);
        folks.add(fred);

        Person homer = new Person("000003", "Homer", "Simpson", "Mr.", 1975);
        System.out.println(homer);
        folks.add(homer);

        // Print all Person objects in the list
        System.out.println("\nAll Persons:");
        for (Person p : folks) {
            System.out.println(p);
        }

        // Test fullName, formalName, and getAge methods
        System.out.println("\nTesting methods:");

        for (Person p : folks) {
            System.out.println("Full Name: " + p.fullName()); // Test fullName
            System.out.println("Formal Name: " + p.formalName()); // Test formalName
            System.out.println("Current Age: " + p.getAge()); // Test getAge (current year)

            // Test getAge(int year) with a specific year (e.g., 2030)
            int testYear = 2030;
            System.out.println("Age in " + testYear + ": " + p.getAge(testYear));
            System.out.println();
        }

        // File Storage formats demonstration
        System.out.println("\nFile Storage formats:");
        for (Person p : folks) {
            System.out.println("JSON: " + p.toJSONRecord());
            System.out.println("XML:  " + p.toXMLRecord());
            System.out.println("CSV:  " + p.toCSVRecord());
            System.out.println();
        }
    }
}
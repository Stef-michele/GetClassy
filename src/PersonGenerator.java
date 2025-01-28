
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args)

    {
        ArrayList<Person> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");
        boolean done = false;

     String personRec = "";
     String ID = "";
     String firstName = "";
     String lastName = "";
     String title = "";
     int YOB = 0;

     do {
         ID = SafeInput.getNonZeroLenString(in, "Enter the ID (6 digits)");
         firstName = SafeInput.getNonZeroLenString(in, "Enter the First Name ");
         lastName = SafeInput.getNonZeroLenString(in, "Enter the Last Name ");
         title = SafeInput.getNonZeroLenString(in, "Enter the Title ");
         YOB = SafeInput.getRangedInt (in, "Enter the year of birth (1940-2010)", 1940, 2010);

         // Create a Person object and add it to the ArrayList<Person>
         Person person = new Person(ID, firstName, lastName, title, YOB);
         folks.add(person);


         done = SafeInput.getYNConfirm(in, "Are you done?");


     } while(!done);

     for (Person p: folks)
         System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person person : folks)
            {
                writer.write(person.toCSVRecord()); // Use toCSVRecord() from the Person class
                writer.newLine();  // Adds a new line after each record
            }


            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
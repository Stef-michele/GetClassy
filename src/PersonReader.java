import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PersonReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String prec;
        ArrayList<Person> people = new ArrayList<>();
        final int FIELDS_LENGTH = 5;

        try {
            // Set current working directory
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath()); // Debugging

                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                // Process the file line by line
                while (reader.ready()) {
                    rec = reader.readLine();
                    System.out.println("Reading record: " + rec); // Show each record

                    String[] fields = rec.split(",");

                    if (fields.length == FIELDS_LENGTH) {
                        try {
                            String ID = fields[0].trim();
                            String FirstName = fields[1].trim();
                            String LastName = fields[2].trim();
                            String Title = fields[3].trim();
                            int YOB = Integer.parseInt(fields[4].trim());

                            // Validate YOB range
                            if (YOB < 1940 || YOB > 2010) {
                                System.out.println("Invalid Year of Birth: " + YOB + " in record: " + rec);
                                continue;
                            }

                            Person person = new Person(ID, FirstName, LastName, Title, YOB);
                            people.add(person);

                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing numeric data in record: " + rec);
                        }
                    } else {
                        System.out.println("Invalid record format (wrong number of fields): " + rec);
                    }
                }

                reader.close();

                // Output results
                System.out.println("\nReconstructed People from File:");
                for (Person person : people) {
                    System.out.println(person);
                }

            } else {
                System.out.println("No file selected. Program will exit.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
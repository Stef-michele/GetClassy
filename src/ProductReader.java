import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        ArrayList<Product> products = new ArrayList<>(); // List to hold Product objects

        final int FIELDS_LENGTH = 4; // Corrected to int since fields.length is an int


        try {
            // Use JFileChooser to select the Product data file
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                Path file = selectedFile.toPath();

                // Open file to read
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int lineNumber = 0; // Track line numbers
                String prec;

                while ((prec = reader.readLine()) != null) {
                    lineNumber++;

                    String[] fields = prec.split(","); // Split the record into fields
                    if (fields.length == FIELDS_LENGTH) {
                        try {
                            String productID = fields[0].trim();
                            String Name = fields[1].trim();
                            String Description = fields[2].trim();
                            Double Cost = parseDouble(fields[3].trim()); // Parse the cost into double

                            // Create a new Product and add it to the list
                            Product product = new Product(productID, Name, Description, Cost);
                            products.add(product);

                            System.out.println("Line " + lineNumber + ": " + product);


                        } catch (NumberFormatException e) {
                            System.out.printf("Error parsing numeric field on line %d: %s%n", lineNumber, prec);
                        }
                    } else {
                        // Invalid record format
                        System.out.printf("Invalid record format on line %d: %s%n", lineNumber, prec);
                    }
                }
                reader.close(); // Close the reader when done
                System.out.println("\n\nData file read successfully!");

                // Display the reconstructed Product objects
                System.out.println("Reconstructed Products from File:");
                if (products.isEmpty()) {
                    System.out.println("No products found");
                } else {

                for (Product product : products) {
                    System.out.println(product); // Calls Product's toString() method
                }
            }
            } else {
                System.out.println("No file selected. Program will exit.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args)

    {
        ArrayList<Product> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        boolean done = false;

        String productRec = "";
        String productID = "";
        String Name = "";
        String Description = "";
        double Cost = 0;

        do {
            productID = SafeInput.getNonZeroLenString(in, "Enter the Product ID (6 digits)");
            Name = SafeInput.getNonZeroLenString(in, "Enter the Product Name ");
            Description = SafeInput.getNonZeroLenString(in, "Enter Description ");
            Cost = SafeInput.getDouble (in, "Enter Item Cost");

            Product product = new Product(productID, Name, Description, Cost);
            products.add(product);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while(!done);

        for (Product p: products)
            System.out.println(p);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

        for (Product product : products) {

            writer.write(product.toCSVRecord());
            writer.newLine(); // Add a new line after each product record
        }
        writer.close(); // Close the writer to save the file
        System.out.println("\nData file written successfully!");
    } catch (IOException e) {
        System.out.println("Error writing the data file.");
        e.printStackTrace();
    }

    }
}


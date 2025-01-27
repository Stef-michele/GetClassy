import java.util.Calendar;
import java.util.Objects;

    public class Product {
        private String productID;
        private String Name;
        private String Description;
        private double Cost;

        private static int ProdIDSeed = 1;

        // Generated constructors
        public Product(String productID, String name, String description, double cost) {
            this.productID = productID;
            Name = name;
            Description = description;
            Cost = cost;
        }

        public Product(String name, String description, double cost) {
            productID = genProdID();
            Name = name;
            Description = description;
            Cost = cost;
        }

        public static int getProdIDSeed() {
            return ProdIDSeed;
        }

        public static void setProdIDSeed(int prodIDSeed) {
            Product.ProdIDSeed = prodIDSeed;
        }

        // Method to generate Product IDs
        private String genProdID() {
            String newProdID = "" + ProdIDSeed;
            while (newProdID.length() < 8) {
                newProdID = "0" + newProdID;
            }

            ProdIDSeed++;
            return newProdID;
        }

        // Getters and Setters
        public String getProductID() {
            return productID;
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public double getCost() {
            return Cost;
        }

        public void setCost(double cost) {
            Cost = cost;
        }

        // Method to convert Product to a CSV record
        public String toCSVRecord() {
            return this.productID + ", " + this.Name + ", " + this.Description + ", " + this.Cost;
        }

        // Method to convert Product to a JSON record
        public String toJSONRecord() {
            String retString = "";
            char DQ = '\u0022';  // Assign the double quote char to a variable
            retString = "{" + DQ + "productID" + DQ + ":" + DQ + this.productID + DQ + ",";
            retString += DQ + "Name" + DQ + ":" + DQ + this.Name + DQ + ",";
            retString += DQ + "Description" + DQ + ":" + DQ + this.Description + DQ + ",";
            retString += DQ + "Cost" + DQ + ":" + this.Cost + "}";

            return retString;
        }

        // Method to convert Product to an XML record
        public String toXMLRecord() {
            String retString = "";
            retString = "<Product><productID>" + this.productID + "</productID>";
            retString += "<Name>" + this.Name + "</Name>";
            retString += "<Description>" + this.Description + "</Description>";
            retString += "<Cost>" + this.Cost + "</Cost></Product>";

            return retString;
        }

        // Override toString method
        @Override
        public String toString() {
            return "Product{" +
                    "productID='" + productID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Description='" + Description + '\'' +
                    ", Cost=" + Cost +
                    '}';
        }

        // Override equals method for comparing Product objects
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.Cost, Cost) == 0 &&
                    Objects.equals(productID, product.productID) &&
                    Objects.equals(Name, product.Name) &&
                    Objects.equals(Description, product.Description);
        }

        // Override hashCode method for hash-based collections
        @Override
        public int hashCode() {
            return Objects.hash(productID, Name, Description, Cost);
        }
    }
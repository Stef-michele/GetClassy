import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void resetProdIDSeed() {
        // Reset ProdIDSeed to a consistent starting point
        Product.setProdIDSeed(1);
    }


    @Test
    void getProdIDSeed() {
        assertEquals(1, Product.getProdIDSeed());
    }

    @Test
    void setProdIDSeed() {
        Product.setProdIDSeed(10);
        assertEquals(10, Product.getProdIDSeed());
    }

    @Test
    void getName() {
        Product product = new Product("TestName", "Description", 50.00);
        assertEquals("TestName", product.getName());
    }

    @Test
    void toCSVRecord() {
        Product product = new Product("TestID", "TestName", "TestDescription", 40.50);
        assertEquals("TestID, TestName, TestDescription, 40.5", product.toCSVRecord());
    }

    @Test
    void toJSONRecord() {
        Product product = new Product("TestID", "TestName", "TestDescription", 40.50);
        String expectedJSON = "{\"productID\":\"TestID\",\"Name\":\"TestName\",\"Description\":\"TestDescription\",\"Cost\":40.5}";
        assertEquals(expectedJSON, product.toJSONRecord());
    }

    @Test
    void toXMLRecord() {
        Product product = new Product("TestID", "TestName", "TestDescription", 40.50);
        String expectedXML = "<Product><productID>TestID</productID><Name>TestName</Name><Description>TestDescription</Description><Cost>40.5</Cost></Product>";
        assertEquals(expectedXML, product.toXMLRecord());
    }

    @Test
    void testToString() {
        Product product = new Product("TestID", "TestName", "TestDescription", 40.50);
        String expectedString = "Product{productID='TestID', Name='TestName', Description='TestDescription', Cost=40.5}";
        assertEquals(expectedString, product.toString());
    }

    @Test
    void testEquals() {
        Product product1 = new Product("ID1", "Name1", "Description1", 50.00);
        Product product2 = new Product("ID1", "Name1", "Description1", 50.00);
        assertEquals(product1, product2);
    }

    @Test
    void testHashCode() {
        Product product1 = new Product("ID1", "Name1", "Description1", 50.00);
        Product product2 = new Product("ID1", "Name1", "Description1", 50.00);
        assertEquals(product1.hashCode(), product2.hashCode());
    }
    @Test
    void testFullConstructor() {
        // Arrange & Act
        Product product = new Product("12345678", "Laptop", "Gaming laptop", 1500.50);

        // Assert
        assertEquals("12345678", product.getProductID());  // Ensure ID is correct
        assertEquals("Laptop", product.getName());        // Ensure name is correct
        assertEquals("Gaming laptop", product.getDescription());  // Ensure description is correct
        assertEquals(1500.50, product.getCost());   // Ensure cost is correct
    }
    @Test
    void testOverloadedConstructor() {
        // Arrange & Act
        Product product = new Product("Smartphone", "A flagship device", 799.99);

        // Assert
        assertNotNull(product.getProductID());  // ID should be automatically generated
        assertEquals("Smartphone", product.getName());  // Ensure name is correct
        assertEquals("A flagship device", product.getDescription());  // Ensure description is correct
        assertEquals(799.99, product.getCost());  // Ensure cost is correct
    }
}
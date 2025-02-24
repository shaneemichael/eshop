package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, product.getProductQuantity());
    }

    @Test
    void testGetProductIdFailure() {
        assertNotEquals("aaaaaaaa-1c39-460e-8860-71af6af63bd6", product.getProductId());
    }

    @Test
    void testGetProductNameFailure() {
        assertNotEquals("Sampo Cap Budi", product.getProductName());
    }

    @Test
    void testGetProductQuantityFailure() {
        assertNotEquals(33, product.getProductQuantity());
    }


}

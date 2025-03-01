package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // This function is intentionally left empty, as no setup is needed
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void findByIdWhenProductExists() {
        Product product1 = new Product();
        product1.setProductId("21ef9e3f-b1e9-4ed4-8f19-a665aab1c34d");
        product1.setProductName("Budi Tabuti");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("1622e479-2df6-4384-b0fb-ec583331f01b");
        product2.setProductName("Rudi Hartono");
        product2.setProductQuantity(100);
        productRepository.create(product2);

        String productId = "1622e479-2df6-4384-b0fb-ec583331f01b";
        Product foundProduct = productRepository.findById(productId);

        assertNotNull(foundProduct);
        assertEquals("Rudi Hartono", foundProduct.getProductName());
        assertEquals("1622e479-2df6-4384-b0fb-ec583331f01b", foundProduct.getProductId());
    }

    @Test
    void findByIdWhenProductDoesNotExist() {
        String productId = "4";
        Product foundProduct = productRepository.findById(productId);

        assertNull(foundProduct);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("5d1bb638-ebb2-4a9e-81c2-5dfe411d84dc");
        product.setProductName("Testing 1");
        product.setProductQuantity(7);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Testing 2");
        updatedProduct.setProductQuantity(8);

        Product result = productRepository.update(updatedProduct.getProductId(), updatedProduct);
        assertNotNull(result);
        assertEquals("Testing 2", result.getProductName());
        assertEquals(8, result.getProductQuantity());
    }

    @Test
    void testEditNonExistentonExistuct() {
        Product existingProduct = new Product();
        existingProduct.setProductId("f01e44a5-1bfe-4e57-ad2d-ad57018d49a0");
        existingProduct.setProductName("Testing 1");
        existingProduct.setProductQuantity(7);
        productRepository.create(existingProduct);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("e3274332-7c8c-4d38-b3e7-1b2f3e9fd431"); // Non-matching ID
        updatedProduct.setProductName("GOAT");
        updatedProduct.setProductQuantity(7);

        Product result = productRepository.update(updatedProduct.getProductId(), updatedProduct);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        productRepository.delete(product.getProductId());

        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonExistentProduct() {
        productRepository.delete("aaaaaaaa-aaaaaaaa-aaaaaaaa");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

}

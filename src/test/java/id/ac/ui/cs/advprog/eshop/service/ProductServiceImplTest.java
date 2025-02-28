package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product("1", "Test Product", 100);
    }

    @Test
    void createProduct_ShouldReturnProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void findAllProducts_ShouldReturnListOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
        assertEquals("Test Product", allProducts.get(0).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findProductById_ShouldReturnProduct() {
        when(productRepository.findById("1")).thenReturn(product);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void editProduct_ShouldReturnUpdatedProduct() {
        when(productRepository.update(product.getProductId(), product)).thenReturn(product);

        Product updatedProduct = productService.update(product.getProductId(), product);

        assertNotNull(updatedProduct);
        assertEquals("Test Product", updatedProduct.getProductName());
        verify(productRepository, times(1)).update(product.getProductId(), product);
    }

    @Test
    void deleteProduct_ShouldReturnDeletedProduct() {
        when(productRepository.delete("1")).thenReturn(true);
        Boolean res = productService.delete("1");

        assertTrue(res);
        verify(productRepository, times(1)).delete("1");
        verify(productRepository, times(1)).delete("1");
    }
}

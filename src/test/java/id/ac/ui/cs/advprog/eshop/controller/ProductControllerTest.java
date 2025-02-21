package id.ac.ui.cs.advprog.eshop.controller;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;
    
    @TestConfiguration
    static class ProductControllerTestConfig {
        @Bean
        public ProductService productService() {
            return org.mockito.Mockito.mock(ProductService.class);
        }
    }
    
    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product dummyProduct = new Product("1", "Test Product", 100);
        when(productService.create(any(Product.class))).thenReturn(dummyProduct);

        mockMvc.perform(post("/product/create")
                        .param("name", "Test Product")
                        .param("price", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));

        verify(productService).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        Product product1 = new Product("1", "Product 1", 100);
        Product product2 = new Product("2", "Product 2", 200);

        when(productService.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testEditProductPage() throws Exception {
        String id = "1";
        Product product = new Product(id, "Product 1", 100);
        when(productService.findById(id)).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPost() throws Exception {
        String id = "1";
        Product updatedProduct = new Product(id, "Product 1", 200);
        when(productService.edit(any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(post("/product/edit/{id}", id)
                        .param("name", "Product 1")
                        .param("price", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService).edit(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String id = "1";
        Product product = new Product(id, "Product 1", 100);
        when(productService.delete(id)).thenReturn(product);

        mockMvc.perform(post("/product/delete/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService).delete(id);
    }
}
package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryInterface;

@Service
public class ProductServiceImpl extends AbstractService<Product, String, ProductRepositoryInterface> implements ProductService {
    
    public ProductServiceImpl(ProductRepositoryInterface productRepository) {
        super(productRepository);
    }
}
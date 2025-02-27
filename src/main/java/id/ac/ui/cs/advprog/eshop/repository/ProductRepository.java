package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

@Repository
public class ProductRepository extends AbstractRepository<Product, String> implements ProductRepositoryInterface {
    
    private final IdGenerator idGenerator;
    
    public ProductRepository(IdGenerator idGenerator) {
        super(Product::getProductId);
        this.idGenerator = idGenerator;
    }
    
    public ProductRepository() {
        this(new UuidGenerator());
    }
    
    @Override
    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(idGenerator.generateId());
        }
        return super.create(product);
    }
    
    @Override
    public Optional<Product> update(String id, Product updatedProduct) {
        return findById(id).map(existingProduct -> {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
            return existingProduct;
        });
    }
}
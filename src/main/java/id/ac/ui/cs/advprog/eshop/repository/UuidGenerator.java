package id.ac.ui.cs.advprog.eshop.repository;

public class UuidGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}

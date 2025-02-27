package id.ac.ui.cs.advprog.eshop.model;

public interface Item {
    String getId();
    String getName();
    int getQuantity();
    void setId(String id);
    void setName(String name);
    void setQuantity(int quantity);
}
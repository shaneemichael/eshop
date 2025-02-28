package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

/**
 * Generic service interface for business operations
 * @param <T> Entity type
 * @param <ID> ID type
 */
public interface GeneralService<T, ID> {
    T create(T entity);
    List<T> findAll();
    T findById(ID id);
    T update(ID id, T entity);
    boolean delete(ID id);
}
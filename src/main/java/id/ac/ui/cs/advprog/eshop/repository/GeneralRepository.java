package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import java.util.Optional;

/**
 * Generic repository interface for data access operations
 * @param <T> Entity type
 * @param <ID> ID type
 */
public interface GeneralRepository<T, ID> {
    T create(T entity);
    Iterator<T> findAll();
    Optional<T> findById(ID id);
    Optional<T> update(ID id, T entity);
    boolean delete(ID id);
}
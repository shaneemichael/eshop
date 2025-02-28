package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.repository.GeneralRepository;

/**
 * Abstract implementation of Service interface with common functionality
 * @param <T> Entity type
 * @param <ID> ID type
 * @param <R> Repository type
 */
public abstract class AbstractService<T, ID, R extends GeneralRepository<T, ID>> implements GeneralService<T, ID> {
    
    protected final R repository;
    
    protected AbstractService(R repository) {
        this.repository = repository;
    }
    
    @Override
    public T create(T entity) {
        return repository.create(entity);
    }
    
    @Override
    public List<T> findAll() {
        Iterator<T> iterator = repository.findAll();
        List<T> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        return result;
    }
    
    @Override
    public T findById(ID id) {
        return repository.findById(id);
    }
    
    @Override
    public T update(ID id, T entity) {
        return repository.update(id, entity);
    }
    
    @Override
    public boolean delete(ID id) {
        return repository.delete(id);
    }
}
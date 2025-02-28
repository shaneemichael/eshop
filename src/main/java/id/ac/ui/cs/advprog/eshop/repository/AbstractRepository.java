package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Abstract implementation of Repository interface with common functionality
 * @param <T> Entity type
 * @param <ID> ID type
 */
public abstract class AbstractRepository<T, ID> implements GeneralRepository<T, ID> {
    
    protected final List<T> data = new ArrayList<>();
    protected final Function<T, ID> idExtractor;
    
    protected AbstractRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }
    
    protected Predicate<T> idFilter(ID id) {
        return entity -> idExtractor.apply(entity).equals(id);
    }
    
    @Override
    public T create(T entity) {
        data.add(entity);
        return entity;
    }
    
    @Override
    public Iterator<T> findAll() {
        return data.iterator();
    }
    
    @Override
    public T findById(ID id) {
        for(T entity : data) {
            if(idExtractor.apply(entity).equals(id)) {
                return entity;
            }
        }
        return null;
    }
    
    @Override
    public boolean delete(ID id) {
        return data.removeIf(idFilter(id));
    }
}
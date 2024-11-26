package repositories.interfaces;

import java.util.List;

import exceptions.GenericException;

public interface GenericRepositoryInterface<T, ID> {
    void save(T entity) throws GenericException;

    T findById(ID id);

    List<T> findAll();

    void delete(ID id);

    void update(T entity);
}
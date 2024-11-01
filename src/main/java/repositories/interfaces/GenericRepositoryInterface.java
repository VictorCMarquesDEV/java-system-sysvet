package repositories.interfaces;

import java.util.List;

import exceptions.UserAlreadyRegistered;

public interface GenericRepositoryInterface<T, ID> {
    void save(T entity) throws UserAlreadyRegistered;

    T findById(ID id);

    List<T> findAll();

    void delete(ID id);

    void update(T entity);
}
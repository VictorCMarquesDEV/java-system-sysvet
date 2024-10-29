package repositories.interfaces;

import java.util.List;

public interface GenericRepositoryInterface<T, ID> {
    void save(T entity);

    T findById(ID id);

    List<T> findAll();

    void delete(ID id);
}
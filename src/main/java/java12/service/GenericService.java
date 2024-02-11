package java12.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface GenericService <T,ID> {
    // ID is Long

    //save
    T save(T entity);

    // find
    T findById(ID id);

    // getAll
    List<T> getAll();

    // update
    T updateById(ID id, T newEntity);

    //delete
    void deleteById(ID id);
}

package com.sda.dao;

import java.util.List;

/**
 * @author StanislavR
 */
public interface Dao<T> {
    // getById(Long id) is more descriptive
    T get(Long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    void deleteAll();

}

package knu.ahafonova.myroslava.db2022.lab3.repository;


import java.util.List;

public interface IRepository<T> {
    int save(T entity);

    T findById(int id);

    List<T> findAll();

    int update(T entity);

    int deleteById(int id);

    int deleteAll();
}

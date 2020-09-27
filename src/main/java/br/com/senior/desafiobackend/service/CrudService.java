package br.com.senior.desafiobackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {

    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    T save(T entity);

    T findOne(ID id);

    boolean exists(ID id);

    long count();

    void delete(ID id);

    void delete(T entity);
}

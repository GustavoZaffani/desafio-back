package br.com.senior.desafiobackend.service;

import br.com.senior.desafiobackend.exception.ListResultIsEmptyException;
import br.com.senior.desafiobackend.exception.ResultNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {

    List<T> findAll() throws ListResultIsEmptyException;

    List<T> findAll(Sort sort) throws ListResultIsEmptyException;

    Page<T> findAll(Pageable pageable) throws ListResultIsEmptyException;

    T save(T entity);

    T findOne(ID id) throws ResultNotFoundException;

    boolean exists(ID id);

    long count();

    void delete(ID id);

    void delete(T entity);
}

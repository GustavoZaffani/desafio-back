package br.com.senior.desafiobackend.service.impl;

import br.com.senior.desafiobackend.exception.ListResultIsEmptyException;
import br.com.senior.desafiobackend.exception.ResultNotFoundException;
import br.com.senior.desafiobackend.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public abstract class CrudServiceImpl<T, ID extends Serializable>
        implements CrudService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() throws ListResultIsEmptyException {
        List<T> list = getRepository().findAll();
        if (list.isEmpty()) {
            throw new ListResultIsEmptyException();
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) throws ListResultIsEmptyException {
        List<T> list = getRepository().findAll(sort);
        if (list.isEmpty()) {
            throw new ListResultIsEmptyException();
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) throws ListResultIsEmptyException {
        Page<T> list = getRepository().findAll(pageable);
        if (list.isEmpty()) {
            throw new ListResultIsEmptyException();
        }
        return list;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) throws ResultNotFoundException {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResultNotFoundException((UUID) id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getRepository().count();
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getRepository().delete(entity);
    }
}

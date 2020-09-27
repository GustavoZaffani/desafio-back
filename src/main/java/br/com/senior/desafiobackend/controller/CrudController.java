package br.com.senior.desafiobackend.controller;

import br.com.senior.desafiobackend.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class CrudController<T, ID extends Serializable> {

    protected abstract CrudService<T, ID> getService();

    @GetMapping
    public List<T> findAll() {
        return getService().findAll(Sort.by("id"));
    }

    @PostMapping
    public T save(@RequestBody T object) {
        return getService().save(object);
    }

    @GetMapping("{id}")
    public T findone(@PathVariable("id") ID id) {
        return getService().findOne(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") ID id) {
        getService().delete(id);
    }

    @GetMapping("exists/{id}")
    public boolean exists(@PathVariable ID id) {
        return getService().exists(id);
    }

    @GetMapping("count")
    public long count() {
        return getService().count();
    }

    @GetMapping("page")
    public Page<T> findAllPaged(@RequestParam("page") int page,
                                @RequestParam("size") int size,
                                @RequestParam(required = false) String order,
                                @RequestParam(required = false) Boolean asc) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (order != null && asc != null) {
            pageRequest = PageRequest.of(page, size, asc ? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        return getService().findAll(pageRequest);
    }
}


package br.com.senior.desafiobackend.service;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.filter.ProdutoFilter;

import java.util.List;
import java.util.UUID;

public interface ProdutoService extends CrudService<Produto, UUID> {

    List<Produto> filter(ProdutoFilter produtoFilter);
}

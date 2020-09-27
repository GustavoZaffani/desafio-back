package br.com.senior.desafiobackend.repository;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.filter.ProdutoFilter;

import java.util.List;

public interface ProdutoCustomRepository {

    List<Produto> filter(ProdutoFilter produtoFilter);
}

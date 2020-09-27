package br.com.senior.desafiobackend.service.impl;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.filter.ProdutoFilter;
import br.com.senior.desafiobackend.repository.ProdutoCustomRepository;
import br.com.senior.desafiobackend.repository.ProdutoRepository;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, UUID>
        implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoCustomRepository produtoCustomRepository;

    @Override
    protected JpaRepository<Produto, UUID> getRepository() {
        return produtoRepository;
    }

    @Override
    public List<Produto> filter(ProdutoFilter produtoFilter) {
        return produtoCustomRepository.filter(produtoFilter);
    }
}

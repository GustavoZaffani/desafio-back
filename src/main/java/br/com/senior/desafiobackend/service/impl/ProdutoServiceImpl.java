package br.com.senior.desafiobackend.service.impl;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.repository.ProdutoRepository;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, UUID>
    implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    protected JpaRepository<Produto, UUID> getRepository() {
        return produtoRepository;
    }
}

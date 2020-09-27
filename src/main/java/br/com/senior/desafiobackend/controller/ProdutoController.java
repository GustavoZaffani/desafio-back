package br.com.senior.desafiobackend.controller;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.service.CrudService;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("produto")
public class ProdutoController extends CrudController<Produto, UUID> {

    @Autowired
    private ProdutoService produtoService;

    @Override
    protected CrudService<Produto, UUID> getService() {
        return produtoService;
    }
}

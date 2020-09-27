package br.com.senior.desafiobackend.controller;

import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.service.CrudService;
import br.com.senior.desafiobackend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("pedido")
public class PedidoController extends CrudController<Pedido, UUID> {

    @Autowired
    private PedidoService pedidoService;

    @Override
    protected CrudService getService() {
        return pedidoService;
    }
}

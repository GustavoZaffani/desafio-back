package br.com.senior.desafiobackend.service;

import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.filter.PedidoFilter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PedidoService extends CrudService<Pedido, UUID> {

    List<Pedido> filter(PedidoFilter pedidoFilter);

    BigDecimal aplicaDescontoPedido(BigDecimal percentual, Pedido pedido);
}

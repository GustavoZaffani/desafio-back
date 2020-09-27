package br.com.senior.desafiobackend.repository;

import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.filter.PedidoFilter;

import java.util.List;

public interface PedidoCustomRepository {

    List<Pedido> filter(PedidoFilter pedidoFilter);
}

package br.com.senior.desafiobackend.service.impl;

import br.com.senior.desafiobackend.ennumeration.StatusPedido;
import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.PedidoItem;
import br.com.senior.desafiobackend.model.filter.PedidoFilter;
import br.com.senior.desafiobackend.repository.PedidoCustomRepository;
import br.com.senior.desafiobackend.repository.PedidoRepository;
import br.com.senior.desafiobackend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, UUID>
        implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoCustomRepository pedidoCustomRepository;

    @Override
    protected JpaRepository<Pedido, UUID> getRepository() {
        return pedidoRepository;
    }

    @Override
    public Pedido save(Pedido entity) {
        entity.getPedidoItem().stream().forEach(pedidoItem -> {
            if (pedidoItem.getProduto().getInativo().equals(Boolean.TRUE)) {
                throw new RuntimeException("Não é possível adicionar um produto inativo no pedido");
            }
        });
        return super.save(entity);
    }

    @Override
    public List<Pedido> filter(PedidoFilter pedidoFilter) {
        return pedidoCustomRepository.filter(pedidoFilter);
    }

    @Override
    public BigDecimal aplicaDescontoPedido(BigDecimal percentual, Pedido pedido) {
        if (pedido.getStatus().equals(StatusPedido.F)) {
            throw new RuntimeException("Não é possível aplicar desconto em uma pedido finalizado");
        }

        BigDecimal totalItens = new BigDecimal(0);
        for (PedidoItem pedidoItem : pedido.getPedidoItem()) {
            if (pedidoItem.getProduto().getTipoProduto().equals(TipoProduto.P)) {
                totalItens = totalItens.add(pedidoItem.getProduto().getPreco().multiply(pedidoItem.getQtde()));
            }
        }

        BigDecimal desconto = new BigDecimal(0);
        if (totalItens.compareTo(new BigDecimal(0)) > 0) {
            desconto = totalItens.multiply(percentual.divide(new BigDecimal(100)), MathContext.DECIMAL32);
            pedido.setDescontoTotal(desconto);
            pedido.setPercDesconto(percentual);
            pedidoRepository.save(pedido);
            return desconto;
        }

        throw new RuntimeException("Não foi aplicado desconto, pois a soma total dos itens foi igual a 0");
    }
}

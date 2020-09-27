package br.com.senior.desafiobackend.service.impl;

import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.repository.PedidoRepository;
import br.com.senior.desafiobackend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, UUID>
    implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

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
}

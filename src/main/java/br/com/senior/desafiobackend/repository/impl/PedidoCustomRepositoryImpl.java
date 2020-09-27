package br.com.senior.desafiobackend.repository.impl;

import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.QPedido;
import br.com.senior.desafiobackend.model.filter.PedidoFilter;
import br.com.senior.desafiobackend.repository.PedidoCustomRepository;
import br.com.senior.desafiobackend.util.WhereBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Pedido> filter(PedidoFilter pedidoFilter) {
        QPedido pedido = QPedido.pedido;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.selectFrom(pedido)
                .where(
                        new WhereBooleanBuilder()
                                .optionalAnd(pedidoFilter.getStatusPedido(), () -> pedido.status.eq(pedidoFilter.getStatusPedido()))
                )
                .createQuery()
                .getResultList();
    }
}

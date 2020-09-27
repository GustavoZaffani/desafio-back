package br.com.senior.desafiobackend.repository.impl;

import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.QProduto;
import br.com.senior.desafiobackend.model.filter.ProdutoFilter;
import br.com.senior.desafiobackend.repository.ProdutoCustomRepository;
import br.com.senior.desafiobackend.util.WhereBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProdutoCustomRepositoryImpl implements ProdutoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> filter(ProdutoFilter produtoFilter) {
        QProduto produto = QProduto.produto;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.selectFrom(produto)
                .where(
                        new WhereBooleanBuilder()
                                .optionalAnd(produtoFilter.getNome(), () -> produto.nome.like("%" + produtoFilter.getNome() + "%"))
                                .optionalAnd(produtoFilter.getPreco(), () -> produto.preco.eq(produtoFilter.getPreco()))
                                .optionalAnd(produtoFilter.getTipoProduto(), () -> produto.tipoProduto.eq(produtoFilter.getTipoProduto()))
                )
                .createQuery()
                .getResultList();
    }
}

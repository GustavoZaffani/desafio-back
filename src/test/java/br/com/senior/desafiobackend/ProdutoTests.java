package br.com.senior.desafiobackend;

import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.filter.ProdutoFilter;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ProdutoTests {

    @Autowired
    private ProdutoService produtoService;

    @Test
    void deveInserirProdutoCorretamente() {
        Produto produto = new Produto("Produto Teste", Boolean.FALSE, TipoProduto.P, new BigDecimal(9.9));
        Produto save = produtoService.save(produto);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        assertThat(save.getNome()).isEqualTo(produto.getNome());
    }

    @Test
    void deveDispararExcecaoDasValidacoesDoModel() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        assertThatThrownBy(() -> produtoService.save(produto)).isInstanceOf(TransactionSystemException.class);
    }

    @Test
    void deveRetornarApenasProdutosQueContemONomeTeste() {
        Produto produto = new Produto("Teste", Boolean.TRUE, TipoProduto.P, new BigDecimal(10));
        produtoService.save(produto);

        ProdutoFilter produtoFilter = new ProdutoFilter();
        produtoFilter.setNome("Teste");
        produtoFilter.setPreco(new BigDecimal(10));

        List<Produto> filter = produtoService.filter(produtoFilter);
        assertThat(filter).isNotNull();
        filter.forEach(produto1 -> {
            assertThat(produto1.getNome()).contains(produtoFilter.getNome());
        });
    }
}

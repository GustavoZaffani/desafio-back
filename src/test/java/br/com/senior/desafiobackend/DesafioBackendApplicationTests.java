package br.com.senior.desafiobackend;

import br.com.senior.desafiobackend.ennumeration.StatusPedido;
import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.PedidoItem;
import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.service.PedidoService;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class DesafioBackendApplicationTests {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProdutoService produtoService;

    @Test
    void deveDispararExcecaoQuandoSalvarPedidoComProdutoDesativado() {
        Produto produto = new Produto();
        produto.setNome("Teste");
        produto.setInativo(Boolean.TRUE);
        produto.setTipoProduto(TipoProduto.P);
        produto.setPreco(new BigDecimal(10));
        produtoService.save(produto);

        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setProduto(produto);
        pedidoItem.setQtde(new BigDecimal(10));

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.A);
        pedido.setPedidoItem(Arrays.asList(pedidoItem));

        assertThat(pedidoService.save(pedido)).isNotNull();

        assertThatThrownBy(() -> pedidoService.save(pedido)).isInstanceOf(RuntimeException.class)
                .hasMessage("Não é possível adicionar um produto inativo no pedido");
    }
}

package br.com.senior.desafiobackend;

import br.com.senior.desafiobackend.ennumeration.StatusPedido;
import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import br.com.senior.desafiobackend.model.Pedido;
import br.com.senior.desafiobackend.model.PedidoItem;
import br.com.senior.desafiobackend.model.Produto;
import br.com.senior.desafiobackend.model.filter.PedidoFilter;
import br.com.senior.desafiobackend.service.PedidoService;
import br.com.senior.desafiobackend.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PedidoTests {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProdutoService produtoService;

    @Test
    void deveDispararExcecaoQuandoSalvarPedidoComProdutoInativo() {
        Produto produto = new Produto("Teste", Boolean.TRUE, TipoProduto.P, new BigDecimal(10));
        produtoService.save(produto);

        PedidoItem pedidoItem = new PedidoItem(new BigDecimal(10), produto);
        Pedido pedido = new Pedido(StatusPedido.A, Arrays.asList(pedidoItem));

        assertThatThrownBy(() -> pedidoService.save(pedido)).isInstanceOf(RuntimeException.class)
                .hasMessage("Não é possível adicionar um produto inativo no pedido");
    }

    @Test
    void deveSalvarPedidoQuandoProdutoEstaAtivo() {
        Produto produto = new Produto("Teste", Boolean.FALSE, TipoProduto.P, new BigDecimal(10));
        produtoService.save(produto);

        PedidoItem pedidoItem = new PedidoItem(new BigDecimal(10), produto);
        Pedido pedido = new Pedido(StatusPedido.A, Arrays.asList(pedidoItem));

        Pedido save = pedidoService.save(pedido);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        assertThat(save.getPedidoItem()).contains(pedidoItem);
    }

    @Test
    void deveAplicarDescontoApenasNosItemsDoTipoProduto() {
        Produto produto1 = new Produto("Teste1", Boolean.FALSE, TipoProduto.P, new BigDecimal(200));
        produtoService.save(produto1);
        Produto produto2 = new Produto("Teste2", Boolean.FALSE, TipoProduto.S, new BigDecimal(100));
        produtoService.save(produto2);
        Produto produto3 = new Produto("Teste3", Boolean.FALSE, TipoProduto.P, new BigDecimal(150));
        produtoService.save(produto3);

        PedidoItem pedidoItem1 = new PedidoItem(new BigDecimal(5), produto1); // total item 1000,00
        PedidoItem pedidoItem2 = new PedidoItem(new BigDecimal(8), produto2); // não deve calcular
        PedidoItem pedidoItem3 = new PedidoItem(new BigDecimal(3), produto3); // total item 425,00

        Pedido pedido = new Pedido(StatusPedido.A, Arrays.asList(pedidoItem1, pedidoItem2, pedidoItem3));
        Pedido save = pedidoService.save(pedido);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        BigDecimal descontoPedido = pedidoService.aplicaDescontoPedido(new BigDecimal(10), save);
        assertThat(descontoPedido.compareTo(new BigDecimal(145)) == 0).isTrue();
    }

    @Test
    void deveDispararExceptionNoCalculoDoDescontoQuandoTodosItensSaoDoTipoServico() {
        Produto produto1 = new Produto("Teste1", Boolean.FALSE, TipoProduto.S, new BigDecimal(200));
        produtoService.save(produto1);
        Produto produto2 = new Produto("Teste2", Boolean.FALSE, TipoProduto.S, new BigDecimal(100));
        produtoService.save(produto2);
        Produto produto3 = new Produto("Teste3", Boolean.FALSE, TipoProduto.S, new BigDecimal(150));
        produtoService.save(produto3);

        PedidoItem pedidoItem1 = new PedidoItem(new BigDecimal(11), produto1);
        PedidoItem pedidoItem2 = new PedidoItem(new BigDecimal(22), produto2);
        PedidoItem pedidoItem3 = new PedidoItem(new BigDecimal(18), produto3);

        Pedido pedido = new Pedido(StatusPedido.A, Arrays.asList(pedidoItem1, pedidoItem2, pedidoItem3));

        Pedido save = pedidoService.save(pedido);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        assertThatThrownBy(() -> pedidoService.aplicaDescontoPedido(new BigDecimal(20), save)).isInstanceOf(RuntimeException.class)
                .hasMessage("Não foi aplicado desconto, pois a soma total dos itens foi igual a 0");
    }

    @Test
    void deveDispararExceptionNoCalculoDoDescontoQuandoOPedidoEstaFinalizado() {
        Produto produto1 = new Produto("Teste1", Boolean.FALSE, TipoProduto.S, new BigDecimal(200));
        produtoService.save(produto1);

        PedidoItem pedidoItem1 = new PedidoItem(new BigDecimal(11), produto1);

        Pedido pedido = new Pedido(StatusPedido.F, Arrays.asList(pedidoItem1));

        Pedido save = pedidoService.save(pedido);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();

        assertThatThrownBy(() -> pedidoService.aplicaDescontoPedido(new BigDecimal(20), save)).isInstanceOf(RuntimeException.class)
                .hasMessage("Não é possível aplicar desconto em uma pedido finalizado");
    }

    @Test
    void deveRetornarPedidoAoFiltrarPorStatus() {
        Produto produto = new Produto("Teste1", Boolean.FALSE, TipoProduto.S, new BigDecimal(200));
        produtoService.save(produto);

        PedidoItem pedidoItem = new PedidoItem(new BigDecimal(10), produto);
        Pedido pedido = new Pedido(StatusPedido.A, Arrays.asList(pedidoItem));
        pedidoService.save(pedido);

        PedidoFilter pedidoFilter = new PedidoFilter();
        pedidoFilter.setStatusPedido(StatusPedido.A);

        List<Pedido> filter = pedidoService.filter(pedidoFilter);
        filter.forEach(pedido1 -> assertThat(pedido1.getStatus()).isEqualTo(pedidoFilter.getStatusPedido()));
    }
}

package br.com.senior.desafiobackend.model.filter;

import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFilter {

    private String nome;
    private TipoProduto tipoProduto;
    private BigDecimal preco;
}

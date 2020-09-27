package br.com.senior.desafiobackend.model;

import br.com.senior.desafiobackend.ennumeration.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotEmpty(message = "O campo 'nome' é obrigatório.")
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DETALHES")
    private String detalhes;

    @Column(name = "INATIVO")
    private Boolean inativo;

    @NotNull(message = "O campo 'tipo de produto' é de escolha obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPOPRODUTO", nullable = false)
    private TipoProduto tipoProduto;

    @NotNull(message = "O campo 'preco' é obrigatório")
    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

    public Produto(@NotEmpty(message = "O campo 'nome' é obrigatório.") String nome, Boolean inativo,
                   @NotNull(message = "O campo 'tipo de produto' é de escolha obrigatória") TipoProduto tipoProduto,
                   @NotNull(message = "O campo 'preco' é obrigatório") BigDecimal preco) {
        this.nome = nome;
        this.inativo = inativo;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
    }
}

package br.com.senior.desafiobackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PEDIDOITEM")
public class PedidoItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull(message = "O campo 'quantidade' deve ser informado")
    @Column(name = "QTDE", nullable = false)
    private BigDecimal qtde;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUTO_ID", referencedColumnName = "ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "ID")
    private Pedido pedido;

    public PedidoItem(BigDecimal qtde, Produto produto) {
        this.qtde = qtde;
        this.produto = produto;
    }
}

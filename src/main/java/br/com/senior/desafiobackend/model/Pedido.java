package br.com.senior.desafiobackend.model;

import br.com.senior.desafiobackend.ennumeration.StatusPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo 'status' deve ser escolhido.")
    @Column(name = "STATUS")
    private StatusPedido status;

    @Column(name = "PERCDESCONTO")
    private BigDecimal percDesconto;

    @Column(name = "DESCONTOTOTAL")
    private BigDecimal descontoTotal;

    @NotNull(message = "Deve ser escolhido ao menos 1 produto.")
    @OneToMany(mappedBy = "pedido",
            cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<PedidoItem> pedidoItem;

    public Pedido(StatusPedido status, @NotNull(message = "Deve ser escolhido ao menos 1 produto.") List<PedidoItem> pedidoItem) {
        this.status = status;
        this.pedidoItem = pedidoItem;
    }
}

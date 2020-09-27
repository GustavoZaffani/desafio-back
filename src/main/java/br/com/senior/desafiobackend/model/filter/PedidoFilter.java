package br.com.senior.desafiobackend.model.filter;

import br.com.senior.desafiobackend.ennumeration.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilter {

    private StatusPedido statusPedido;
}

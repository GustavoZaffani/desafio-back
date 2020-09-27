package br.com.senior.desafiobackend.repository;

import br.com.senior.desafiobackend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}

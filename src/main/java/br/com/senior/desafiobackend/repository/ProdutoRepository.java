package br.com.senior.desafiobackend.repository;

import br.com.senior.desafiobackend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}

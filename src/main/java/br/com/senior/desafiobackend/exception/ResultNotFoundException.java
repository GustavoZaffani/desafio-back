package br.com.senior.desafiobackend.exception;

import java.util.UUID;

public class ResultNotFoundException extends Exception {

    public ResultNotFoundException(UUID id) {
        super(String.format("O registro com id %s não foi encontrado", id));
    }
}

package br.com.senior.desafiobackend.exception;

public class ListResultIsEmptyException extends Exception {

    public ListResultIsEmptyException() {
        super("Não foi encontrado nenhum registro.");
    }
}

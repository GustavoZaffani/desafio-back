package br.com.senior.desafiobackend.ennumeration;

public enum StatusPedido {

    A("Aberto"),
    F("Fechado");

    private String status;

    StatusPedido(String tipo) {
        this.status = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

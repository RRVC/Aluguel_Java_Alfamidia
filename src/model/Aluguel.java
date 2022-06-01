package model;

import util.Contador;

import java.time.LocalDate;

public class Aluguel {
    private Integer id;
    private Veiculo veiculo;
    private Cliente cliente;
    private Vendedor vendedor;
    private LocalDate data;

    public Aluguel(Veiculo veiculo, Cliente cliente, Vendedor vendedor, LocalDate data) {
        this.id = Contador.proximoId();
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public LocalDate getData() {
        return data;
    }
}

package model;

import util.Contador;

public class Vendedor extends Pessoa {

    private Double totalVendas;
    public final static Double COMISSAO = 0.01;
    private Double salario;

    public Vendedor(String nome, String cpf, String senha) {
        super(Contador.proximoId(), nome, cpf, senha);
        this.totalVendas = 0.0;
        this.salario = 2000.0;
    }

    public Double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Double totalVendas) {
        this.totalVendas = totalVendas;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
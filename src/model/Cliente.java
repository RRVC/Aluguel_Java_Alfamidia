package model;

import util.Contador;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private List<Veiculo> veiculos = new ArrayList<>();

    public Cliente(String nome, String cpf, String senha) {
        super(Contador.proximoId(), nome, cpf, senha);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
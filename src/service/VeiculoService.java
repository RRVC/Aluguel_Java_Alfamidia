package service;

import exceptions.VeiculoException;
import model.Veiculo;
import repository.RepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class VeiculoService {
    private Scanner sc;
    private RepositoryImpl<Integer, Veiculo> repository = new RepositoryImpl<>();

    public VeiculoService(Scanner sc) {
        this.sc = sc;
        repository.salvar(1, new Veiculo("IJK5500", "GOL", "WOLKS", "VERMELHO", 4500.0, Veiculo.Tipo.CARRO));
        repository.salvar(2, new Veiculo("BFG6957", "PALIO", "FIAT", "CINZA", 6500.0, Veiculo.Tipo.CARRO));
        repository.salvar(3, new Veiculo("LPQ3940", "CRUZE", "CHEVROLET", "PRETO", 1500.0, Veiculo.Tipo.CARRO));
    }

    public void cadastrarVeiculo() {
        sc.nextLine();
        System.out.println("Digite o modelo do veiculo: ");
        String modelo = sc.nextLine();
        System.out.println("Digite a placa do veiculo: ");
        String placa = sc.nextLine();
        System.out.println("Digite a marca do veiculo: ");
        String marca = sc.nextLine();
        System.out.println("Digite a cor do veiculo: ");
        String cor = sc.nextLine();
        System.out.println("Digite o valor do veiculo: ");
        Double valor = sc.nextDouble();
        System.out.println("Escolha a opção para o veiculo: ");
        System.out.println("1 - CARRO");
        System.out.println("2 - MOTO");
        System.out.println("3 - CAMINHAO");
        int opcao = sc.nextInt();
        Veiculo.Tipo tipo = null;
        switch (opcao) {
            case 1:
                tipo = Veiculo.Tipo.CARRO;
                break;
            case 2:
                tipo = Veiculo.Tipo.MOTO;
                break;
            case 3:
                tipo = Veiculo.Tipo.CAMINHAO;
                break;
        }

        Veiculo veiculo = new Veiculo(placa, modelo, marca, cor, valor, tipo);
        repository.salvar(veiculo.getId(), veiculo);

    }

    public void mostrarTodos() {
        for (Veiculo veiculo : repository.buscarTodos()) {
            System.out.println(
                    veiculo.getId() + " - " +
                            veiculo.getMarca() + ", " +
                            veiculo.getModelo() + ", " +
                            veiculo.getCor() + ", R$ " +
                            veiculo.getValor()
            );
        }
    }

    public void mostrarTodosLivres() {
        for (Veiculo veiculo : repository.buscarTodos()) {
            if (veiculo.getStatus() == Veiculo.Status.LIVRE) {
                System.out.println(veiculo.getId() + " - " + veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor() + ", R$ " + veiculo.getValor());
            }
        }
    }

    public Veiculo buscarPorId(Integer opcaoVeiculo) {
        Veiculo veiculo = this.repository.buscaPorId(opcaoVeiculo);
        if (veiculo == null) {
            throw new VeiculoException("Veiculo não encontrado! ID: " + opcaoVeiculo);
        } else {
            return veiculo;
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        //Método salvar() salva e atualiza. Se o ID ja existir, ele atualia. Se não existir, ele salva.
        this.repository.salvar(veiculo.getId(), veiculo);
    }

    public void buscarVeiculosAlugados() {
        List<Veiculo> veiculos = this.repository.buscarTodos();
        for (Veiculo veiculoFor : veiculos) {
            if (veiculoFor.getStatus() == Veiculo.Status.ALUGADO) {
                System.out.println("Alugado para o cliente " + veiculoFor.getCliente().getNome() + ". " + veiculoFor.getId() + " - " + veiculoFor.getMarca() + ", " + veiculoFor.getModelo() + ", " + veiculoFor.getCor());
            }
        }
    }
}
package service;

import exceptions.ClienteException;
import model.Cliente;
import model.Veiculo;
import repository.RepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class ClienteService {
    private Scanner sc;
    private RepositoryImpl<Integer, Cliente> repository = new RepositoryImpl<>();

    public ClienteService(Scanner sc) {
        this.sc = sc;
        Cliente cliente = new Cliente("Fulano", "10", "0000");
        repository.salvar(cliente.getId(), cliente);
    }

    public Cliente cadastrarCliente() throws ClienteException {
        sc.nextLine();
        System.out.println("Digite o nome do cliente:");
        String nome = sc.nextLine();
        System.out.println("Digite o cpf do cliente:");
        String cpf = sc.nextLine();
        System.out.println("Cadastre uma senha de acesso:");
        String senha = sc.nextLine();

        List<Cliente> clientes = this.repository.buscarTodos();

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                throw new ClienteException("CPF já cadastrado.");
            }
        }
        Cliente cliente = new Cliente(nome, cpf, senha);
        repository.salvar(cliente.getId(), cliente);

        return cliente;
    }

    public Cliente buscarCPF(String cpf) throws ClienteException {
        Cliente cliente = null;
        List<Cliente> clientes = repository.buscarTodos();
        for (Cliente clienteFor : clientes) {
            if (clienteFor.getCpf().equals(cpf)) {
                cliente = clienteFor;
            }
        }
        if (cliente == null) {
            throw new ClienteException("Cliente inválido.");
        }
        return cliente;
    }

    public Cliente tratarEntradaCliente(String entrada) throws ClienteException {
        entrada = entrada.toLowerCase();
        entrada = entrada.replace("ã", "a");
        if (entrada.equals("nao") || entrada.equals("n")) {
            return this.cadastrarCliente();
        } else {
            Cliente cliente = this.buscarCPF(entrada);
            boolean continua = true;
            Integer tentativas = 3;
            do {
                System.out.println("Digite sua senha:");
                String senha = sc.next();
                if (!cliente.getSenha().equals(senha)) {
                    tentativas--;
                    System.out.println("Senha incorreta! Tente novamente.\nTentativas restantes " + tentativas + "/3");
                    if (tentativas == 0) {
                        throw new ClienteException("Excedido número de tentativas!");
                    }
                } else {
                    continua = false;
                }
            } while (continua);
            return cliente;
        }
    }

    public void atualizarCliente(Cliente cliente) {
        this.repository.salvar(cliente.getId(), cliente);
    }

    public void mostrarVeiculosAlugados(Integer id) {
        Cliente clienteRepository = this.repository.buscaPorId(id);

        for (Veiculo veiculoFor : clienteRepository.getVeiculos()) {
            System.out.println(
                    veiculoFor.getId() + " - " +
                            veiculoFor.getMarca() + ", " +
                            veiculoFor.getModelo() + ", " +
                            veiculoFor.getCor()
            );
        }
    }
}
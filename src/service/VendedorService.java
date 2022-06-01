package service;

import exceptions.VendedorException;
import model.Vendedor;
import repository.RepositoryImpl;

import java.util.Scanner;


public class VendedorService {
    private Scanner sc;
    private RepositoryImpl<Integer, Vendedor> repository = new RepositoryImpl<>();

    public VendedorService(Scanner sc) {
        this.sc = sc;
        Vendedor vendedor1 = new Vendedor("João", "111-222-333-44", "1111");
        Vendedor vendedor2 = new Vendedor("Maria", "222-333-444.55", "2222");
        repository.salvar(vendedor1.getId(), vendedor1);
        repository.salvar(vendedor2.getId(), vendedor2);
    }

    public void mostrarTodos() {
        for (Vendedor vendedor : repository.buscarTodos()) {
            System.out.println(vendedor.getId() + " - " + vendedor.getNome());
        }
    }

    public Vendedor buscarVendedorPorId(Integer id) {
        Vendedor vendedor = repository.buscaPorId(id);
        if (vendedor == null) {
            throw new VendedorException("Opção de vendedor invalida. ID: " + id);
        } else {
            return vendedor;
        }
    }


    public Vendedor login() {
        System.out.println("Digite uma opção para se identificar:");
        this.mostrarTodos();
        Integer idVendedor = sc.nextInt();
        Vendedor vendedor = this.buscarVendedorPorId(idVendedor);
        boolean continua = true;
        Integer tentativas = 3;
        do {
            System.out.println("Digite sua senha");
            String senhaVendedor = sc.next();
            if (!vendedor.getSenha().equals(senhaVendedor)) {
                tentativas--;
                System.out.println("Senha incorreta! Tente novamente.\nTentativas restantes " + tentativas + "/3");
                if (tentativas ==0){
                    throw new VendedorException("Excedido número de tentativas");
                }
            } else {
                continua = false;
            }
        } while (continua);
        return vendedor;
    }

    public void cadastrarVendedor() {
        sc.nextLine();
        System.out.println("Digite o nome do vendedor: ");
        String nome = sc.nextLine();
        System.out.println("Digite o CPF referente ao vendedor cadastrado: ");
        String cpf = sc.nextLine();
        System.out.println("Cadastre a senha de acesso do vendedor: ");
        String senha = sc.nextLine();

        Vendedor vendedor = new Vendedor(nome, cpf, senha);
        repository.salvar(vendedor.getId(), vendedor);
    }

    public void cadastrarVenda(Integer idVendedor, Double valorVenda) {
        Vendedor vendedor = repository.buscaPorId(idVendedor);
        vendedor.setTotalVendas(vendedor.getTotalVendas() + valorVenda);
        this.repository.salvar(vendedor.getId(), vendedor);
    }

    public void mostrarSalario(Integer idVendedor) {
        Vendedor vendedor = this.repository.buscaPorId(idVendedor);
        System.out.println("Salário atual: " + vendedor.getSalario());
        Double salarioTotal = vendedor.getSalario() + (vendedor.getTotalVendas() * Vendedor.COMISSAO);
        System.out.println("Seu salário com comissão: " + salarioTotal);
        System.out.println("Valor total de vendas: " + vendedor.getTotalVendas());
    }
}
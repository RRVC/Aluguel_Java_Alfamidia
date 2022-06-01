package main;

import exceptions.ClienteException;
import exceptions.VeiculoException;
import exceptions.VendedorException;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import service.AluguelService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;
import util.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        VeiculoService veiculoService = new VeiculoService(sc);
        ClienteService clienteService = new ClienteService(sc);
        VendedorService vendedorService = new VendedorService(sc);
        AluguelService aluguelService = new AluguelService(sc);
        boolean continua = true;

        do {
            try {
                Menu.bemVindo();
                Menu.identificar();

                int identificacao = sc.nextInt();
//          =====================================================================
//          INICIO DO MENU DO CLIENTE
                if (identificacao == 1) {
                    Menu.menuCliente1();
                    String cadastro = sc.next();
                    Cliente cliente = clienteService.tratarEntradaCliente(cadastro);
                    Menu.menuCliente2();
                    int opcao = sc.nextInt();
                    if (opcao == 1) {   // MÉTODOS DE ALUGUEL PARA USUARIOS CADASTRADOS
                        System.out.println("\nEscolha uma opção:");
                        veiculoService.mostrarTodosLivres();
                        Integer opcaoVeiculo = sc.nextInt();
                        Veiculo veiculo = veiculoService.buscarPorId(opcaoVeiculo);
                        System.out.println("\nEscolha qual o vendedor que lhe atendeu:");
                        vendedorService.mostrarTodos();
                        Integer opcaoVendedor = sc.nextInt();
                        Vendedor vendedor = vendedorService.buscarVendedorPorId(opcaoVendedor);
                        aluguelService.salvar(cliente, vendedor, veiculo);
                        veiculo.setStatus(Veiculo.Status.ALUGADO);
                        veiculo.setCliente(cliente);
                        veiculoService.atualizarVeiculo(veiculo);
                        cliente.getVeiculos().add(veiculo);
                        clienteService.atualizarCliente(cliente);
                        vendedorService.cadastrarVenda(vendedor.getId(), veiculo.getValor());
                    } else if (opcao == 2) {    // MÉTODOS DE DEVOLUÇÃO DE VEICULOS
                        System.out.println("Escolha uma opção referente ao veiculo:");
                        clienteService.mostrarVeiculosAlugados(cliente.getId());
                        Integer opcaoDevolucao = sc.nextInt();
                        Veiculo veiculo = veiculoService.buscarPorId(opcaoDevolucao);
                        cliente.getVeiculos().remove(veiculo);
                        veiculo.setStatus(Veiculo.Status.LIVRE);
                        veiculo.setCliente(null);
                        veiculoService.atualizarVeiculo(veiculo);
                        clienteService.atualizarCliente(cliente);
                    }
                    //          =====================================================================
                    //          INICIO DO MENU DO VENDEDOR
                } else if (identificacao == 2) {
                    Vendedor vendedor = vendedorService.login();
                    Menu.menuVendedor();
                    int opcaoVendedor = sc.nextInt();
                    if (opcaoVendedor == 1) {   // MÉTODO PARA MOSTRAR SALÁRIO ATUAL + COMISSÃO
                        vendedorService.mostrarSalario(vendedor.getId());
                    } else if (opcaoVendedor == 2) {    // MÉTODO PARA VER CARROS ALUGADOS PELO VENDEDOR
                        veiculoService.buscarVeiculosAlugados();
                    }
                    //          =====================================================================
                    //          INICIO DO MENU DO ADMINISTRADOR
                } else if (identificacao == 3) {
                    Menu.menuAdmin();
                    int opcao = sc.nextInt();
                    if (opcao == 1) {
                        veiculoService.cadastrarVeiculo();
                    } else if (opcao == 2) {
                        vendedorService.cadastrarVendedor();
                    } else if (opcao == 3) {
                        aluguelService.mostrarTotalVendas();
                    }
                } else if (identificacao == 0) {
                    continua = false;
                    System.out.println("Programa encerrado.");
                    veiculoService.mostrarTodos();
                }
//                CATCH DE EXCEPTIONS
            } catch (InputMismatchException e) {
                System.out.println("Opção invalida!\nTente novamente...");
                sc.nextLine();
            } catch (ClienteException | VeiculoException | VendedorException e) {
                System.out.println(e.getMessage());
            } finally {
                Thread.sleep(2000);
            }
        } while (continua);

    }

}
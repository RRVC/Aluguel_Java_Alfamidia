package util;

public class Menu {

    public static void bemVindo() {
        System.out.println("==============================================");
        System.out.println("[Bem vindo ao sistema de aluguel de veiculos]");
        System.out.println("==============================================");
    }

    public static void identificar() {
        System.out.println("\nDigite uma opção:");
        System.out.println("1 - Cliente.");
        System.out.println("2 - Vendedor.");
        System.out.println("3 - Administrador.");
        System.out.println("0 - Sair.");
    }

    public static void menuCliente1() {
        System.out.println("\nVocê já possui cadastro?");
        System.out.println("Sim - Digite seu CPF");
        System.out.println("Não - Digite 'não'");
    }

    public static void menuCliente2() {
        System.out.println("\nDigite uma opção:");
        System.out.println("1 - Alugar um veiculo");
        System.out.println("2 - Devolver um veiculo");
    }

    public static void alugarVeiculo() {
        System.out.println("\nDigite uma opção:");
        System.out.println("1 - Alugar um veiculo");
        System.out.println("2 - Devolver um veiculo");
    }

    public static void menuVendedor() {
        System.out.println("\nDigite uma opção:");
        System.out.println("1 - Ver seu salário atual + comissão");
        System.out.println("2 - Ver carros alugados");
    }

    public static void menuAdmin() {
        System.out.println("\nDigite uma opção:");
        System.out.println("1 - Cadastrar veiculo");
        System.out.println("2 - Cadastrar vendedor");
        System.out.println("3 - Ver total de vendas");

    }

}
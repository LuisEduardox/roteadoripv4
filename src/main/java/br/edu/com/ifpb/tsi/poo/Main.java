package br.edu.com.ifpb.tsi.poo;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Roteador roteador;

    public static void main(String[] args) {
        System.out.print("Digite o nome do roteador: ");
        String nomeRoteador = scanner.nextLine();
        roteador = new Roteador(nomeRoteador);

        int opcao = 0;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("Sistema encerrado.");
    }

    private static void exibirMenu() {
        System.out.println("\n========== MENU ROTEADOR " + roteador.getNome() + " ==========");
        System.out.println("1. Cadastrar Interface");
        System.out.println("2. Cadastrar Rota");
        System.out.println("3. Alterar Rota");
        System.out.println("4. Remover Rota");
        System.out.println("5. Exibir Tabela de Rotas");
        System.out.println("6. Rotear IP");
        System.out.println("7. Resetar Tabela de Rotas");
        System.out.println("8. Alterar Modo de Exibição");
        System.out.println("9. Exibir Interfaces");
        System.out.println("0. Sair");
        System.out.println("=====================================");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        String entrada = scanner.nextLine();
        int opcao = Integer.parseInt(entrada);
        return opcao;
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarInterface();
                break;
            case 2:
                cadastrarRota();
                break;
            case 3:
                alterarRota();
                break;
            case 4:
                removerRota();
                break;
            case 5:
                exibirTabelaRotas();
                break;
            case 6:
                rotearIP();
                break;
            case 7:
                resetarTabela();
                break;
            case 8:
                alterarModoExibicao();
                break;
            case 9:
                exibirInterfaces();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarInterface() {
        System.out.println("\n--- Cadastrar Interface ---");
        System.out.print("Nome da interface: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço IP: ");
        String ip = scanner.nextLine();

        Interface novaInterface = new Interface(nome, ip);
        if (roteador.cadastrarInterface(novaInterface)) {
            System.out.println("Interface cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Interface já existe!");
        }
    }

    private static void cadastrarRota() {
        System.out.println("\n--- Cadastrar Rota ---");
        
        if (roteador.getInterfaces().isEmpty()) {
            System.out.println("Erro: Cadastre uma interface primeiro!");
            return;
        }

        System.out.print("Destino (IP): ");
        String destino = scanner.nextLine();
        System.out.print("Máscara: ");
        String mascara = scanner.nextLine();
        System.out.print("Gateway: ");
        String gateway = scanner.nextLine();
        System.out.print("Nome da Interface: ");
        String nomeInterface = scanner.nextLine();

        Interface interfaceEncontrada = roteador.buscarInterfaceNome(nomeInterface);
        if (interfaceEncontrada == null) {
            System.out.println("Erro: Interface não encontrada!");
            return;
        }

        Rota novaRota = new Rota(destino, mascara, gateway, interfaceEncontrada);
        if (roteador.cadastrarRota(novaRota)) {
            System.out.println("Rota cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Rota já existe!");
        }
    }

    private static void alterarRota() {
        System.out.println("\n--- Alterar Rota ---");
        System.out.println("Digite os dados da rota a ser alterada:");
        
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Máscara: ");
        String mascara = scanner.nextLine();
        System.out.print("Gateway: ");
        String gateway = scanner.nextLine();
        System.out.print("Nome da Interface: ");
        String nomeInterface = scanner.nextLine();

        Interface interfaceEncontrada = roteador.buscarInterfaceNome(nomeInterface);
        if (interfaceEncontrada == null) {
            System.out.println("Erro: Interface não encontrada!");
            return;
        }

        Rota rotaAlterada = new Rota(destino, mascara, gateway, interfaceEncontrada);
        roteador.alterarRota(rotaAlterada);
        System.out.println("Rota alterada com sucesso!");
    }

    private static void removerRota() {
        System.out.println("\n--- Remover Rota ---");
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Máscara: ");
        String mascara = scanner.nextLine();
        System.out.print("Gateway: ");
        String gateway = scanner.nextLine();

        Rota rotaRemover = new Rota(destino, mascara, gateway, null);
        roteador.removerRota(rotaRemover);
        System.out.println("Rota removida!");
    }

    private static void exibirTabelaRotas() {
        System.out.println("\n--- Tabela de Rotas ---");
        System.out.println(roteador.exibiTabelaDeRotas());
    }

    private static void rotearIP() {
        System.out.println("\n--- Rotear IP ---");
        System.out.print("Digite o IP de destino: ");
        String ipDestino = scanner.nextLine();

        Rota rota = roteador.rotear(ipDestino);
        if (rota != null) {
            System.out.println("Rota encontrada:");
            System.out.println(rota);
        } else {
            System.out.println("Nenhuma rota encontrada para o IP: " + ipDestino);
        }
    }

    private static void resetarTabela() {
        System.out.println("\n--- Resetar Tabela ---");
        System.out.print("Tem certeza? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            roteador.resetarTabela();
            System.out.println("Tabela de rotas resetada!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void alterarModoExibicao() {
        System.out.println("\n--- Alterar Modo de Exibição ---");
        System.out.println("1. Exibir com Máscara");
        System.out.println("2. Exibir com CIDR");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        if (opcao == 1) {
            roteador.setModoExibicao(ModoExibicao.MASCARA);
            System.out.println("Modo alterado para MÁSCARA");
        } else if (opcao == 2) {
            roteador.setModoExibicao(ModoExibicao.CIDR);
            System.out.println("Modo alterado para CIDR");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void exibirInterfaces() {
        System.out.println("\n--- Interfaces Cadastradas ---");
        if (roteador.getInterfaces().isEmpty()) {
            System.out.println("Nenhuma interface cadastrada.");
        } else {
            for (Interface i : roteador.getInterfaces()) {
                System.out.println(i);
                System.out.println("---");
            }
        }
    }
}
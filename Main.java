import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MapaCidades mapa = new MapaCidades();

    public static void main(String[] args) {
        // Carrega os dados fixos do sistema
        inicializarDados();

        int opcao;
        do {
            exibirMenuPrincipal();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        listarTodasAsCidades();
                        break;
                    case 2:
                        verDetalhesDeUmaCidade();
                        break;
                    case 3:
                        encontrarCaminhoEntreCidades();
                        break;
                    case 0:
                        System.out.println("Obrigado por usar o sistema. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); 
                opcao = -1; 
            }

            if (opcao != 0) {
                pressioneEnterParaContinuar();
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Consulta de Cidades e Rotas ---");
        System.out.println("1. Listar todas as cidades");
        System.out.println("2. Ver detalhes e rotas diretas de uma cidade");
        System.out.println("3. Encontrar caminho entre duas cidades");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void listarTodasAsCidades() {
        System.out.println("\n--- Cidades Disponíveis no Sistema ---");
        mapa.getCidades().forEach(System.out::println);
    }

    private static void verDetalhesDeUmaCidade() {
        System.out.println("\n--- Consultar Detalhes da Cidade ---");
        Cidade cidadeEscolhida = escolherCidade("Escolha o número da cidade para ver os detalhes:");
        
        if (cidadeEscolhida != null) {
            System.out.println("\nDetalhes para: " + cidadeEscolhida.toString());
            mapa.listarConexoes(cidadeEscolhida);
        }
    }

    private static void encontrarCaminhoEntreCidades() {
        System.out.println("\n--- Encontrar Caminho de um lugar a outro ---");
        
        Cidade origem = escolherCidade("Escolha a cidade de ORIGEM:");
        if (origem == null) return; // O usuário cancelou ou errou

        Cidade destino = escolherCidade("Agora, escolha a cidade de DESTINO:");
        if (destino == null) return;

        if (origem.equals(destino)) {
            System.out.println("A cidade de origem e destino são a mesma. O caminho é apenas a própria cidade.");
            return;
        }

        List<Cidade> caminho = mapa.buscarCaminho(origem, destino);
        
        if (caminho.isEmpty()) {
            System.out.println("Não foi encontrado um caminho entre " + origem.getNome() + " e " + destino.getNome() + ".");
        } else {
            System.out.println("\nCaminho encontrado:");
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print(caminho.get(i).getNome() + (i == caminho.size() - 1 ? "" : " -> "));
            }
            System.out.println();
        }
    }
    
    // Método auxiliar unificado para escolher uma cidade de uma lista numerada
    private static Cidade escolherCidade(String mensagem) {
        List<Cidade> cidadesList = new ArrayList<>(mapa.getCidades());
        if (cidadesList.isEmpty()) {
            System.out.println("Nenhuma cidade cadastrada no sistema.");
            return null;
        }

        System.out.println("\nCidades disponíveis:");
        for (int i = 0; i < cidadesList.size(); i++) {
            System.out.println((i + 1) + ". " + cidadesList.get(i).getNome());
        }

        try {
            System.out.print(mensagem + " ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha > 0 && escolha <= cidadesList.size()) {
                return cidadesList.get(escolha - 1);
            } else {
                System.out.println("Escolha inválida.");
                return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
            scanner.nextLine();
            return null;
        }
    }

    private static void pressioneEnterParaContinuar() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void inicializarDados() {
        Cidade c1 = new Cidade("São Sebastião do Paraíso", "MG", 71515);
        Cidade c2 = new Cidade("Itamogi", "MG", 10323);
        Cidade c3 = new Cidade("Monte Santo de Minas", "MG", 21511);
        Cidade c4 = new Cidade("Ribeirão Preto", "SP", 698259);
        Cidade c5 = new Cidade("Franca", "SP", 358539);
        Cidade c6 = new Cidade("Passos", "MG", 115968);

        mapa.adicionarCidade(c1);
        mapa.adicionarCidade(c2);
        mapa.adicionarCidade(c3);
        mapa.adicionarCidade(c4);
        mapa.adicionarCidade(c5);
        mapa.adicionarCidade(c6);
        
        mapa.conectarCidades(c1, c2, 28);  // Paraíso <-> Itamogi
        mapa.conectarCidades(c1, c6, 75);  // Paraíso <-> Passos
        mapa.conectarCidades(c2, c3, 22);  // Itamogi <-> Monte Santo
        mapa.conectarCidades(c4, c5, 80);  // Ribeirão Preto <-> Franca
        mapa.conectarCidades(c1, c5, 95);  // Paraíso <-> Franca
    }
}
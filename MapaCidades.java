
import java.util.*;

public class MapaCidades {
    private Set<Cidade> cidades;
    private Map<Cidade, Set<Rota>> mapa;

    public MapaCidades() {
        this.cidades = new TreeSet<>();
        this.mapa = new HashMap<>();
    }

    public void adicionarCidade(Cidade cidade) {
        cidades.add(cidade);
        mapa.put(cidade, new HashSet<>());
    }

    public void conectarCidades(Cidade origem, Cidade destino, double distancia) {
        if (!cidades.contains(origem) || !cidades.contains(destino)) {
            System.out.println("Erro: Uma ou ambas as cidades não estão cadastradas.");
            return;
        }
        mapa.get(origem).add(new Rota(destino, distancia));
        mapa.get(destino).add(new Rota(origem, distancia));
    }

    public void listarConexoes(Cidade cidade) {
        Set<Rota> rotas = mapa.get(cidade);
        if (rotas == null || rotas.isEmpty()) {
            System.out.println("Nenhuma conexão encontrada para esta cidade.");
        } else {
            System.out.println("Rotas diretas (conexões):");
            rotas.forEach(System.out::println);
        }
    }

    public List<Cidade> buscarCaminho(Cidade origem, Cidade destino) {
        if (!cidades.contains(origem) || !cidades.contains(destino)) {
            return Collections.emptyList();
        }
        Queue<Cidade> fila = new LinkedList<>();
        Set<Cidade> visitados = new HashSet<>();
        Map<Cidade, Cidade> predecessores = new HashMap<>();
        fila.add(origem);
        visitados.add(origem);
        predecessores.put(origem, null);
        while (!fila.isEmpty()) {
            Cidade atual = fila.poll();
            if (atual.equals(destino)) {
                LinkedList<Cidade> caminho = new LinkedList<>();
                Cidade passo = destino;
                while (passo != null) {
                    caminho.addFirst(passo);
                    passo = predecessores.get(passo);
                }
                return caminho;
            }
            for (Rota rota : mapa.get(atual)) {
                Cidade vizinho = rota.getDestino();
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    fila.add(vizinho);
                    predecessores.put(vizinho, atual);
                }
            }
        }
        return Collections.emptyList();
    }

    public Set<Cidade> getCidades() {
        return this.cidades;
    }
}
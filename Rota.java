
public class Rota {
    private Cidade destino;
    private double distancia;

    public Rota(Cidade destino, double distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    // Getters
    public Cidade getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return "-> " + destino.getNome() + " (" + destino.getEstado() + ") | Distância: " + distancia + " km";
    }
}
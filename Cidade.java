
import java.util.Objects;

public class Cidade implements Comparable<Cidade> {
    private String nome;
    private String estado;
    private int populacao;

    public Cidade(String nome, String estado, int populacao) {
        this.nome = nome;
        this.estado = estado;
        this.populacao = populacao;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getPopulacao() {
        return populacao;
    }

    /**
     * Define a ordem natural das cidades como sendo a ordem alfabética de seus nomes.
     * Essencial para o funcionamento do TreeSet.
     */
    @Override
    public int compareTo(Cidade outra) {
        return this.nome.compareTo(outra.getNome());
    }

    /**
     * Duas cidades são consideradas iguais se tiverem o mesmo nome e estado.
     * Essencial para que a cidade funcione como chave em um HashMap e em um HashSet.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return nome.equals(cidade.nome) && estado.equals(cidade.estado);
    }

    /**
     * Gera um código hash baseado nos mesmos campos usados no método equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, estado);
    }

    @Override
    public String toString() {
        return nome + " (" + estado + ") - População: " + populacao;
    }
}
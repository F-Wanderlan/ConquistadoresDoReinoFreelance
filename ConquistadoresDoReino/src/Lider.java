import java.util.HashMap;
import java.util.Map;

public class Lider {

    private String nome;
    private Reino reino;

    private Map<String, RelacaoLider> relacoes;

    public Lider(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do líder não pode ser nulo ou vazio");
        }
        this.nome = nome;
        this.relacoes = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do líder não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public Reino getReino() {
        return reino;
    }

    public void setReino(Reino reino) {
        this.reino = reino;
    }

    public Map<String, RelacaoLider> getRelacoes() {
        return relacoes;
    }

    // Métodos adicionais para manipulação das relações, como adicionarRelacao(), removerRelacao(), etc.
}

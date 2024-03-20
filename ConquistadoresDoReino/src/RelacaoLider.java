import java.util.Objects;

public class RelacaoLider {

    private Lider lider1;
    private String relacao;
    private Lider lider2;

    /**
     * Método para melhorar a relação entre os líderes.
     * Atualiza a relação para o próximo nível.
     * Caso a relação já esteja no máximo, imprime uma mensagem indicando isso.
     * @param atual A relação atual entre os líderes.
     */
    public void melhorarRelacao(RelacaoLider atual) {
        switch (atual.relacao) {
            case "Ruim":
                atual.setRelacao("Neutra");
                break;
            case "Neutra":
                atual.setRelacao("Boa");
                break;
            default:
                System.out.println("Relação já está no máximo.");
        }
    }

    /**
     * Método para reduzir a relação entre os líderes.
     * Atualiza a relação para o nível anterior.
     * Caso a relação já esteja no mínimo, imprime uma mensagem indicando isso.
     * @param atual A relação atual entre os líderes.
     */
    public void reduzirRelacao(RelacaoLider atual) {
        switch (atual.relacao) {
            case "Neutra":
                atual.setRelacao("Ruim");
                break;
            case "Boa":
                atual.setRelacao("Neutra");
                break;
            default:
                System.out.println("Relação está no mínimo.");
        }
    }

    /**
     * Método para doar influência de um líder para outro.
     * Verifica se a quantidade de influência é válida antes de realizar a transferência.
     * Após a doação, a relação entre os líderes é melhorada.
     * @param quantidadeInfluencia A quantidade de influência a ser doada.
     * @param atual A relação atual entre os líderes.
     */
    public void doarInfluencia(int quantidadeInfluencia, RelacaoLider atual) {
        if (quantidadeInfluencia <= 0 && quantidadeInfluencia >= atual.lider1.getReino().getInfluencia()) {
            System.out.println("Quantidade de influência inválida para doação.");
            return;
        }

        // Transferir a influência do reino doador para o reino receptor
        atual.getLider2().getReino().adicionarInfluencia(quantidadeInfluencia);
        atual.getLider1().getReino().removerInfluencia(quantidadeInfluencia);

        // Melhorar a relação entre os líderes associados a esses reinos
        melhorarRelacao(atual);

        System.out.println("Doação de influência concluída. Relação melhorada entre " +
                atual.getLider1().getNome() + " e " + atual.getLider2().getNome());
    }

    // Getters e setters
    public Lider getLider1() {
        return lider1;
    }

    public void setLider1(Lider lider1) {
        this.lider1 = lider1;
    }

    public String getRelacao() {
        return relacao;
    }

    public void setRelacao(String relacao) {
        this.relacao = relacao;
    }

    public Lider getLider2() {
        return lider2;
    }

    public void setLider2(Lider lider2) {
        this.lider2 = lider2;
    }
}

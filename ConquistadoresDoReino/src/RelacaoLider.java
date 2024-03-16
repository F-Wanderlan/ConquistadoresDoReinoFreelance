import java.util.List;
import java.util.Map;

public class RelacaoLider {

    private Lider lider1;
    private String relacao;
    private Lider lider2;



    public void melhorarRelacao(RelacaoLider atual){
        switch (atual.relacao){
            case "Ruim":
                atual.setRelacao("Neutra");
                break;
            case "Neutra":
                atual.setRelacao("Boa");
                break;
            default:
                System.out.println("Relação já está no maximo");
        }

    }

    public void reduzirRelacao(RelacaoLider atual){
        switch (atual.relacao){
            case "Neutra":
                atual.setRelacao("Ruim");
                break;
            case "Boa":
                atual.setRelacao("Neutra");
                break;
            default:
                System.out.println("Relação está no minimo");
        }
    }


    public void doarInfluencia(int quantidadeInfluencia, RelacaoLider atual){
        if (quantidadeInfluencia <= 0) {
            System.out.println("Quantidade de influência inválida para doação");
            return;
        }

        // 1. Transferir a influência do reino doador para o reino receptor
        atual.getLider2().getReino().setInfluencia(atual.getLider2().getReino().getInfluencia() + quantidadeInfluencia);
        atual.getLider1().getReino().setInfluencia(atual.getLider1().getReino().getInfluencia() - quantidadeInfluencia);

        // 2. Melhorar a relação entre os líderes associados a esses reinos
        melhorarRelacao(atual);

        System.out.println("Doação de influência concluída. Relação melhorada entre " + atual.getLider1().getNome() + " e " + atual.getLider2().getNome());
    }


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

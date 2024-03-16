import java.util.HashMap;
import java.util.Map;

/**
 * A classe Reino representa uma entidade que contém informações sobre um reino em um sistema.
 * Cada reino tem um líder associado e possui atributos como nome, influência, quantidade de soldados,
 * quantidade de serventes, reputação, entre outros.
 */
public class Reino {

    private Lider lider;

    private String nome;
    private int influencia, soldados, soldadosAtk, soldadosDef, serventes, reputacao;


    public Reino(String nome, int influencia, int soldados, int soldadosAtk, int soldadosDef, int serventes) {
        this.nome = nome;
        this.influencia = influencia;
        this.soldados = soldados;
        this.soldadosAtk = soldadosAtk;
        this.soldadosDef = soldadosDef;
        this.serventes = serventes;
    }

    /**
     * Método para comprar soldados para o reino.
     * @param influenciaGasta A quantidade de influência gasta na compra de soldados.
     * @param reino O reino para o qual os soldados serão comprados.
     */
    public void comprarSoldado(int influenciaGasta, Reino reino){
        if (influenciaGasta < 3 || influenciaGasta > reino.getInfluencia()){
            System.out.println("Quantidade de influência inválida para comprar soldado");
        }
        else{
            int soldadosComprados = influenciaGasta / 3;
            reino.setSoldados(reino.getSoldados() + soldadosComprados);
            reino.setInfluencia(reino.getInfluencia() - influenciaGasta);
        }
    }

    /**
     * Método para comprar serventes para o reino.
     * @param influenciaGasta A quantidade de influência gasta na compra de serventes.
     * @param reino O reino para o qual os serventes serão comprados.
     */
    public void comprarServente(int influenciaGasta, Reino reino){
        if (influenciaGasta < 3 || influenciaGasta > reino.getInfluencia()){
            System.out.println("Quantidade de influência inválida para comprar servente");
        }
        else{
            int serventesComprados = influenciaGasta / 3;
            reino.setServentes(reino.getServentes() + serventesComprados);
            reino.setInfluencia(reino.getInfluencia() - influenciaGasta);
        }
    }

    /**
     * Método para definir a quantidade de soldados atacantes em um ataque.
     * @param atacantes A quantidade de soldados atacantes.
     * @param reino O reino que está realizando o ataque.
     */
    public void soldadosAtacantes(int atacantes, Reino reino){
        if (atacantes < 0 || atacantes > reino.getSoldados()) {
            System.out.println("Quantidade de soldados inválida para os atacantes");
        } else {
            reino.setSoldadosAtk(atacantes);
            reino.setSoldados(reino.getSoldados() - atacantes);
        }
    }

    /**
     * Método para definir a quantidade de soldados defensores em uma defesa.
     * @param defensores A quantidade de soldados defensores.
     * @param reino O reino que está se defendendo.
     */
    public void soldadosDefensores(int defensores, Reino reino){
        if (defensores < 0 || defensores > reino.getSoldados()) {
            System.out.println("Quantidade de soldados inválida para os defensores");
        } else {
            reino.setSoldadosDef(defensores);
            reino.setSoldados(reino.getSoldados() - defensores); // Subtrai a quantidade de soldados defensores do total de soldados
        }
    }

    /**
     * Método para gerar influência para o reino com base no número de serventes.
     * @param serventes A quantidade de serventes no reino.
     */
    public void gerarInfluência(int serventes){
        int influenciaGerada = serventes; // Cada servente contribui com 1 de influência
        this.influencia += influenciaGerada;
        System.out.println("Influência gerada: " + influenciaGerada);
    }

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getInfluencia() {
        return influencia;
    }

    public void setInfluencia(int influencia) {
        this.influencia = influencia;
    }

    public int getSoldados() {
        return soldados;
    }

    public void setSoldados(int soldados) {
        this.soldados = soldados;
    }

    public int getSoldadosAtk() {
        return soldadosAtk;
    }

    public void setSoldadosAtk(int soldadosAtk) {
        this.soldadosAtk = soldadosAtk;
    }

    public int getSoldadosDef() {
        return soldadosDef;
    }

    public void setSoldadosDef(int soldadosDef) {
        this.soldadosDef = soldadosDef;
    }

    public int getServentes() {
        return serventes;
    }

    public void setServentes(int serventes) {
        this.serventes = serventes;
    }

    public int getReputacao() {
        return reputacao;
    }

    public void setReputacao(int reputacao) {
        this.reputacao = reputacao;
    }
}

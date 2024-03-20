import java.util.ArrayList;
import java.util.List;

public class Turno {
    private List<Reino> ordemTurnos;
    private int turnoAtual;

    public Turno(List<Reino> ordemTurnos) {
        this.ordemTurnos = new ArrayList<>(ordemTurnos);
        this.turnoAtual = 0;
    }

    public void iniciarTurnos() {
        while (!verificarFimDoJogo()) {
            Reino reinoAtual = ordemTurnos.get(turnoAtual);
            executarTurno(reinoAtual);
            proximoTurno();
        }
    }

    private void executarTurno(Reino reino) {

    }

    private void proximoTurno() {
        turnoAtual++;
        if (turnoAtual >= ordemTurnos.size()) {
            turnoAtual = 0; // Volta para o primeiro jogador se atingir o final da lista
        }
    }

    private boolean verificarFimDoJogo() {
        // Aqui você pode implementar a lógica para verificar se o jogo chegou ao fim
        // Por exemplo, se apenas um jogador restou, ou se algum objetivo foi alcançado
        return false; // Retorna true se o jogo terminou
    }
}
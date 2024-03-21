import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Turno {
    private List<Reino> ordemTurnos = new ArrayList<>();
    private int turnoAtual;
    private int turnogeral;
    private String ganhador;

     Menu menu = new Menu();



    public Turno(List<Reino> ordemTurnos) {
        this.turnoAtual = 0;
        this.turnogeral = 1;
    }

    public void iniciarTurnos() {
        List<Reino> a = new ArrayList<>(Menu.getReinosGeral().values());
        setOrdemTurnos(a);

        if(!verificarFimDoJogo()) {
            Reino reinoAtual = ordemTurnos.get(turnoAtual);
            executarTurno(reinoAtual);
        }
        else{
            System.out.println("Acabou");
        }

    }

    private void executarTurno(Reino reino) {
        reino.gerarInfluência(reino.getServentes());
        if (reino.getLider().isJogador()) {
            proximoTurno();
            Menu.abrirMenuAcoes(reino);
            return;
        } else {
            proximoTurno();
            BotActions.executarAcao(reino);
            return;
        }
    }

    private void proximoTurno() {
        turnoAtual++;
        turnogeral++;
        if (turnoAtual >= ordemTurnos.size()) {
            turnoAtual = 0; // Volta para o primeiro jogador se atingir o final da lista
        }

    }

    private boolean verificarFimDoJogo() {
        int menorTurnos = Integer.MAX_VALUE;
        String primeiroLider = null;
        for (Reino reino : Menu.getReinosGeral().values()) {
            if (primeiroLider == null) {
                primeiroLider = reino.getLider().getNome();
            } else if (!primeiroLider.equals(reino.getLider().getNome())) {
                return false; // Se encontrarmos um líder diferente, o jogo não terminou
            }
        }
        if (turnogeral < menorTurnos) {
            menorTurnos = turnogeral;
            salvar();
        }
        return true; // Se todos os reinos pertencem ao mesmo líder, o jogo terminou
    }


    public List<Reino> getOrdemTurnos() {
        return ordemTurnos;
    }

    public void setOrdemTurnos(List<Reino> ordemTurnos) {
        this.ordemTurnos = ordemTurnos;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }

    public void setTurnoAtual(int turnoAtual) {
        this.turnoAtual = turnoAtual;
    }

    public void salvar() {
        String menorJogador = ordemTurnos.get(turnoAtual).getLider().getNome();
        int menorTurnos = Integer.MAX_VALUE;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vencedor.txt"))) {
            writer.write("Jogador: " + menorJogador + ", Turnos: " + menorTurnos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

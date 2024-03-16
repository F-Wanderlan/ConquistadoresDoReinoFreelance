public class Sys {

    int turnos;

    public void contadorDeTurnos(){
        turnos++;
    }

    public Reino combateEntreReinos(Reino reinoAtacante, Reino reinoDefensor){
        if (reinoAtacante.getSoldadosAtk() > reinoDefensor.getSoldadosDef()) {
            // Ataque ganha: subtrai soldados do reino defensor e ajusta tropas do reino atacante
            reinoDefensor.setSoldadosDef(0); // O reino defensor perde todas as tropas defensoras
            int perdaAtaque = reinoDefensor.getSoldadosDef();
            reinoAtacante.setSoldadosAtk(reinoAtacante.getSoldadosAtk() - perdaAtaque);
            System.out.println("O reino atacante venceu o combate!");
            return reinoAtacante;
        } else if (reinoDefensor.getSoldadosDef() > reinoAtacante.getSoldadosAtk()) {
            // Defesa ganha: subtrai soldados dos reinos e ajusta tropas
            double perdaAtaque = reinoAtacante.getSoldadosAtk() * 0.5; // Ataque perde metade das tropas atacantes
            double perdaDefesa = reinoAtacante.getSoldadosAtk() * 0.1; // Defesa perde 10% das tropas atacantes
            reinoAtacante.setSoldadosAtk((int)Math.floor(reinoAtacante.getSoldadosAtk() - perdaAtaque));
            reinoDefensor.setSoldadosDef(0); // O reino defensor perde todas as tropas defensoras
            System.out.println("O reino defensor venceu o combate!");
            return reinoDefensor;
        } else {
            // Empate: não há perda de tropas
            System.out.println("O combate terminou em empate!");
            return null;
        }
    }


    public void unificarReino(Reino reinoMaior, Reino reinoMenor) {
        double diferencaPercentual = (double)(reinoMaior.getInfluencia() - reinoMenor.getInfluencia()) / reinoMenor.getInfluencia() * 100;

        if (diferencaPercentual >= 70) {
            System.out.println("Unificação dos reinos em andamento...");
            System.out.println("Reino " + reinoMenor.getNome() + " será unificado ao reino " + reinoMaior.getNome());

            // Transferir os recursos e soldados do reino menor para o reino maior
            reinoMaior.setInfluencia(reinoMaior.getInfluencia() + reinoMenor.getInfluencia());
            reinoMaior.setSoldados(reinoMaior.getSoldados() + reinoMenor.getSoldados());
            reinoMaior.setServentes(reinoMaior.getServentes() + reinoMenor.getServentes());

            // Definir o líder do reino menor como o líder do reino maior
            reinoMaior.setLider(reinoMenor.getLider());

            // Após a transferência, reino menor é "destruído"
            reinoMenor.setInfluencia(0);
            reinoMenor.setSoldados(0);
            reinoMenor.setServentes(0);
            reinoMenor.setLider(null);

            System.out.println("Unificação concluída!");
        } else {
            System.out.println("Não é possível unificar os reinos. A diferença percentual de influência é inferior a 70%.");
        }
    }






}

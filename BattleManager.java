public class BattleManager {
    private Validador validador;
    private RoteiroLoader roteiro;
    private Player jogador;

    public BattleManager(Validador validador, RoteiroLoader roteiro, Player jogador) {
        this.validador = validador;
        this.roteiro = roteiro;
        this.jogador = jogador;
    }

    public void iniciarLuta1() {
        roteiro.fase1();
        // Homem-Pedra tem 5000 HP, então o critério é apenas os 4 acertos
        processarTurnos(1, Npcs.homemPedra, false); 
    }

    public void iniciarLuta2() {
        roteiro.fase2();
        processarTurnos(2, Npcs.homemMorcego, true);
    }

    public void iniciarLuta3() {
        roteiro.fase3();
        processarTurnos(3, Npcs.sereia, true);
    }

    public void iniciarLuta4() {
        roteiro.fase4();
        processarTurnos(4, Npcs.goblin, true);
    }

    private void processarTurnos(int parte, Enemy inimigo, boolean recebeDano) {
        int acertosNaFase = 0;
        int tentativas = 0;

        while (acertosNaFase < 4 && tentativas < 8 && jogador.estaVivo() && inimigo.estaVivo()) {
            boolean acertou = false;
            
            // Chama a parte correspondente do validador
            switch (parte) {
                case 1 -> acertou = validador.rodarParte1();
                case 2 -> acertou = validador.rodarParte2();
                case 3 -> acertou = validador.rodarParte3();
                case 4 -> acertou = validador.rodarParte4();
            }

            if (acertou) {
                acertosNaFase++;
                inimigo.setVida(inimigo.getVida() - jogador.getForca());
                System.out.println("Você acertou a pergunta, e atacou  " + inimigo.getNome() + " HP: " + inimigo.getVida());
                
            } else {
                System.out.println("Tente de Novo");
                if (recebeDano) {
                    jogador.setVida(jogador.getVida() - inimigo.getForca());
                    System.out.println("[ERRO] " + inimigo.getNome() + " atacou! Seu HP: " + jogador.getVida());
                }
            }
            tentativas++;
        }

        validarResultadoFase(parte, acertosNaFase, inimigo);
    }

    private void validarResultadoFase(int parte, int acertos, Enemy inimigo) {
        if (acertos >= 4 || !inimigo.estaVivo()) {
            exibirGanho(parte);
        } else {
            exibirPerda(parte);
            System.exit(0); // Encerra o jogo se morrer/perder
        }
    }

    private void exibirGanho(int parte) {
        switch (parte) {
            case 1 -> roteiro.fase1Ganho();
            case 2 -> roteiro.fase2Ganho();
            case 3 -> roteiro.fase3Ganho();
            case 4 -> roteiro.fase4Ganho();
        }
    }

    private void exibirPerda(int parte) {
        switch (parte) {
            case 1 -> roteiro.fase1Perda();
            case 2 -> roteiro.fase2Perda();
            case 3 -> roteiro.fase3Perda();
            case 4 -> roteiro.fase4Perda();
        }
    }
}
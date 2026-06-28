package game;

/**
 * Gerencia a pontuação acumulada durante a partida e exibe estatísticas ao final.
 */
public class ScoreSystem {

    private int pontuacao      = 0;
    private int acertos        = 0;
    private int erros          = 0;
    private int danoTotal      = 0;
    private int curaTotal      = 0;

    // ------------------------------------------------------------------
    //  Registro de eventos
    // ------------------------------------------------------------------

    public void registrarAcerto(int score) {
        pontuacao += score;
        acertos++;
    }

    public void registrarErro() {
        erros++;
    }

    public void registrarDano(int dano) {
        danoTotal += dano;
    }

    public void registrarCura(int cura) {
        curaTotal += cura;
    }

    // ------------------------------------------------------------------
    //  Getters
    // ------------------------------------------------------------------

    public int getPontuacao() { return pontuacao; }
    public int getAcertos()   { return acertos; }
    public int getErros()     { return erros; }

    // ------------------------------------------------------------------
    //  Tela final
    // ------------------------------------------------------------------

    public void exibirEstatisticas(boolean vitoria, int vidaRestante) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println(vitoria
                ? "║           🏆  VITÓRIA!                   ║"
                : "║           💀  DERROTA...                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Pontuação final:       %6d pontos    ║%n", pontuacao);
        System.out.printf( "║  Questões acertadas:    %6d           ║%n", acertos);
        System.out.printf( "║  Questões erradas:      %6d           ║%n", erros);
        System.out.printf( "║  Dano total causado:    %6d           ║%n", danoTotal);
        if (curaTotal > 0)
            System.out.printf("║  Vida recuperada:       %6d           ║%n", curaTotal);
        System.out.printf( "║  Vida restante:         %6d           ║%n", vidaRestante);
        System.out.println("╚══════════════════════════════════════════╝");
    }
}

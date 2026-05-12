/**
 * Acumula as estatísticas do jogo inteiro e exibe ao final.
 */
public class ScoreSystem {

    private int totalAcertos = 0;
    private int totalErros   = 0;
    private int scoreTotal   = 0;
    private int fasesCompletas = 0;

    public void registrarRodada(Round.Resultado resultado) {
        totalAcertos   += resultado.acertos;
        totalErros     += resultado.erros;
        scoreTotal     += resultado.scoreObtido;
        if (resultado.jogadorSobreviveu) fasesCompletas++;
    }

    public void exibirEstatisticas(Player jogador) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         ESTATÍSTICAS FINAIS          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf( "║  Jogador:      %-22s║%n", jogador.getNome());
        System.out.printf( "║  Fases:        %-22s║%n", fasesCompletas + "/4");
        System.out.printf( "║  Acertos:      %-22s║%n", totalAcertos);
        System.out.printf( "║  Erros:        %-22s║%n", totalErros);
        System.out.printf( "║  Score total:  %-22s║%n", scoreTotal);
        System.out.printf( "║  Vida restante:%-22s║%n", jogador.barraDeVida());
        System.out.printf( "║  Moedas:       %-22s║%n", jogador.getMoedas());
        System.out.println("╚══════════════════════════════════════╝");
    }

    // Getters para uso externo
    public int getScoreTotal()    { return scoreTotal; }
    public int getTotalAcertos()  { return totalAcertos; }
    public int getTotalErros()    { return totalErros; }
}

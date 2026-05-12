import java.util.Scanner;

/**
 * Ponto de entrada do CodeArena.
 * Controla: apresentação → escolha de personagem → 4 fases → fim de jogo.
 *
 * Para rodar:
 *   javac *.java
 *   java Main
 *
 * Certifique-se de que os arquivos estão no mesmo diretório:
 *   - portuguesequestion.txt
 *   - Roteiro.txt
 */
public class Main {

    // ── Caminhos dos arquivos de conteúdo ──────────────────────────────────
    // Altere aqui se mover os arquivos de lugar
    private static final String CAMINHO_PERGUNTAS = "portuguesequestion.txt";
    private static final String CAMINHO_ROTEIRO   = "Roteiro.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ── Configuração inicial ────────────────────────────────────────────
        RoteiroReader roteiro = new RoteiroReader(CAMINHO_ROTEIRO, RoteiroReader.Velocidade.NORMAL);
        LoaderQuestion loader  = new LoaderQuestion(CAMINHO_PERGUNTAS);
        ScoreSystem score      = new ScoreSystem();

        // ── Introdução ──────────────────────────────────────────────────────
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           CODEARENA                  ║");
        System.out.println("║    A Maldição do Questionário        ║");
        System.out.println("╚══════════════════════════════════════╝");
        roteiro.exibir("INTRO");

        pausar(sc);

        // ── Escolha de personagem ───────────────────────────────────────────
        roteiro.exibir("ESCOLHA");
        System.out.print("Digite 1 ou 2: ");
        String escolha = sc.nextLine().trim();

        Player jogador;
        if (escolha.equals("2")) {
            jogador = new Player.Hanny();
            System.out.println("\nVocê escolheu Hanny!");
        } else {
            jogador = new Player.Pete();
            System.out.println("\nVocê escolheu Pete!");
        }

        jogador.mostrarStatus();
        roteiro.exibir("POS_ESCOLHA");
        pausar(sc);

        // ── BattleManager ───────────────────────────────────────────────────
        BattleManager battle = new BattleManager(jogador, loader, score);

        // ── FASE 1 ──────────────────────────────────────────────────────────
        roteiro.exibir("FASE1_ENTRADA");
        pausar(sc);
        boolean vivo = battle.Fase1();
        if (!vivo) { fimDeJogo(roteiro, score, jogador); return; }

        // ── FASE 2 ──────────────────────────────────────────────────────────
        roteiro.exibir("FASE2_ENTRADA");
        pausar(sc);
        vivo = battle.Fase2();
        if (!vivo) { fimDeJogo(roteiro, score, jogador); return; }

        // ── FASE 3 ──────────────────────────────────────────────────────────
        roteiro.exibir("FASE3_ENTRADA");
        pausar(sc);
        vivo = battle.Fase3();
        if (!vivo) { fimDeJogo(roteiro, score, jogador); return; }

        // ── FASE 4 ──────────────────────────────────────────────────────────
        roteiro.exibir("FASE4_ENTRADA");
        pausar(sc);
        vivo = battle.Fase4();

        // ── Fim ─────────────────────────────────────────────────────────────
        if (vivo) {
            roteiro.exibir("VITORIA");
        } else {
            fimDeJogo(roteiro, score, jogador);
            return;
        }

        score.exibirEstatisticas(jogador);
    }

    /** Exibe tela de game over e estatísticas */
    private static void fimDeJogo(RoteiroReader roteiro, ScoreSystem score, Player jogador) {
        roteiro.exibir("GAME_OVER");
        score.exibirEstatisticas(jogador);
    }

    /** Aguarda o jogador pressionar Enter para continuar */
    private static void pausar(Scanner sc) {
        System.out.print("\n[ Pressione ENTER para continuar ] ");
        sc.nextLine();
    }
}

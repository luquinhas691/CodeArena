package game;

import character.Pete;
import character.Hanny;
import character.Player;
import battle.BattleManager;
import question.QuestionLoader;
import java.util.Scanner;

/** Controla o fluxo geral da partida: seleção de personagem, sequência de fases e tela final. */
public class Game {
    private RoteiroLoader roteiro;
    private Scanner       scanner;
    private ScoreSystem   score;
    String arquivoA;
    String arquivoB;

    public Game() {
        scanner = new Scanner(System.in);
        roteiro = new RoteiroLoader("roteiro.txt");
        score   = new ScoreSystem();
    }

    public void iniciar() {
        roteiro.introducao();
        roteiro.pete();
        roteiro.hanny();

        Player jogador = escolherPersonagem();

        if (jogador instanceof Pete) {
            roteiro.introducaoPete();
            arquivoA = "MT_A.txt";
            arquivoB = "MT_B.txt";
        } else {
            roteiro.introducaoHanny();
            arquivoA = "PT_A.txt";
            arquivoB = "PT_B.txt";
        }

        QuestionLoader ql = new QuestionLoader(arquivoA, arquivoB);
        BattleManager  bm = new BattleManager(ql, roteiro, jogador, scanner, score);

        if (!bm.fase1()) { encerrar(jogador, false); return; }
        if (!bm.fase2()) { encerrar(jogador, false); return; }
        if (!bm.fase3()) { encerrar(jogador, false); return; }
        if (!bm.fase4()) { encerrar(jogador, false); return; }

        roteiro.finalGame();
        encerrar(jogador, true);
    }

    private Player escolherPersonagem() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        ESCOLHA SEU PERSONAGEM            ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1 - PETE   │ Força 35 │ Vida 100        ║");
        System.out.println("║    💥 Habilidade: Golpe Duplo            ║");
        System.out.println("║       Dobra o dano do próximo ataque     ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  2 - HANNY  │ Força 25 │ Vida 120        ║");
        System.out.println("║    💚 Habilidade: Pulso Vital            ║");
        System.out.println("║       Recupera 30 de vida                ║");
        System.out.println("╚══════════════════════════════════════════╝");

        int opcao = -1;
        while (opcao != 1 && opcao != 2) {
            System.out.print("\n> ");
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao != 1 && opcao != 2)
                    System.out.println("Opção inválida. Digite 1 ou 2.");
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite 1 ou 2.");
            }
        }

        Player p = opcao == 1 ? new Pete() : new Hanny();
        System.out.println("\n>>> Você escolheu " + p.getNome() + "!");
        System.out.println("    Habilidade: " + p.getNomeHabilidade()
                + " — " + p.getDescricaoHabilidade());
        System.out.println();
        return p;
    }

    private void encerrar(Player jogador, boolean vitoria) {
        if (!vitoria) System.out.println("\nGAME OVER. A maldição se espalhou...");
        else          System.out.println("\nParabéns! Você salvou seu amigo e o mundo!");
        score.exibirEstatisticas(vitoria, jogador.getVida());
        System.exit(0);
    }
}

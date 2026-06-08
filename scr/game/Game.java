package game;

import character.Player;
import battle.BattleManager;
import question.QuestionLoader;
import java.util.Scanner;

public class Game {
    private RoteiroLoader roteiro;
    private Scanner scanner;
    String arquivoA;
    String arquivoB;

    public Game() {
        scanner = new Scanner(System.in);
        roteiro = new RoteiroLoader("roteiro.txt");
    }

    public void iniciar() {
        roteiro.introducao();
        roteiro.pete();
        roteiro.hanny();

        Player jogador = escolherPersonagem();

        if (jogador.getNome().equals("Pete")) {
            roteiro.introducaoPete();
            arquivoA = "MT_A.txt";
            arquivoB = "MT_B.txt";
        } else {
            roteiro.introducaoHanny();
            arquivoA = "PT_A.txt";
            arquivoB = "PT_B.txt";
        }

        QuestionLoader ql = new QuestionLoader(arquivoA, arquivoB);
        BattleManager bm  = new BattleManager(ql, roteiro, jogador, scanner);

        if (!bm.fase1()) { gameOver(); return; }
        if (!bm.fase2()) { gameOver(); return; }
        if (!bm.fase3()) { gameOver(); return; }
        if (!bm.fase4()) { gameOver(); return; }

        roteiro.finalGame();
        System.out.println("\nParabéns! Você salvou seu amigo e o mundo!");
    }

    private Player escolherPersonagem() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        ESCOLHA SEU PERSONAGEM            ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1 - PETE   │ Força 35 │ Vida 100        ║");
        System.out.println("║    ⏳ Habilidade: Controle do Tempo      ║");
        System.out.println("║       +15s em cada questão cronometrada  ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  2 - HANNY  │ Força 25 │ Vida 120        ║");
        System.out.println("║    🧠 Habilidade: Dedução Lógica         ║");
        System.out.println("║       Acerta 1 questão automaticamente   ║");
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

        Player p = opcao == 1
                ? new Player("Pete",  100, 35)
                : new Player("Hanny", 120, 25);

        System.out.println("\n>>> Você escolheu " + p.getNome() + "!");
        System.out.println("    Habilidade: " + p.getHabilidade().getNome()
                + " — " + p.getHabilidade().getDescricao());
        System.out.println();
        return p;
    }

    private void gameOver() {
        System.out.println("\nGAME OVER. A maldição se espalhou...");
        System.exit(0);
    }
}

        Player jogador = escolherPersonagem();

        if (jogador.getNome().equals("Pete")) {
            roteiro.introducaoPete();
            arquivoA = "MT_A.txt";
            arquivoB = "MT_B.txt";
        } else {
            roteiro.introducaoHanny();
            arquivoA = "PT_A.txt";
            arquivoB = "PT_B.txt";
        }

        QuestionLoader ql = new QuestionLoader(arquivoA, arquivoB);
        BattleManager bm  = new BattleManager(ql, roteiro, jogador, scanner);

        if (!bm.fase1()) { gameOver(); return; }
        if (!bm.fase2()) { gameOver(); return; }
        if (!bm.fase3()) { gameOver(); return; }
        if (!bm.fase4()) { gameOver(); return; }

        roteiro.finalGame();
        System.out.println("\nParabéns! Você salvou seu amigo e o mundo!");
    }

    private Player escolherPersonagem() {
        int opcao = -1;
        while (opcao != 1 && opcao != 2) {
            System.out.print("\nEscolha seu personagem:\n1 - Pete (Forca 35, Vida 100)\n2 - Hanny (Forca 25, Vida 120)\n> ");
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Digite 1 ou 2.");
            }
        }
        return opcao == 1 ? new Player("Pete", 100, 35) : new Player("Hanny", 120, 25);
    }

    private void gameOver() {
        System.out.println("\nGAME OVER. A maldição se espalhou...");
        System.exit(0);
    }
}

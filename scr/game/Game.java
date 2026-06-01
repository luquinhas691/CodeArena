package game;

import character.Player;
import battle.BattleManager;
import question.QuestionLoader;
import java.util.Scanner;

public class Game {
    private RoteiroLoader roteiro;
    private Scanner scanner;

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
        } else {
            roteiro.introducaoHanny();
        }

        QuestionLoader ql = new QuestionLoader("PT_A.txt", "PT_B.txt");
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

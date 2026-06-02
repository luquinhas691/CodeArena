package battle;

import character.Player;
import character.Enemy;
import character.Npcs;
import game.RoteiroLoader;
import question.QuestionLoader;
import question.Question;
import java.util.Scanner;

public class BattleManager {
    private QuestionLoader questionLoader;
    private RoteiroLoader roteiro;
    private Player jogador;
    private Scanner scanner;

    public BattleManager(QuestionLoader ql, RoteiroLoader rl, Player jogador, Scanner scanner) {
        this.questionLoader = ql;
        this.roteiro = rl;
        this.jogador = jogador;
        this.scanner = scanner;
    }

    // Fase 1: Homem-Pedra – 8 acertos consecutivos, inimigo não ataca
    public boolean fase1() {
        roteiro.fase1();
        int acertos = 0;
        while (acertos < 8) {
            Question q = questionLoader.proximaPerguntaA();
            if (q == null) {
                System.out.println("ERRO: Sem perguntas suficientes para a fase 1.");
                return false;
            }
            q.mostrarEnunciado();
            q.mostrarAlternativas();
            System.out.print("> ");
            String resposta = scanner.nextLine();
            if (questionLoader.validarResposta(q, resposta)) {
                acertos++;
                System.out.println("Acertou! (" + acertos + "/8)");
            } else {
                System.out.println("Errou! Tente novamente.");
            }
        }
        roteiro.fase1Ganho();
        return true;
    }

    // Fase 2: Homem-Morcego – perguntas do pool A
    public boolean fase2() {
        roteiro.fase2();
        Enemy inimigo = Npcs.homemMorcego();
        return batalhaComum(inimigo, false);
    }

    // Fase 3: Sereia – perguntas do pool B
    public boolean fase3() {
        roteiro.fase3();
        Enemy inimigo = Npcs.sereia();
        return batalhaComum(inimigo, true);
    }

    // Fase 4: Goblin – perguntas do pool B
    public boolean fase4() {
        roteiro.fase4();
        Enemy inimigo = Npcs.goblin();
        return batalhaComum(inimigo, true);
    }

    // Lógica comum para fases 2, 3, 4
    private boolean batalhaComum(Enemy inimigo, boolean usarPerguntasB) {
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            Question q = usarPerguntasB
                    ? questionLoader.proximaPerguntaB()
                    : questionLoader.proximaPerguntaA();
            if (q == null) {
                System.out.println("ERRO: Sem perguntas disponíveis.");
                return false;
            }
            q.mostrarEnunciado();
            q.mostrarAlternativas();
            System.out.print("> ");
            String resposta = scanner.nextLine();

            if (questionLoader.validarResposta(q, resposta)) {
                int dano = jogador.getForca();
                inimigo.receberDano(dano);
                System.out.println("Você acertou! Causou " + dano + " de dano em " + inimigo.getNome()
                        + " (HP: " + inimigo.getVida() + ")");
            } else {
                System.out.println("Resposta errada!");
                inimigo.atacar(jogador);
                if (!jogador.estaVivo()) {
                    System.out.println("Você foi derrotado...");
                }
            }
        }

        if (jogador.estaVivo() && !inimigo.estaVivo()) {
            if (inimigo.getNome().equals("Homem-Morcego")) roteiro.fase2Ganho();
            else if (inimigo.getNome().equals("Sereia"))        roteiro.fase3Ganho();
            else if (inimigo.getNome().equals("Goblin"))        roteiro.fase4Ganho();
            return true;
        } else {
            if (inimigo.getNome().equals("Homem-Morcego")) roteiro.fase2Perda();
            else if (inimigo.getNome().equals("Sereia"))        roteiro.fase3Perda();
            else if (inimigo.getNome().equals("Goblin"))        roteiro.fase4Perda();
            return false;
        }
    }
}

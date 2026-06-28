package battle;

import character.Player;
import character.Enemy;
import character.Npcs;
import game.RoteiroLoader;
import game.ScoreSystem;
import question.Question;
import question.QuestionLoader;
import java.util.Scanner;

public class BattleManager {

    private final QuestionLoader questionLoader;
    private final RoteiroLoader  roteiro;
    private final Player         jogador;
    private final Scanner        scanner;
    private final ScoreSystem    score;

    public BattleManager(QuestionLoader ql, RoteiroLoader rl,
                         Player jogador, Scanner scanner, ScoreSystem score) {
        this.questionLoader = ql;
        this.roteiro        = rl;
        this.jogador        = jogador;
        this.scanner        = scanner;
        this.score          = score;
    }

    public boolean fase1() {
        roteiro.fase1();
        int acertos = 0;
        while (acertos < 4) {
            Question q = questionLoader.proximaPerguntaA();
            if (q == null) { System.out.println("ERRO: Sem perguntas suficientes."); return false; }

            mostrarStatus(null);
            oferecerHabilidade();

            q.mostrarEnunciado();
            q.mostrarAlternativas();
            System.out.print("> ");
            String resposta = scanner.nextLine();

            if (questionLoader.validarResposta(q, resposta)) {
                acertos++;
                score.registrarAcerto(q.getScore());
                System.out.println("Acertou! (" + acertos + "/4)  [+" + q.getScore() + " pts]");
            } else {
                score.registrarErro();
                System.out.println("Errou! Tente novamente.");
            }
        }
        roteiro.fase1Ganho();
        jogador.resetarHabilidade();
        return true;
    }

    public boolean fase2() {
        roteiro.fase2();
        boolean resultado = batalhaComum(Npcs.homemMorcego(), false);
        jogador.resetarHabilidade();
        return resultado;
    }

    public boolean fase3() {
        roteiro.fase3();
        boolean resultado = batalhaComum(Npcs.sereia(), true);
        jogador.resetarHabilidade();
        return resultado;
    }

    public boolean fase4() {
        roteiro.fase4();
        return batalhaComum(Npcs.goblin(), true);
    }

    private boolean batalhaComum(Enemy inimigo, boolean usarPerguntasB) {
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            Question q = usarPerguntasB
                    ? questionLoader.proximaPerguntaB()
                    : questionLoader.proximaPerguntaA();
            if (q == null) { System.out.println("ERRO: Sem perguntas disponíveis."); return false; }

            mostrarStatus(inimigo);
            oferecerHabilidade();

            q.mostrarEnunciado();
            q.mostrarAlternativas();
            System.out.print("> ");
            String resposta = scanner.nextLine();

            if (questionLoader.validarResposta(q, resposta)) {
                int vidaAntes = inimigo.getVida();
                jogador.atacar(inimigo, q.getScore());
                int danoCausado = vidaAntes - inimigo.getVida();
                score.registrarAcerto(q.getScore());
                score.registrarDano(danoCausado);
                System.out.println("Voce acertou! [+" + q.getScore() + " pts] (HP inimigo: " + inimigo.getVida() + ")");
            } else {
                score.registrarErro();
                errarResposta(inimigo);
            }
        }
        return resolverFim(inimigo);
    }

    private void oferecerHabilidade() {
        if (!jogador.habilidadeDisponivel()) return;

        System.out.println("\n Habilidade disponivel: " + jogador.getNomeHabilidade());
        System.out.println("   " + jogador.getDescricaoHabilidade());
        System.out.print("   Deseja usar agora? (s/n) > ");

        String escolha = scanner.nextLine().trim().toLowerCase();
        if (escolha.equals("s")) {
            int vidaAntes = jogador.getVida();
            jogador.ativarHabilidade();
            int cura = jogador.getVida() - vidaAntes;
            if (cura > 0) score.registrarCura(cura);
        } else {
            System.out.println("   [Habilidade guardada para outro turno]");
        }
    }

    private void errarResposta(Enemy inimigo) {
        System.out.println("Resposta errada!");
        inimigo.atacar(jogador);
        if (!jogador.estaVivo()) System.out.println("Voce foi derrotado...");
    }

    private void mostrarStatus(Enemy inimigo) {
        System.out.println("\n── Status ──────────────────────────────");
        System.out.println("  " + jogador.getNome() + " — HP: " + jogador.getVida()
                + "  |  Pontos: " + score.getPontuacao());
        if (inimigo != null)
            System.out.println("  " + inimigo.getNome() + " — HP: " + inimigo.getVida());
        System.out.println("────────────────────────────────────────");
    }

    private boolean resolverFim(Enemy inimigo) {
        if (jogador.estaVivo() && !inimigo.estaVivo()) {
            exibirGanho(inimigo.getNome());
            return true;
        }
        exibirPerda(inimigo.getNome());
        return false;
    }

    private void exibirGanho(String nome) {
        switch (nome) {
            case "Homem-Morcego": roteiro.fase2Ganho(); break;
            case "Sereia":        roteiro.fase3Ganho(); break;
            case "Goblin":        roteiro.fase4Ganho(); break;
        }
    }

    private void exibirPerda(String nome) {
        switch (nome) {
            case "Homem-Morcego": roteiro.fase2Perda(); break;
            case "Sereia":        roteiro.fase3Perda(); break;
            case "Goblin":        roteiro.fase4Perda(); break;
        }
    }
}

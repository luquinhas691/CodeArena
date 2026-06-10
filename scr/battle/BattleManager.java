package battle;

import character.Habilidade;
import character.Player;
import character.Enemy;
import character.Npcs;
import game.RoteiroLoader;
import question.Question;
import question.QuestionLoader;
import question.TimedQuestion;
import question.TimedQuestionImpl;
import java.util.Scanner;


public class BattleManager {

    /** Tempo base (segundos) para questões cronometradas. */
    private static final int TEMPO_BASE_FASE4 = 20;

    private final QuestionLoader questionLoader;
    private final RoteiroLoader  roteiro;
    private final Player         jogador;
    private final Scanner        scanner;

    public BattleManager(QuestionLoader ql, RoteiroLoader rl,
                         Player jogador, Scanner scanner) {
        this.questionLoader = ql;
        this.roteiro        = rl;
        this.jogador        = jogador;
        this.scanner        = scanner;
    }

    // ------------------------------------------------------------------
    //  Fase 1 – Homem-Pedra (8 acertos consecutivos, inimigo não ataca)
    // ------------------------------------------------------------------

    public boolean fase1() {
        roteiro.fase1();
        int acertos = 0;
        while (acertos < 8) {
            Question q = questionLoader.proximaPerguntaA();
            if (q == null) {
                System.out.println("ERRO: Sem perguntas suficientes para a fase 1.");
                return false;
            }

            // Hanny pode usar auto-acerto na fase 1 também
            if (tentarAutoAcerto()) {
                acertos++;
                System.out.println("Acerto automático! (" + acertos + "/8)");
                continue;
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

    // ------------------------------------------------------------------
    //  Fase 2 – Homem-Morcego
    // ------------------------------------------------------------------

    public boolean fase2() {
        roteiro.fase2();
        return batalhaComum(Npcs.homemMorcego(), false);
    }

    // ------------------------------------------------------------------
    //  Fase 3 – Sereia
    // ------------------------------------------------------------------

    public boolean fase3() {
        roteiro.fase3();
        return batalhaComum(Npcs.sereia(), true);
    }

    // ------------------------------------------------------------------
    //  Fase 4 – Goblin (questões cronometradas via TimedQuestion)
    // ------------------------------------------------------------------

    public boolean fase4() {
        roteiro.fase4();
        return batalhaComTempo(Npcs.goblin());
    }

    // ------------------------------------------------------------------
    //  Batalha comum (fases 2 e 3)
    // ------------------------------------------------------------------

    private boolean batalhaComum(Enemy inimigo, boolean usarPerguntasB) {
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            Question q = usarPerguntasB
                    ? questionLoader.proximaPerguntaB()
                    : questionLoader.proximaPerguntaA();
            if (q == null) {
                System.out.println("ERRO: Sem perguntas disponíveis.");
                return false;
            }

            // Hanny: habilidade de auto-acerto consultada via interface Habilidade
            if (tentarAutoAcerto()) {
                aplicarDano(inimigo);
                continue;
            }

            q.mostrarEnunciado();
            q.mostrarAlternativas();
            System.out.print("> ");
            String resposta = scanner.nextLine();

            if (questionLoader.validarResposta(q, resposta)) {
                aplicarDano(inimigo);
            } else {
                errarResposta(inimigo);
            }
        }
        return resolverFim(inimigo);
    }

    // ------------------------------------------------------------------
    //  Batalha cronometrada (fase 4) — usa TimedQuestion + Habilidade
    // ------------------------------------------------------------------


    private boolean batalhaComTempo(Enemy inimigo) {
        int tempoEfetivo = resolverTempoEfetivo();

        System.out.println("\n⚠  ATENÇÃO: Esta fase é CRONOMETRADA! "
                + "Você tem " + tempoEfetivo + " segundos por pergunta.\n");

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            Question q = questionLoader.proximaPerguntaB();
            if (q == null) {
                System.out.println("ERRO: Sem perguntas disponíveis.");
                return false;
            }

            // Hanny: auto-acerto antes de exibir a questão
            if (tentarAutoAcerto()) {
                aplicarDano(inimigo);
                continue;
            }

            // Questão cronometrada — referenciada apenas pela interface
            TimedQuestion tq = new TimedQuestionImpl(q, tempoEfetivo);
            tq.mostrarEnunciadoComTempo();
            tq.mostrarAlternativas();

            String resposta = tq.lerRespostaComTempo();

            if (resposta == null) {
                System.out.println("Sem resposta a tempo! O inimigo aproveita a abertura...");
                inimigo.atacar(jogador);
                if (!jogador.estaVivo()) System.out.println("Você foi derrotado...");
            } else if (tq.validarResposta(resposta)) {
                aplicarDano(inimigo);
            } else {
                errarResposta(inimigo);
            }
        }
        return resolverFim(inimigo);
    }

    // ------------------------------------------------------------------
    //  Lógica de habilidades — acesso exclusivo via interface Habilidade
    // ------------------------------------------------------------------

    /**
     * Verifica se a habilidade pode acertar automaticamente
     
     */
    private boolean tentarAutoAcerto() {
        Habilidade h = jogador.getHabilidade();
        if (h instanceof character.HabilidadeAutoAcerto && h.estaDisponivel()) {
            h.ativar();
            return true;
        }
        return false;
    }

  
  // Calcula o tempo disponível para responder
   
    private int resolverTempoEfetivo() {
        Habilidade h = jogador.getHabilidade();
        if (h instanceof character.HabilidadeTempoExtra && h.estaDisponivel()) {
            h.ativar();
            return TEMPO_BASE_FASE4 + character.HabilidadeTempoExtra.BONUS_SEGUNDOS;
        }
        return TEMPO_BASE_FASE4;
    }

    // ------------------------------------------------------------------
    //  Auxiliares compartilhados
    // ------------------------------------------------------------------

    private void aplicarDano(Enemy inimigo) {
        int dano = jogador.getForca();
        inimigo.receberDano(dano);
        System.out.println("Você acertou! Causou " + dano + " de dano em "
                + inimigo.getNome() + " (HP: " + inimigo.getVida() + ")");
    }

    private void errarResposta(Enemy inimigo) {
        System.out.println("Resposta errada!");
        inimigo.atacar(jogador);
        if (!jogador.estaVivo()) System.out.println("Você foi derrotado...");
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

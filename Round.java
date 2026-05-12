import java.util.List;
import java.util.Scanner;

/**
 * Controla uma rodada de perguntas.
 * - Exibe as perguntas uma a uma
 * - Aplica dano ao jogador se errar (exceto fase 1)
 * - Aplica dano ao inimigo se acertar (a partir da fase 2)
 * - Retorna se o jogador passou da rodada
 */
public class Round {

    private List<Question> perguntas;
    private Player jogador;
    private Enemy inimigo;
    private boolean causDanoAoErrar;
    private int danoAoErrar;
    private Scanner scanner;

    /**
     * @param perguntas       lista de perguntas desta rodada
     * @param jogador         personagem do jogador
     * @param inimigo         inimigo da fase (pode ser null na fase 1)
     * @param causDanoAoErrar se true, jogador toma dano ao errar
     * @param danoAoErrar     quanto de dano o inimigo causa ao errar
     */
    public Round(List<Question> perguntas, Player jogador, Enemy inimigo,
                 boolean causDanoAoErrar, int danoAoErrar) {
        this.perguntas = perguntas;
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.causDanoAoErrar = causDanoAoErrar;
        this.danoAoErrar = danoAoErrar;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Executa a rodada e retorna o resultado.
     * @return resultado com acertos, erros e se o jogador sobreviveu
     */
    public Resultado executar() {
        int acertos = 0;
        int erros = 0;
        int scoreTotal = 0;

        for (Question q : perguntas) {
            if (!jogador.estaVivo()) break;

            // Se for Hanny e a questão for difícil, oferece eliminar alternativa
            if (jogador instanceof Player.Hanny && q.getDificuldade() == Question.Dificuldade.DIFICIL) {
                System.out.print("\n[Hanny] Deseja usar sua habilidade e eliminar uma alternativa? (s/n): ");
                String resp = scanner.nextLine().trim();
                if (resp.equalsIgnoreCase("s")) {
                    String eliminada = q.alternativaErradaAleatoria();
                    if (eliminada != null) {
                        System.out.println("A alternativa " + eliminada + " foi eliminada!");
                    }
                }
            }

            q.exibir();
            String escolha = scanner.nextLine().trim();

            if (q.verificarResposta(escolha)) {
                System.out.println("✅ CORRETO! +" + q.getPontos() + " ponto(s).");
                acertos++;
                scoreTotal += q.getPontos();

                // Acerto causa dano ao inimigo (fases 2+)
                if (inimigo != null && inimigo.estaVivo()) {
                    int dano = jogador.getAtaque();
                    inimigo.receberDano(dano);
                    System.out.println("Você atacou o " + inimigo.getNome() + "! (" + inimigo.barraDeVida() + ")");
                }
            } else {
                System.out.println("❌ INCORRETO. Resposta correta: " + q.getRespostaCorreta());
                erros++;

                if (causDanoAoErrar) {
                    jogador.receberDanoComEscudo(danoAoErrar);
                    System.out.println(jogador.getNome() + ": " + jogador.barraDeVida());
                }
            }
        }

        boolean todasCertas = (erros == 0);
        boolean sobreviveu  = jogador.estaVivo();

        System.out.println("\n--- Rodada encerrada ---");
        System.out.println("Acertos: " + acertos + " | Erros: " + erros + " | Score: " + scoreTotal);

        return new Resultado(acertos, erros, scoreTotal, todasCertas, sobreviveu);
    }

    // ─── Resultado imutável da rodada ───────────────────────────────────────
    public static class Resultado {
        public final int acertos;
        public final int erros;
        public final int scoreObtido;
        public final boolean todasCertas;
        public final boolean jogadorSobreviveu;

        public Resultado(int acertos, int erros, int score, boolean todasCertas, boolean sobreviveu) {
            this.acertos = acertos;
            this.erros = erros;
            this.scoreObtido = score;
            this.todasCertas = todasCertas;
            this.jogadorSobreviveu = sobreviveu;
        }
    }
}

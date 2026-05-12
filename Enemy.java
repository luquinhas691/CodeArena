/**
 * Classe base para todos os inimigos do jogo.
 * Cada inimigo estende Character e define sua própria descrição e comportamento.
 */
public abstract class Enemy extends Character {

    public Enemy(String nome, int vida, int ataque) {
        super(nome, vida, ataque);
    }

    /** Inimigo ataca o jogador e retorna o dano causado */
    public int atacar() {
        return this.getAtaque();
    }

    // =========================================================
    // FASE 1 — Homem de Pedra (inofensivo, não causa dano inicial)
    // =========================================================
    public static class HomemDePedra extends Enemy {
        public HomemDePedra() {
            super("Homem de Pedra", 0, 0); // inofensivo na fase 1
        }

        @Override
        public String getDescricao() {
            return "Uma figura de pedra imponente que bloqueia o caminho. " +
                   "Ele não ataca, mas só libera a passagem após as perguntas serem respondidas.";
        }

        public void darBoasVindas() {
            System.out.println(
                "Homem de Pedra: Bem-vindo à maldição do questionário, uma maldição que ocorre a cada mil anos.\n" +
                "Alguém foi raptado. Você precisa responder perguntas para resgata-lo.\n" +
                "Mas atenção: se sua barra de vida esgotar, você morre — e seu amigo nunca será salvo!\n" +
                "Você entendeu as regras? Está pronto para iniciar o jogo?\n"
            );
        }
    }

    // =========================================================
    // FASE 2 — Homem Morcego (médio, ataca ao errar perguntas)
    // =========================================================
    public static class HomemMorcego extends Enemy {
        public HomemMorcego() {
            super("Homem Morcego", 60, 15);
        }

        @Override
        public String getDescricao() {
            return "Uma criatura alada que patrulha o teto da sala. " +
                   "Não ataca de início, mas a cada pergunta errada ele arranha o jogador.";
        }
    }

    // =========================================================
    // FASE 3 — Sereia (difícil, ataca 3x antes das perguntas)
    // =========================================================
    public static class Sereia extends Enemy {
        private int ataquesIniciais = 3;

        public Sereia() {
            super("Sereia", 80, 18);
        }

        @Override
        public String getDescricao() {
            return "Uma sereia perigosa que habita a sala alagada. " +
                   "Ela ataca 3 vezes antes das perguntas começarem — use o escudo para se defender.";
        }

        /** Realiza ataques iniciais antes da rodada de perguntas */
        public void atacarInicialmente(Player jogador) {
            System.out.println("\nA Sereia emerge das águas e ataca " + ataquesIniciais + " vezes!\n");
            for (int i = 0; i < ataquesIniciais; i++) {
                int dano = this.getAtaque();
                // O escudo pode bloquear — tratado no BattleManager
                System.out.println("Sereia ataca! Dano: " + dano);
                jogador.receberDanoComEscudo(dano);
                if (!jogador.estaVivo()) break;
            }
        }

        public int getAtaquesIniciais() { return ataquesIniciais; }
    }

    // =========================================================
    // FASE 4 — Goblin Rei (chefe final, dano alto, perguntas difíceis)
    // =========================================================
    public static class GoblinRei extends Enemy {
        public GoblinRei() {
            super("Goblin Rei", 120, 25);
        }

        @Override
        public String getDescricao() {
            return "O chefe final. Segura o amigo do jogador em seu trono de lava. " +
                   "Seus koopas atacam dos lados e o chão cospe fogo. Só perguntas difíceis aqui.";
        }
    }
}

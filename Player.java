/**
 * Classe base para jogadores. Estende Character e adiciona
 * inventário simples (escudo, adaga) e sistema de moedas.
 */
public abstract class Player extends Character {

    private int moedas = 0;
    private boolean temEscudo = false;
    private boolean temAdaga = false;
    private int escudoCargas = 0; // o escudo aguenta 1 hit

    public Player(String nome, int vida, int ataque) {
        super(nome, vida, ataque);
    }

    /** Habilidade especial de cada personagem — implementada nas subclasses */
    public abstract void usarHabilidade();

    /** Retorna o multiplicador de tempo para perguntas cronometradas */
    public abstract double multiplicadorTempo();

    /** Recebe dano, mas bloqueia com escudo se tiver */
    public void receberDanoComEscudo(int dano) {
        if (temEscudo && escudoCargas > 0) {
            System.out.println("🛡 O escudo bloqueou o ataque!");
            escudoCargas--;
            if (escudoCargas <= 0) {
                temEscudo = false;
                System.out.println("O escudo foi destruído.");
            }
        } else {
            this.receberDano(dano);
            System.out.println(getNome() + " recebeu " + dano + " de dano. " + barraDeVida());
        }
    }

    public void ganharMoedas(int qtd) {
        moedas += qtd;
        System.out.println("💰 +" + qtd + " moedas! Total: " + moedas);
    }

    public boolean gastarMoedas(int qtd) {
        if (moedas >= qtd) {
            moedas -= qtd;
            return true;
        }
        System.out.println("Moedas insuficientes.");
        return false;
    }

    public void equiparEscudo() {
        temEscudo = true;
        escudoCargas = 1;
        System.out.println("🛡 Escudo equipado! Ele aguenta 1 hit.");
    }

    public void equiparAdaga() {
        temAdaga = true;
        System.out.println("🗡 Adaga equipada! Seu ataque aumentou.");
        setAtaque(getAtaque() + 10);
    }

    public int getMoedas()     { return moedas; }
    public boolean temEscudo() { return temEscudo; }
    public boolean temAdaga()  { return temAdaga; }

    // =========================================================
    // PETE — Estudante de Matemática
    // 100 HP, 32 atk | Habilidade: dobra o tempo das perguntas
    // =========================================================
    public static class Pete extends Player {
        public Pete() {
            super("Pete", 100, 32);
        }

        @Override
        public String getDescricao() {
            return "Pete é um estudante de matemática. Forte e preciso, tem mais tempo " +
                   "para responder perguntas cronometradas — 15s viram 30s para ele.";
        }

        @Override
        public void usarHabilidade() {
            System.out.println("[Pete] Habilidade: Tempo estendido ativado para a próxima pergunta!");
        }

        /** Pete tem o dobro de tempo nas perguntas cronometradas */
        @Override
        public double multiplicadorTempo() {
            return 2.0;
        }
    }

    // =========================================================
    // HANNY — Estudante de Português
    // 120 HP, 22 atk | Habilidade: elimina 1 alternativa errada
    // =========================================================
    public static class Hanny extends Player {
        public Hanny() {
            super("Hanny", 120, 22);
        }

        @Override
        public String getDescricao() {
            return "Hanny é estudante de língua portuguesa. Mais resistente que Pete, " +
                   "mas com ataque menor. Sua habilidade elimina uma alternativa errada nas perguntas difíceis.";
        }

        @Override
        public void usarHabilidade() {
            System.out.println("[Hanny] Habilidade: Uma alternativa errada foi eliminada!");
        }

        /** Hanny tem tempo normal */
        @Override
        public double multiplicadorTempo() {
            return 1.0;
        }
    }
}

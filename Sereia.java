/** Fase 3 — sempre em ataque; ataca 3x logo no início. */
public class Sereia extends Enemy {
    private int ataquesIniciaisRestantes = 3;

    public Sereia() {
        super("Sereia", 80, 15, true);
    }

    /** Ataque especial: os 3 primeiros ataques chegam antes das perguntas. */
    public boolean temAtaquesIniciais() {
        return ataquesIniciaisRestantes > 0;
    }

    public void ataqueInicial(Player jogador) {
        if (ataquesIniciaisRestantes > 0) {
            System.out.println("  >> A Sereia surge das águas e ataca furiosamente!");
            jogador.receberDano(danoAtaque);
            ataquesIniciaisRestantes--;
        }
    }
}

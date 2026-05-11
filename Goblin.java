/** Fase 4 — chefe final. Dano alto, tem koopas como escudo. */
public class Goblin extends Enemy {
    private int koopasVivos = 3;

    public Goblin() {
        super("Goblin Rei", 120, 25, true);
    }

    public boolean temKoopas() { return koopasVivos > 0; }

    public void eliminarKoopa() {
        if (koopasVivos > 0) {
            koopasVivos--;
            System.out.println("  >> Um Koopa foi destruído! Koopas restantes: " + koopasVivos);
        }
    }

    /** Ataque do chão de lava: dano extra ao jogador. */
    public void ataqueLava(Player jogador) {
        System.out.println("  >> O CHÃO DE LAVA EXPLODE sob seus pés!");
        jogador.receberDano(10);
    }

    @Override
    public void atacar(Player jogador) {
        System.out.println("  >> O Goblin Rei grita e lança um ataque devastador!");
        jogador.receberDano(danoAtaque);
    }

    public int getKoopasVivos() { return koopasVivos; }
}

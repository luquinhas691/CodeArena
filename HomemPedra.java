/** Fase 1 — inofensivo, só bloqueia o caminho. */
public class HomemPedra extends Enemy {
    public HomemPedra() {
        super("Homem de Pedra", 999, 0, false);
    }

    @Override
    public void atacar(Player jogador) {
        System.out.println("  >> O Homem de Pedra te olha com indiferença. Ele não ataca — ainda.");
    }
}

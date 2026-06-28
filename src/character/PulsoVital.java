package character;

public class PulsoVital implements Habilidade {

    public static final int CURA = 30;

    private boolean disponivel = true;
    private final Player portador;

    public PulsoVital(Player portador) {
        this.portador = portador;
    }

    @Override public String getNome()         { return "Pulso Vital"; }
    @Override public String getDescricao()    { return "Canaliza energia e recupera " + CURA + " pontos de vida!"; }
    @Override public boolean estaDisponivel() { return disponivel; }

    @Override
    public void ativar() {
        if (!disponivel) { System.out.println("[Habilidade ja utilizada nesta fase]"); return; }
        disponivel = false;
        System.out.println("\n HABILIDADE ATIVA — " + getNome() + "!");
        System.out.println("   " + getDescricao());
        portador.curar(CURA);
    }

    @Override
    public void resetar() {
        disponivel = true;
    }
}

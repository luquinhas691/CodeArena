package character;

// Concede tempo extra para responder questões
public class HabilidadeTempoExtra implements Habilidade {

    /** Segundos extras concedidos ao tempo padrão da questão. */
    public static final int BONUS_SEGUNDOS = 15;

    private boolean disponivel = true;

    @Override
    public String getNome() {
        return "Controle do Tempo";
    }

    @Override
    public String getDescricao() {
        return "Pete manipula o fluxo temporal: +" + BONUS_SEGUNDOS
                + "s em cada questão cronometrada!";
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public void ativar() {
        if (!disponivel) {
            System.out.println("[Habilidade já utilizada]");
            return;
        }
        disponivel = false;
        System.out.println("\n✨ HABILIDADE ATIVA — " + getNome() + "!");
        System.out.println("   " + getDescricao());
    }
}

package character;

/**
 * Habilidade de Pete: Controle do Tempo.
 *
 * Efeito passivo que o BattleManager lê via {@link Habilidade} para
 * aumentar o tempo limite das {@code TimedQuestion} da fase 4.
 *
 * Uso único: após consultado, marca-se como consumido.
 */
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

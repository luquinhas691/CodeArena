package character;

/** Interface comum a todas as habilidades especiais dos personagens. */
public interface Habilidade {
    String getNome();
    String getDescricao();
    boolean estaDisponivel();
    void ativar();
    void resetar();
}

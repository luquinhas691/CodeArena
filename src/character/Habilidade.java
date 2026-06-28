package character;

public interface Habilidade {
    String getNome();
    String getDescricao();
    boolean estaDisponivel();
    void ativar();
    void resetar();
}

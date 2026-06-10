package character;

// Interface das habilidades dos personagens
public interface Habilidade {

    /** Nome descritivo exibido ao jogador. */
    String getNome();

    /** Descrição breve do efeito da habilidade. */
    String getDescricao();

    /**
     * Indica se a habilidade ainda pode ser usada nesta sessão.
     * Habilidades de uso único retornam false após o primeiro acionamento.
     */
    boolean estaDisponivel();

    /**
     * Ativa a habilidade, registrando que foi consumida.
     * Implementações devem imprimir a mensagem de ativação ao usuário.
     */
    void ativar();
}

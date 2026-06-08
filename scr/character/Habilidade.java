package character;

/**
 * Contrato de uma habilidade especial de personagem.
 *
 * O BattleManager manipula habilidades exclusivamente por este tipo,
 * sem conhecer implementações concretas, garantindo o polimorfismo
 * solicitado pelo projeto.
 */
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

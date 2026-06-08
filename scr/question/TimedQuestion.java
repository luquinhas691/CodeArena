package question;

/**
 * Interface que define o contrato para questões com tempo limitado.
 * O jogo manipula objetos TimedQuestion por meio desta interface,
 * sem depender de implementações concretas.
 */
public interface TimedQuestion {

    /** Retorna o tempo máximo (em segundos) para responder. */
    int getTempoLimite();

    /**
     * Exibe o enunciado com aviso do tempo restante.
     * Implementações podem formatar o cabeçalho como quiserem.
     */
    void mostrarEnunciadoComTempo();

    /**
     * Exibe as alternativas da questão.
     */
    void mostrarAlternativas();

    /**
     * Lê a resposta do usuário respeitando o tempo limite.
     * Se o tempo esgotar antes de uma resposta, retorna null.
     *
     * @return a resposta digitada, ou null se o tempo esgotou
     */
    String lerRespostaComTempo();

    /**
     * Valida se a resposta fornecida está correta.
     *
     * @param respostaUsuario texto digitado pelo jogador
     * @return true se correta, false caso contrário
     */
    boolean validarResposta(String respostaUsuario);

    /**
     * Retorna a Question subjacente, permitindo que o BattleManager
     * acesse metadados (score, enunciado puro, etc.) sem downcast.
     */
    Question getQuestion();
}

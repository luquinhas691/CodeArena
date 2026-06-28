package question;

/**
 * Questão de múltipla escolha — formato original do jogo.
 * Formato no arquivo:
 *   MULTIPLA
 *   <enunciado>
 *   <alt1>|<alt2>|<alt3>|<alt4>
 *   <resposta>
 *   <score>
 */
public class MultipleChoiceQuestion extends Question {

    private final String[] alternativas;

    public MultipleChoiceQuestion(String enunciado, String[] alternativas,
                                  String resposta, int score) {
        super(enunciado, resposta, score);
        this.alternativas = alternativas;
    }

    @Override
    public void mostrarAlternativas() {
        System.out.println("   [Múltipla escolha]");
        for (String alt : alternativas) {
            System.out.println("   " + alt.trim());
        }
    }
}

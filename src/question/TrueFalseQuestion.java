package question;

/**
 * Questão de verdadeiro ou falso.
 * Formato no arquivo:
 *   VF
 *   <enunciado>
 *   <V ou F>
 *   <score>
 */
public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String enunciado, String resposta, int score) {
        super(enunciado, resposta, score);
    }

    @Override
    public void mostrarAlternativas() {
        System.out.println("   [Verdadeiro ou Falso]");
        System.out.println("   V - Verdadeiro");
        System.out.println("   F - Falso");
    }
}

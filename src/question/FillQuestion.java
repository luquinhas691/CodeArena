package question;

/**
 * Questão de preenchimento — jogador digita a resposta livremente.
 * Formato no arquivo:
 *   PREENCHER
 *   <enunciado com ___ no lugar da lacuna>
 *   <resposta>
 *   <score>
 */
public class FillQuestion extends Question {

    public FillQuestion(String enunciado, String resposta, int score) {
        super(enunciado, resposta, score);
    }

    @Override
    public void mostrarAlternativas() {
        System.out.println("   [Preencha a lacuna]");
        System.out.println("   Digite sua resposta:");
    }
}

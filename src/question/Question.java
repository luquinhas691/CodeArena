package question;

/**
 * Classe base abstrata para todos os tipos de questão.
 * Subclasses: MultipleChoiceQuestion, TrueFalseQuestion, FillQuestion.
 */
public abstract class Question {

    protected String enunciado;
    protected String resposta;
    protected int    score;

    public Question(String enunciado, String resposta, int score) {
        this.enunciado = enunciado;
        this.resposta  = resposta;
        this.score     = score;
    }

    public String getEnunciado() { return enunciado; }
    public String getResposta()  { return resposta; }
    public int    getScore()     { return score; }

    public void mostrarEnunciado() {
        System.out.println("\n" + enunciado);
    }

    public abstract void mostrarAlternativas();

    public boolean validarResposta(String respostaUsuario) {
        return respostaUsuario.trim().equalsIgnoreCase(resposta.trim());
    }
}

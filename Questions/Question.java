public class Question {
    private String enunciado;
    private String[] alternativas;
    private String resposta;
    private int score;
    // Classe question geral
    public Question(String enunciado, String[] alternativas, String resposta, int score) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.resposta = resposta;
        this.score = score;
    }

    public String getEnunciado() { return enunciado; }
    public String[] getAlternativas() { return alternativas; }
    public String getResposta() { return resposta; }
    public int getScore() { return score; }

    public void mostrarEnunciado() {
        System.out.println(enunciado);
    }

    public void mostrarAlternativas() {
        if (alternativas != null) {
            for (String alt : alternativas) {
                System.out.println("   " + alt.trim());
            }
        }
    }
}
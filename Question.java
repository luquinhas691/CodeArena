public class Question {
    
    private String enunciado;
    private String[] alternativas;
    private String resposta;
    private int score;

    // Construtor
    public Question(String enunciado, String[] alternativas, String resposta, int score) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.resposta = resposta;
        this.score = score;
    }

    // Getters 
    public String getEnunciado() { return enunciado;}
    public String[] getAlternativas() {return alternativas;}
    public String getResposta() {return resposta;}
    public int getScore() {return score;}

    // Setters
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public void setAlternativas(String[] alternativas) {this.alternativas = alternativas;}
    public void setResposta(String resposta) {this.resposta = resposta;}
    public void setScore(int score) {this.score = score;}

    // Funções porra
    
    public void mostrarEnunciado() {
        System.out.println("Enunciado: " + this.enunciado);
    }

    public void mostrarAlternativas() {
        if (alternativas != null) {
            for (String alt : alternativas) {
                System.out.println("  " + alt.trim());
            }
        }
    }

    public boolean validarResposta(String entradaUsuario) {
        if (entradaUsuario == null) return false;
        return entradaUsuario.trim().equalsIgnoreCase(this.resposta.trim());
    }
}
public class Question {
    String enunciado;         
    String[] alternativas;        
    int resposta;  
    int score;              

    public Question(String enunciado, String[] alternativas, int resposta, int score) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.resposta = resposta;
        this.score = score;
        
    }
}
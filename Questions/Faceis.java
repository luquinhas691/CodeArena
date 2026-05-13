import java.util.List;

public class Faceis {
    private List<Question> lista;

    public Faceis(QuestionLoader loader) {
        this.lista = loader.getFaceis();
    }

    // Printa todas uma por uma
    public void printarTodas() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print("Questão " + (i + 1) + ": ");
            lista.get(i).mostrarEnunciado();
        }
    }

    // Printa apenas a questão específica solicitada no Main
    public void printarQuestao(int numero) {
        int indice = numero - 1;
        if (indice >= 0 && indice < lista.size()) {
            Question q = lista.get(indice);
            q.mostrarEnunciado();
            q.mostrarAlternativas();
        } else {
            System.out.println("Erro: Questão " + numero + " não encontrada no nível Fácil.");
        }
    }
}
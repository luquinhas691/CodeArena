import java.util.List;

public class MuitoDificeis {
    private List<Question> lista;

    public MuitoDificeis(QuestionLoader loader) {
        this.lista = loader.getMuitoDificeis();
    }

    public void printarTodas() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print("Questão " + (i + 1) + ": ");
            lista.get(i).mostrarEnunciado();
        }
    }

    public void printarQuestao(int numero) {
        int indice = numero - 1;
        if (indice >= 0 && indice < lista.size()) {
            Question q = lista.get(indice);
            q.mostrarEnunciado();
            q.mostrarAlternativas();
        }
    }
}
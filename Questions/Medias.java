import java.util.List;

public class Medias {
    private List<Question> lista;

    public Medias(QuestionLoader loader) {
        this.lista = loader.getMedias();
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
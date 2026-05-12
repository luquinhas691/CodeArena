import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * Carrega perguntas de um arquivo .txt e permite filtrá-las por dificuldade.
 *
 * Formato esperado no .txt (cada bloco separado por linha em branco):
 *
 *   Enunciado da pergunta
 *   A) op1|B) op2|C) op3|D) op4
 *   B
 *   2
 *   MEDIO
 *
 * NOTA: o arquivo portuguesequestion.txt atual não tem a linha de dificuldade.
 * Para compatibilidade, se a 5ª linha não for FACIL/MEDIO/DIFICIL,
 * o loader atribui FACIL como padrão.
 */
public class LoaderQuestion {

    private List<Question> todasPerguntas = new ArrayList<>();

    public LoaderQuestion(String caminhoArquivo) {
        carregarArquivo(caminhoArquivo);
    }

    private void carregarArquivo(String caminho) {
        File file = new File(caminho);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String enunciado = sc.nextLine().trim();
                if (enunciado.isEmpty()) continue;

                String linhaAlternativas = sc.hasNextLine() ? sc.nextLine() : "";
                String resposta          = sc.hasNextLine() ? sc.nextLine().trim() : "A";
                String pontosStr         = sc.hasNextLine() ? sc.nextLine().trim() : "1";
                String difStr            = sc.hasNextLine() ? sc.nextLine().trim() : "FACIL";

                String[] alternativas = linhaAlternativas.split("\\|");
                int pontos = parseSafe(pontosStr, 1);
                Question.Dificuldade dif = parseDificuldade(difStr);

                todasPerguntas.add(new Question(enunciado, alternativas, resposta, pontos, dif));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de perguntas: " + e.getMessage());
        } finally {
            if (sc != null) sc.close();
        }
    }

    /** Retorna uma lista embaralhada de perguntas de uma dificuldade específica */
    public List<Question> getPorDificuldade(Question.Dificuldade dificuldade, int quantidade) {
        List<Question> filtradas = new ArrayList<>();
        for (Question q : todasPerguntas) {
            if (q.getDificuldade() == dificuldade) filtradas.add(q);
        }
        java.util.Collections.shuffle(filtradas);
        return filtradas.subList(0, Math.min(quantidade, filtradas.size()));
    }

    /** Retorna todas as perguntas carregadas */
    public List<Question> getTodas() { return todasPerguntas; }

    /** Conta quantas perguntas há de cada dificuldade */
    public void imprimirResumo() {
        int f = 0, m = 0, d = 0;
        for (Question q : todasPerguntas) {
            switch (q.getDificuldade()) {
                case FACIL:  f++; break;
                case MEDIO:  m++; break;
                case DIFICIL: d++; break;
            }
        }
        System.out.println("Perguntas carregadas — FÁCIL: " + f + " | MÉDIO: " + m + " | DIFÍCIL: " + d);
    }

    private int parseSafe(String s, int padrao) {
        try { return Integer.parseInt(s); } catch (Exception e) { return padrao; }
    }

    private Question.Dificuldade parseDificuldade(String s) {
        switch (s.toUpperCase()) {
            case "MEDIO":   return Question.Dificuldade.MEDIO;
            case "DIFICIL": return Question.Dificuldade.DIFICIL;
            default:        return Question.Dificuldade.FACIL;
        }
    }
}

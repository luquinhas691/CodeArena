import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionLoader {
    private List<Question> faceis = new ArrayList<>();
    private List<Question> medias = new ArrayList<>();
    private List<Question> dificeis = new ArrayList<>();
    private List<Question> muitoDificeis = new ArrayList<>();

    public QuestionLoader(String caminhoArquivo) {
        carregarDados(caminhoArquivo);
    }

    private void carregarDados(String caminho) {
        try (Scanner leitor = new Scanner(new File(caminho), "UTF-8")) {
            while (leitor.hasNextLine()) {
                String enunciado = leitor.nextLine().trim();
                
                if (enunciado.isEmpty()) continue;

                String linhaAlts = leitor.hasNextLine() ? leitor.nextLine() : "";
                String resposta  = leitor.hasNextLine() ? leitor.nextLine().trim() : "";
                String scoreStr  = leitor.hasNextLine() ? leitor.nextLine().trim() : "1";

                String[] alternativas = linhaAlts.split("\\|");
                int score;
                try {
                    score = Integer.parseInt(scoreStr);
                } catch (NumberFormatException e) {
                    score = 1; // Default caso o arquivo esteja cagado
                }

                Question q = new Question(enunciado, alternativas, resposta, score);
                
                // Distribuição baseada no score do arquivo
                switch (score) {
                    case 1 -> faceis.add(q);
                    case 2 -> medias.add(q);
                    case 3 -> dificeis.add(q);
                    case 5 -> muitoDificeis.add(q);
                    default -> faceis.add(q);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado no caminho: " + caminho);
        }
    }

    // Getters
    public List<Question> getFaceis() { return faceis; }
    public List<Question> getMedias() { return medias; }
    public List<Question> getDificeis() { return dificeis; }
    public List<Question> getMuitoDificeis() { return muitoDificeis; }
}
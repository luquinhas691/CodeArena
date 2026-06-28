package question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionLoader {
    private List<Question> poolA = new ArrayList<>();
    private List<Question> poolB = new ArrayList<>();
    private int idxA = 0;
    private int idxB = 0;

    public QuestionLoader(String arquivoA, String arquivoB) {
        carregarDados(arquivoA, poolA);
        carregarDados(arquivoB, poolB);
        Collections.shuffle(poolA);
        Collections.shuffle(poolB);
    }

    public QuestionLoader(String arquivoA) {
        carregarDados(arquivoA, poolA);
        Collections.shuffle(poolA);
    }

    private void carregarDados(String nomeArquivo, List<Question> pool) {
        try (InputStream is = QuestionLoader.class.getClassLoader().getResourceAsStream(nomeArquivo);
             BufferedReader leitor = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String enunciado = linha.trim();
                if (enunciado.isEmpty()) continue;
                String linhaAlts = leitor.readLine();
                String resposta  = leitor.readLine();
                String scoreStr  = leitor.readLine();
                if (linhaAlts == null || resposta == null) continue;
                String[] alternativas = linhaAlts.split("\\|");
                int score = 1;
                try { if (scoreStr != null) score = Integer.parseInt(scoreStr.trim()); }
                catch (NumberFormatException e) {}
                pool.add(new Question(enunciado, alternativas, resposta.trim(), score));
            }
        } catch (Exception e) {
            System.err.println("ERRO ao ler '" + nomeArquivo + "': " + e.getMessage());
            System.exit(1);
        }
    }

    public Question proximaPerguntaA() {
        if (idxA < poolA.size()) return poolA.get(idxA++);
        return null;
    }

    public Question proximaPerguntaB() {
        if (idxB < poolB.size()) return poolB.get(idxB++);
        return null;
    }

    public boolean validarResposta(Question q, String respostaUsuario) {
        return q.validarResposta(respostaUsuario);
    }

    public List<Question> getPoolA() { return Collections.unmodifiableList(poolA); }
    public List<Question> getPoolB() { return Collections.unmodifiableList(poolB); }

    public void resetar() {
        Collections.shuffle(poolA);
        Collections.shuffle(poolB);
        idxA = 0; idxB = 0;
    }
}

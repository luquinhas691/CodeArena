package question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Carrega questões de arquivos de texto.
 * Detecta o tipo pela primeira linha do bloco: MULTIPLA, VF ou PREENCHER.
 *
 * Formato geral — cada questão ocupa um bloco separado por linha vazia:
 *
 *   MULTIPLA
 *   <enunciado>
 *   <alt1>|<alt2>|<alt3>|<alt4>
 *   <resposta>
 *   <score>
 *
 *   VF
 *   <enunciado>
 *   <V ou F>
 *   <score>
 *
 *   PREENCHER
 *   <enunciado com ___ na lacuna>
 *   <resposta>
 *   <score>
 *
 * Arquivos sem marcador de tipo são tratados como MULTIPLA (retrocompatibilidade).
 */
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

    // ------------------------------------------------------------------
    //  Leitura e detecção de tipo
    // ------------------------------------------------------------------

    private void carregarDados(String nomeArquivo, List<Question> pool) {
        try (InputStream is = QuestionLoader.class.getClassLoader().getResourceAsStream(nomeArquivo);
             BufferedReader leitor = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                Question q = lerProximaQuestao(linha, leitor);
                if (q != null) pool.add(q);
            }
        } catch (Exception e) {
            System.err.println("ERRO ao ler '" + nomeArquivo + "': " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Lê um bloco de questão a partir da primeira linha já lida.
     * Detecta o tipo e delega para o método correspondente.
     */
    private Question lerProximaQuestao(String primeiraLinha, BufferedReader leitor)
            throws Exception {

        switch (primeiraLinha.toUpperCase()) {
            case "VF":
                return lerVF(leitor);
            case "PREENCHER":
                return lerPreencher(leitor);
            case "MULTIPLA":
                return lerMultipla(leitor);
            default:
                // Retrocompatibilidade: primeira linha é o enunciado (sem marcador)
                return lerMultiplaLegado(primeiraLinha, leitor);
        }
    }

    private MultipleChoiceQuestion lerMultipla(BufferedReader leitor) throws Exception {
        String enunciado = lerLinha(leitor);
        String altsLinha = lerLinha(leitor);
        String resposta  = lerLinha(leitor);
        int score        = lerScore(leitor);
        if (enunciado == null || altsLinha == null || resposta == null) return null;
        return new MultipleChoiceQuestion(enunciado, altsLinha.split("\\|"), resposta.trim(), score);
    }

    /** Formato antigo: enunciado já foi lido como primeira linha. */
    private MultipleChoiceQuestion lerMultiplaLegado(String enunciado, BufferedReader leitor)
            throws Exception {
        String altsLinha = lerLinha(leitor);
        String resposta  = lerLinha(leitor);
        int score        = lerScore(leitor);
        if (altsLinha == null || resposta == null) return null;
        return new MultipleChoiceQuestion(enunciado, altsLinha.split("\\|"), resposta.trim(), score);
    }

    private TrueFalseQuestion lerVF(BufferedReader leitor) throws Exception {
        String enunciado = lerLinha(leitor);
        String resposta  = lerLinha(leitor);
        int score        = lerScore(leitor);
        if (enunciado == null || resposta == null) return null;
        return new TrueFalseQuestion(enunciado, resposta.trim().toUpperCase(), score);
    }

    private FillQuestion lerPreencher(BufferedReader leitor) throws Exception {
        String enunciado = lerLinha(leitor);
        String resposta  = lerLinha(leitor);
        int score        = lerScore(leitor);
        if (enunciado == null || resposta == null) return null;
        return new FillQuestion(enunciado, resposta.trim(), score);
    }

    // ------------------------------------------------------------------
    //  Auxiliares de leitura
    // ------------------------------------------------------------------

    private String lerLinha(BufferedReader leitor) throws Exception {
        String linha = leitor.readLine();
        return linha != null ? linha.trim() : null;
    }

    private int lerScore(BufferedReader leitor) throws Exception {
        String linha = lerLinha(leitor);
        try { if (linha != null) return Integer.parseInt(linha.trim()); }
        catch (NumberFormatException ignored) {}
        return 1;
    }

    // ------------------------------------------------------------------
    //  API pública
    // ------------------------------------------------------------------

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

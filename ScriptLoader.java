import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScriptLoader {
    private Map<String, String> blocosRoteiro = new HashMap<>();

    public ScriptLoader(String caminhoArquivo) {
        carregarRoteiro(caminhoArquivo);
    }

    private void carregarRoteiro(String caminho) {
        try (Scanner leitor = new Scanner(new File(caminho), "UTF-8")) {
            String tagAtual = "";
            StringBuilder conteudoAtual = new StringBuilder();

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();

                if (linha.startsWith("[")) {
                    if (!tagAtual.isEmpty()) {
                        blocosRoteiro.put(tagAtual, conteudoAtual.toString().trim());
                        conteudoAtual.setLength(0);
                    }
                    tagAtual = linha.replace("[", "").replace("]", "");
                } else if (!linha.isEmpty()) {
                    conteudoAtual.append(linha).append("\n");
                }
            }
            if (!tagAtual.isEmpty()) {
                blocosRoteiro.put(tagAtual, conteudoAtual.toString().trim());
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler roteiro: " + e.getMessage());
        }
    }

    public String getText(String tag) {
        return blocosRoteiro.getOrDefault(tag, "Texto não encontrado: " + tag);
    }
}
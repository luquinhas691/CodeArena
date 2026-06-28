package game;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class ScriptLoader {
    private Map<String, String> blocos = new HashMap<>();

    public ScriptLoader(String nomeArquivo) {
        try (InputStream is = ScriptLoader.class.getClassLoader().getResourceAsStream(nomeArquivo);
             BufferedReader leitor = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String tagAtual = "";
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.startsWith("[")) {
                    if (!tagAtual.isEmpty()) {
                        blocos.put(tagAtual, conteudo.toString().trim());
                        conteudo.setLength(0);
                    }
                    tagAtual = linha.replace("[", "").replace("]", "");
                } else if (!linha.isEmpty()) {
                    conteudo.append(linha).append("\n");
                }
            }
            if (!tagAtual.isEmpty()) blocos.put(tagAtual, conteudo.toString().trim());
        } catch (Exception e) {
            System.err.println("ERRO ao ler '" + nomeArquivo + "': " + e.getMessage());
            System.exit(1);
        }
    }

    public String getText(String tag) {
        return blocos.getOrDefault(tag, "[Texto nao encontrado: " + tag + "]");
    }
}

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;

/**
 * Lê o arquivo Roteiro.txt e imprime trechos por tag,
 * exibindo palavra a palavra para efeito de leitura dramática.
 *
 * Formato do arquivo:
 *   [NOME_DA_TAG]
 *   texto da cena...
 *   (linha em branco encerra o bloco da tag)
 *
 * Velocidade configurável: LENTA (150ms), NORMAL (80ms), RAPIDA (30ms).
 */
public class RoteiroReader {

    public enum Velocidade {
        LENTA(150), NORMAL(80), RAPIDA(30);
        final int msEntreTokens;
        Velocidade(int ms) { this.msEntreTokens = ms; }
    }

    private Map<String, String> cenas = new HashMap<>();
    private Velocidade velocidade;

    public RoteiroReader(String caminhoArquivo, Velocidade velocidade) {
        this.velocidade = velocidade;
        carregarArquivo(caminhoArquivo);
    }

    private void carregarArquivo(String caminho) {
        try {
            Scanner sc = new Scanner(new File(caminho));
            String tagAtual = null;
            StringBuilder buffer = new StringBuilder();

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();

                if (linha.startsWith("[") && linha.endsWith("]")) {
                    // Salva bloco anterior
                    if (tagAtual != null) {
                        cenas.put(tagAtual, buffer.toString().trim());
                    }
                    tagAtual = linha.substring(1, linha.length() - 1);
                    buffer = new StringBuilder();
                } else {
                    buffer.append(linha).append("\n");
                }
            }
            // Salva último bloco
            if (tagAtual != null) {
                cenas.put(tagAtual, buffer.toString().trim());
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("[RoteiroReader] Arquivo não encontrado: " + caminho);
        }
    }

    /**
     * Exibe o trecho da cena palavra a palavra.
     * @param tag ex: "INTRO", "FASE1_ENTRADA", "VITORIA"
     */
    public void exibir(String tag) {
        String texto = cenas.get(tag);
        if (texto == null) {
            System.out.println("[Roteiro] Cena não encontrada: " + tag);
            return;
        }

        System.out.println();
        String[] linhas = texto.split("\n");
        for (String linha : linhas) {
            String[] palavras = linha.split(" ");
            for (String palavra : palavras) {
                System.out.print(palavra + " ");
                try { Thread.sleep(velocidade.msEntreTokens); }
                catch (InterruptedException ignored) {}
            }
            System.out.println(); // quebra de linha ao fim de cada linha do roteiro
        }
        System.out.println();
    }

    /**
     * Versão alternativa: imprime letra a letra (mais dramático).
     */
    public void exibirLetraALetra(String tag) {
        String texto = cenas.get(tag);
        if (texto == null) {
            System.out.println("[Roteiro] Cena não encontrada: " + tag);
            return;
        }

        System.out.println();
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            int delay = (c == '.' || c == '!') ? velocidade.msEntreTokens * 4
                      : (c == ',') ? velocidade.msEntreTokens * 2
                      : velocidade.msEntreTokens / 4;
            try { Thread.sleep(delay); }
            catch (InterruptedException ignored) {}
        }
        System.out.println("\n");
    }

    public void setVelocidade(Velocidade v) { this.velocidade = v; }
}

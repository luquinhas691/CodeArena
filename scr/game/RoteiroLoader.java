package game;

// Exibe as partes da história do jogo
public class RoteiroLoader {
    private ScriptLoader loader;

    /**
     * Milissegundos de espera base por linha de texto exibida.
     * Ajuste este valor para calibrar o ritmo da narrativa.
     */
    private static final int MS_POR_LINHA = 1800;

    /** Pausa mínima garantida após qualquer bloco (ms). */
    private static final int PAUSA_MINIMA = 1500;

    public RoteiroLoader(String caminho) {
        this.loader = new ScriptLoader(caminho);
    }

    // ---------------------------------------------------------------
    //  Métodos de exibição – todos delegam para exibir()
    // ---------------------------------------------------------------

    public void introducao()      { exibir("INTRODUCAO"); }
    public void pete()            { exibir("PETE"); }
    public void hanny()           { exibir("HANNY"); }
    public void introducaoPete()  { exibir("INTRODUCAO_PETE"); }
    public void introducaoHanny() { exibir("INTRODUCAO_HANNY"); }

    public void fase1()      { exibir("FASE1"); }
    public void fase1Perda() { exibir("FASE1_PERDA"); }
    public void fase1Ganho() { exibir("FASE1_GANHO"); }

    public void fase2()      { exibir("FASE2"); }
    public void fase2Perda() { exibir("FASE2_PERDA"); }
    public void fase2Ganho() { exibir("FASE2_GANHO"); }

    public void fase3()      { exibir("FASE3"); }
    public void fase3Perda() { exibir("FASE3_PERDA"); }
    public void fase3Ganho() { exibir("FASE3_GANHO"); }

    public void fase4()      { exibir("FASE4"); }
    public void fase4Perda() { exibir("FASE4_PERDA"); }
    public void fase4Ganho() { exibir("FASE4_GANHO"); }

    public void finalGame()  { exibir("FINAL_GAME"); }

    // ---------------------------------------------------------------
    //  Lógica de exibição com pausa proporcional
    // ---------------------------------------------------------------

    /**
     * Recupera o texto da tag e o imprime linha a linha com efeito
     * "máquina de escrever" suave. Ao final, pausa proporcional ao
     * número de linhas para que o jogador possa ler confortavelmente.
     */
    private void exibir(String tag) {
        String texto = loader.getText(tag);
        System.out.println(texto);

        int linhas = texto.split("\n").length;
        long pausa = Math.max(PAUSA_MINIMA, (long) linhas * MS_POR_LINHA);

        aguardar(pausa);
        System.out.println(); // linha em branco para separação visual
    }

    /** Dorme silenciosamente pelo tempo indicado (ms). */
    private static void aguardar(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

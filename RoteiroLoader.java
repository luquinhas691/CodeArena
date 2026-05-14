public class RoteiroLoader {
    private ScriptLoader loader;

    public RoteiroLoader(String caminhoArquivo) {
        this.loader = new ScriptLoader(caminhoArquivo);
    }

    // --- MÉTODOS DE INTRODUÇÃO E PERSONAGENS ---

    public void introducao() {
        System.out.println(loader.getText("INTRODUCAO"));
    }

    public void pete() {
        System.out.println(loader.getText("PETE"));
    }

    public void hanny() {
        System.out.println(loader.getText("HANNY"));
    }

    public void introducaoPete() {
        System.out.println(loader.getText("INTRODUCAO_PETE"));
    }

    public void introducaoHanny() {
        System.out.println(loader.getText("INTRODUCAO_HANNY"));
    }

    // --- FASE 1 ---

    public void fase1() {
        System.out.println(loader.getText("FASE1"));
    }

    public void fase1Perda() {
        System.out.println(loader.getText("FASE1_PERDA"));
    }

    public void fase1Ganho() {
        System.out.println(loader.getText("FASE1_GANHO"));
    }

    // --- FASE 2 ---

    public void fase2() {
        System.out.println(loader.getText("FASE2"));
    }

    public void fase2Perda() {
        System.out.println(loader.getText("FASE2_PERDA"));
    }

    public void fase2Ganho() {
        System.out.println(loader.getText("FASE2_GANHO"));
    }

    // --- FASE 3 ---

    public void fase3() {
        System.out.println(loader.getText("FASE3"));
    }

    public void fase3Perda() {
        System.out.println(loader.getText("FASE3_PERDA"));
    }

    public void fase3Ganho() {
        System.out.println(loader.getText("FASE3_GANHO"));
    }

    // --- FASE 4 E FINAL ---

    public void fase4() {
        System.out.println(loader.getText("FASE4"));
    }

    public void fase4Perda() {
        System.out.println(loader.getText("FASE4_PERDA"));
    }

    public void fase4Ganho() {
        System.out.println(loader.getText("FASE4_GANHO"));
    }

    public void finalGame() {
        System.out.println(loader.getText("FINAL_GAME"));
    }
}
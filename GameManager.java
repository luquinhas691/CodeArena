public class GameManager {
    private ScriptLoader script;
    private String personagemEscolhido;
    private int velocidadeSoneca = 25; // Milissegundos entre letras

    public GameManager(ScriptLoader sLoader) {
        this.script = sLoader;
    }

    // O MOTOR DE ATRASO: Ninguém mais vê, ele trabalha sozinho
    private void imprimirComSoneca(String texto) {
        if (texto == null) return;
        
        for (char letra : texto.toCharArray()) {
            System.out.print(letra);
            try {
                Thread.sleep(velocidadeSoneca);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); // Pula linha automático no fim
    }

    // FUNÇÃO ÚNICA: Você só usa essa para o roteiro inteiro
    public void dizer(String tag) {
        String conteudo = script.getTexto(tag);
        imprimirComSoneca(conteudo);
    }

    // Atalhos para o seu Main continuar funcionando sem erro
    public void exibirIntro() {
        dizer("INTRODUCAO");
    }

    public void escolherPersonagem(String nome) {
        this.personagemEscolhido = nome.toUpperCase();
        dizer("INTRODUCAO_" + personagemEscolhido);
    }

    public void iniciarFase1(boolean venceu) {
        dizer("FASE1");
        if (venceu) {
            dizer("FASE1_GANHO");
        } else {
            dizer("FASE1_PERDA");
        }
    }
}
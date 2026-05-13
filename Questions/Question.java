/**
 * Representa uma pergunta do jogo.
 * O arquivo .txt deve seguir o formato:
 *
 *   Enunciado da pergunta
 *   A) op1|B) op2|C) op3|D) op4
 *   A          <- resposta correta
 *   2          <- pontos
 *   FACIL      <- dificuldade: FACIL, MEDIO ou DIFICIL
 *              <- linha em branco separando perguntas
 */
public class Question {
    private String enunciado;
    private String[] alternativas; // separadas por '|'
    private String respostaCorreta;
    private int pontos;
    private Dificuldade dificuldade;

    public enum Dificuldade {
        FACIL, MEDIO, DIFICIL
    }

    public Question(String enunciado, String[] alternativas, String respostaCorreta,
                    int pontos, Dificuldade dificuldade) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
        this.pontos = pontos;
        this.dificuldade = dificuldade;
    }

    public boolean verificarResposta(String escolha) {
        return escolha.trim().equalsIgnoreCase(respostaCorreta.trim());
    }

    /** Para a habilidade da Hanny: retorna uma alternativa errada aleatória para eliminar */
    public String alternativaErradaAleatoria() {
        java.util.List<String> erradas = new java.util.ArrayList<>();
        for (String alt : alternativas) {
            String letra = alt.trim().substring(0, 1);
            if (!letra.equalsIgnoreCase(respostaCorreta.trim())) {
                erradas.add(letra);
            }
        }
        if (erradas.isEmpty()) return null;
        return erradas.get((int)(Math.random() * erradas.size()));
    }

    // Getters
    public String getEnunciado()        { return enunciado; }
    public String[] getAlternativas()   { return alternativas; }
    public String getRespostaCorreta()  { return respostaCorreta; }
    public int getPontos()              { return pontos; }
    public Dificuldade getDificuldade() { return dificuldade; }

    /** Exibe a pergunta formatada no terminal */
    public void exibir() {
        System.out.println("\n========================================");
        System.out.println("QUESTÃO [" + dificuldade + "]: " + enunciado);
        for (String alt : alternativas) {
            System.out.println("  " + alt.trim());
        }
        System.out.println("========================================");
        System.out.print("Sua resposta: ");
    }
}

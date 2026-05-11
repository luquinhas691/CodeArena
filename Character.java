/**
 * Classe abstrata que representa um personagem do jogo.
 * Define atributos e comportamentos comuns a todos os personagens.
 */
public abstract class Character {
    private String nome;
    private int vidaMaxima;
    private int vida;
    private int ataque;

    public Character(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vidaMaxima = vida;
        this.vida = vida;
        this.ataque = ataque;
    }

    /** Aplica dano ao personagem, mínimo 0. */
    public void receberDano(int dano) {
        this.vida = Math.max(0, this.vida - dano);
    }

    /** Cura o personagem, limitado à vida máxima. */
    public void curar(int quantidade) {
        this.vida = Math.min(vidaMaxima, this.vida + quantidade);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    /** Retorna uma linha visual representando a barra de vida. */
    public String barraDeVida() {
        int total = 20;
        int cheios = (int) ((vida / (double) vidaMaxima) * total);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < total; i++) {
            sb.append(i < cheios ? "█" : "░");
        }
        sb.append("] " + vida + "/" + vidaMaxima + " HP");
        return sb.toString();
    }

    /** Retorna descrição especial do personagem (habilidade passiva, etc.). */
    public abstract String getDescricao();

    // ── Getters ──────────────────────────────────────────────
    public String getNome()     { return nome; }
    public int getVida()        { return vida; }
    public int getVidaMaxima()  { return vidaMaxima; }
    public int getAtaque()      { return ataque; }
}

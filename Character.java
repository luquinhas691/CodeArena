/**
 * Classe abstrata que representa um personagem do jogo.
 * Define atributos e comportamentos comuns a todos os personagens.
 */
public abstract class Character {
    private String nome;
    private int vida;
    private int ataque;
    private int vidaMaxima;

    public Character(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.vidaMaxima = vida;
    }

    // funcoes gerais do personagem

    /** Aplica dano ao personagem, mínimo 0. */
    public void receberDano(int dano) {
        this.vida = Math.max(0, this.vida - dano);
    }

    // Cura o personagem
    public void curar(int cura) {
        this.vida = Math.min(this.vida + cura, this.vidaMaxima);
        System.out.println("Você foi curado em " + cura + " pontos de vida.");
        System.out.println("Vida atual: " + this.barraDeVida());
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    /** Mostra a vida com NUMNEROS */
    public String barraDeVida() {
        return vida + "/" + vidaMaxima + " HP";
    }

    /** Retorna descrição do personagem. */
    public abstract String getDescricao();

    // Getters do personagem
    public String getNome()     { return nome; }
    public int getVida()        { return vida; }
    public int getVidaMaxima()  { return vidaMaxima; }
    public int getAtaque()      { return ataque; }
    // Setters do personagem
    public void setNome(String nome) { this.nome = nome; }
    public void setVida(int vida) { this.vida = vida; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

    public void mostrarStatus() {
        System.out.println("========================================");
        System.out.println("Você é : " + this.nome);
        System.out.println("Essa é sua barra de vida: " + this.barraDeVida());
        System.out.println("Voce consegue dar esse tanto de ataque: " + this.ataque);
        System.out.println("========================================");
    }


}
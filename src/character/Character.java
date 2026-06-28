package character;

/** Classe base abstrata para personagens do jogo (jogador e inimigos). */
public abstract class Character {
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int forca;

    public Character(String nome, int vida, int forca) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
    }

    public boolean estaVivo() { return vida > 0; }

    public String getNome()    { return nome; }
    public int    getVida()    { return vida; }
    public int    getForca()   { return forca; }
    public void   setForca(int forca) { this.forca = forca; }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }
}

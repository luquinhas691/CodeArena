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

    // Método essencial para saber se o personagem ainda pode lutar
    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { 
        this.vida = Math.max(0, Math.min(vida, vidaMaxima)); 
    }
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }
}
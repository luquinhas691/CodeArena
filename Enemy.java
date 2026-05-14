public class Enemy extends Character {

    public Enemy(String nome, int vida, int forca) {
        super(nome, vida, forca);
    }

    public void atacar(Player jogador) {
        int dano = this.getForca();
        System.out.println(this.getNome() + " ataca " + jogador.getNome() + " causando " + dano + " de dano!");
        jogador.receberDano(dano);
    }

    public void receberDano(int dano) {
        this.setVida(this.getVida() - dano);
        if (this.getVida() <= 0) {
            System.out.println("O " + this.getNome() + " foi derrotado!");
        }
    }
}
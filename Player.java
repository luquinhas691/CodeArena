public class Player extends Character {
    
    public Player(String nome, int vida, int forca) {
        super(nome, vida, forca); // Passa os dados para Character
    }

    public void atacar(Enemy inimigo) {
        int dano = this.getForca();
        System.out.println(this.getNome() + " ataca " + inimigo.getNome() + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }

    public void receberDano(int dano) {
        this.setVida(this.getVida() - dano);
        System.out.println(this.getNome() + " sofreu " + dano + " de dano. Vida: " + this.getVida());
    }

    public void curar(int cura) {
        this.setVida(this.getVida() + cura);
        System.out.println(this.getNome() + " se curou em " + cura + ". Vida: " + this.getVida());
    }

    // Parte em aberto para habilidade, será desenvolvido
    public void usarHabilidade() {
        if (this.getNome().equalsIgnoreCase("Pete")) {
            System.out.println("Pete usa: Controle do Tempo! O turno do inimigo foi retardado.");
        } else if (this.getNome().equalsIgnoreCase("Hanny")) {
            System.out.println("Hanny usa: Dedução Lógica! O próximo erro foi eliminado.");
        }
    }
}
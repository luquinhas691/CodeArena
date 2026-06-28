package character;

public abstract class Player extends Character {

    protected Habilidade habilidade;

    public Player(String nome, int vida, int forca) {
        super(nome, vida, forca);
    }

    public String  getNomeHabilidade()      { return habilidade.getNome(); }
    public String  getDescricaoHabilidade() { return habilidade.getDescricao(); }
    public boolean habilidadeDisponivel()   { return habilidade.estaDisponivel(); }
    public void    ativarHabilidade()       { habilidade.ativar(); }
    public void    resetarHabilidade()      { habilidade.resetar(); }

    public int consumirMultiplicadorDano() { return 1; }

    public void atacar(Enemy inimigo) {
        int multiplicador = consumirMultiplicadorDano();
        int dano = this.getForca() * multiplicador;
        if (multiplicador > 1) System.out.println("\n DANO DOBRADO!");
        System.out.println(this.getNome() + " ataca " + inimigo.getNome()
                + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }

    public void atacar(Enemy inimigo, int scoreDaQuestao) {
        int multiplicador = consumirMultiplicadorDano();
        int dano = (this.getForca() + scoreDaQuestao) * multiplicador;
        if (multiplicador > 1) System.out.println("\n DANO DOBRADO!");
        System.out.println(this.getNome() + " ataca " + inimigo.getNome()
                + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }

    public void receberDano(int dano) {
        this.setVida(this.getVida() - dano);
        System.out.println(this.getNome() + " sofreu " + dano
                + " de dano. Vida: " + this.getVida());
    }

    public void curar(int cura) {
        this.setVida(this.getVida() + cura);
        System.out.println(this.getNome() + " recuperou " + cura
                + " de vida. Vida: " + this.getVida());
    }
}

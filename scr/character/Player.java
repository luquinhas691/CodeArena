package character;

// Dados do personagem escolhido pelo jogador
public class Player extends Character {

    /** Habilidade especial deste personagem. Nunca null após construção. */
    private final Habilidade habilidade;

    public Player(String nome, int vida, int forca) {
        super(nome, vida, forca);
        this.habilidade = criarHabilidade(nome);
    }

    // ------------------------------------------------------------------
    //  Habilidade – exposta apenas pela interface
    // ------------------------------------------------------------------

  // Retorna a habilidade do personagem
    public Habilidade getHabilidade() {
        return habilidade;
    }

    // ------------------------------------------------------------------
    //  Combate
    // ------------------------------------------------------------------

    public void atacar(Enemy inimigo) {
        int dano = this.getForca();
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
        System.out.println(this.getNome() + " se curou em " + cura
                + ". Vida: " + this.getVida());
    }

    // ------------------------------------------------------------------
    //  Fábrica de habilidades (privado — não vaza implementações)
    // ------------------------------------------------------------------

    private static Habilidade criarHabilidade(String nome) {
        if (nome.equalsIgnoreCase("Pete"))  return new HabilidadeTempoExtra();
        if (nome.equalsIgnoreCase("Hanny")) return new HabilidadeAutoAcerto();
        // Personagem sem habilidade: retorna uma implementação nula segura
        return new HabilidadeNula();
    }

    // ------------------------------------------------------------------
    //  Implementação nula (Null Object) — evita null checks no jogo
    // ------------------------------------------------------------------

    private static class HabilidadeNula implements Habilidade {
        @Override public String getNome()       { return "Nenhuma"; }
        @Override public String getDescricao()  { return "Sem habilidade especial."; }
        @Override public boolean estaDisponivel() { return false; }
        @Override public void ativar()          { /* noop */ }
    }
}


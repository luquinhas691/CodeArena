package character;

public class Pete extends Player {

    private final GolpeDuplo golpeDuplo;

    public Pete() {
        super("Pete", 100, 35);
        this.golpeDuplo = new GolpeDuplo();
        this.habilidade = golpeDuplo;
    }

    @Override
    public int consumirMultiplicadorDano() {
        return golpeDuplo.consumirMultiplicador();
    }
}

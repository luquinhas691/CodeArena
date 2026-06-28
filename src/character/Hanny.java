package character;

public class Hanny extends Player {

    public Hanny() {
        super("Hanny", 120, 25);
        this.habilidade = new PulsoVital(this);
    }
}

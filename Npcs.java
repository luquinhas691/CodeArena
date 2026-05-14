public class Npcs {
    public static Player pete;
    public static Player hanny;
    public static Enemy homemPedra;
    public static Enemy homemMorcego;
    public static Enemy sereia;
    public static Enemy goblin;

    public static void inicializarNpcs() {
        pete = new Player("Pete", 100, 35);
        hanny = new Player("Hanny", 120, 25);
        homemPedra = new Enemy("Homem-Pedra", 5000, 0); //nao foi feito pra morrer
        homemMorcego = new Enemy("Homem-Morcego", 100, 15);
        sereia = new Enemy("Sereia", 110, 25);
        goblin = new Enemy("Goblin", 140, 30);
    }
}

package character;

public class Npcs {
    public static Enemy homemPedra() {
        return new Enemy("Homem-Pedra", 5000, 0);
    }
    public static Enemy homemMorcego() {
        return new Enemy("Homem-Morcego", 100, 15);
    }
    public static Enemy sereia() {
        return new Enemy("Sereia", 110, 25);
    }
    public static Enemy goblin() {
        return new Enemy("Goblin", 140, 30);
    }
}

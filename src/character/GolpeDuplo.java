package character;

public class GolpeDuplo implements Habilidade {

    private boolean disponivel = true;
    private boolean ativa      = false;

    @Override public String getNome()         { return "Golpe Duplo"; }
    @Override public String getDescricao()    { return "Concentra toda a forca — o proximo ataque causa o dobro de dano!"; }
    @Override public boolean estaDisponivel() { return disponivel; }

    @Override
    public void ativar() {
        if (!disponivel) { System.out.println("[Habilidade ja utilizada nesta fase]"); return; }
        disponivel = false;
        ativa      = true;
        System.out.println("\n HABILIDADE ATIVA — " + getNome() + "!");
        System.out.println("   " + getDescricao());
    }

    @Override
    public void resetar() {
        disponivel = true;
        ativa      = false;
    }

    public int consumirMultiplicador() {
        if (ativa) { ativa = false; return 2; }
        return 1;
    }
}

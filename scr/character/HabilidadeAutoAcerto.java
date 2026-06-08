package character;

/**
 * Habilidade de Hanny: Dedução Lógica.
 *
 * Permite que UMA questão seja acertada automaticamente sem que o
 * usuário precise digitir a resposta. O BattleManager consulta
 * {@link #estaDisponivel()} antes de exibir a pergunta; se disponível,
 * chama {@link #ativar()} e trata o turno como acerto automático.
 *
 * Uso único: a habilidade expira após o primeiro acionamento.
 */
public class HabilidadeAutoAcerto implements Habilidade {

    private boolean disponivel = true;

    @Override
    public String getNome() {
        return "Dedução Lógica";
    }

    @Override
    public String getDescricao() {
        return "Hanny analisa a questão instantaneamente e responde sozinha — "
                + "acerto automático garantido!";
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public void ativar() {
        if (!disponivel) {
            System.out.println("[Habilidade já utilizada]");
            return;
        }
        disponivel = false;
        System.out.println("\n✨ HABILIDADE ATIVA — " + getNome() + "!");
        System.out.println("   " + getDescricao());
        System.out.println("   → Hanny resolve esta questão automaticamente!\n");
    }
}

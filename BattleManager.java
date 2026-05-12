import java.util.List;
import java.util.Scanner;

/**
 * Gerencia o fluxo de batalha de cada fase.
 * Recebe o jogador, o inimigo e a lista de perguntas da fase,
 * instancia o Round correto e retorna o resultado.
 *
 * BattleManager entra em ação a partir da Fase 2 (quando há combate real).
 * A Fase 1 é tratada no Main pois é tutorial/narrativa.
 */
public class BattleManager {

    private Player jogador;
    private LoaderQuestion loader;
    private ScoreSystem score;
    private Scanner scanner = new Scanner(System.in);

    public BattleManager(Player jogador, LoaderQuestion loader, ScoreSystem score) {
        this.jogador = jogador;
        this.loader  = loader;
        this.score   = score;
    }

    // ─── FASE 1 — Homem de Pedra (sem dano, tutorial) ──────────────────────
    public boolean Fase1() {
        Enemy.HomemDePedra homem = new Enemy.HomemDePedra();
        homem.darBoasVindas();

        // 5 perguntas fáceis/médias
        List<Question> perguntas = loader.getPorDificuldade(Question.Dificuldade.FACIL, 5);
        Round round = new Round(perguntas, jogador, null, false, 0);
        Round.Resultado resultado = round.executar();
        score.registrarRodada(resultado);

        if (resultado.todasCertas) {
            System.out.println("\nHomem de Pedra: Impressionante! Você acertou tudo.");
            System.out.println("Uma sala secreta se abre. Dentro: uma Poção de Vida e 10 moedas!");
            jogador.curar(30);
            jogador.ganharMoedas(10);
        }

        System.out.println("\nHomem de Pedra: Tome esta adaga. Você vai precisar.");
        jogador.equiparAdaga();

        return resultado.jogadorSobreviveu;
    }

    // ─── FASE 2 — Homem Morcego (médio, causa dano ao errar) ───────────────
    public boolean Fase2() {
        Enemy.HomemMorcego morcego = new Enemy.HomemMorcego();
        System.out.println("\n[FASE 2] Um barulho vem do teto. O Homem Morcego aparece!");
        System.out.println(morcego.getDescricao());

        // 5 perguntas médias
        List<Question> perguntas = loader.getPorDificuldade(Question.Dificuldade.MEDIO, 5);
        int danoAoErrar = morcego.getAtaque();

        Round round = new Round(perguntas, jogador, morcego, true, danoAoErrar);
        Round.Resultado resultado = round.executar();
        score.registrarRodada(resultado);

        if (!resultado.jogadorSobreviveu) return false;

        if (!morcego.estaVivo() || resultado.todasCertas) {
            System.out.println("\nO Homem Morcego cai! A pedra no caminho se move...");
            System.out.println("Uma slime emerge — ataque-a!");
            // Combate simples com a slime
            combateSlime();
            System.out.println("A slime explodiu e dropou um ESCUDO!");
            jogador.equiparEscudo();
        }

        return jogador.estaVivo();
    }

    // ─── FASE 3 — Sereia (difícil, ataca 3x antes, escudo é útil) ──────────
    public boolean Fase3() {
        Enemy.Sereia sereia = new Enemy.Sereia();
        System.out.println("\n[FASE 3] Uma sala alagada. Plataformas flutuam na água escura.");
        System.out.println(sereia.getDescricao());

        // Sereia ataca 3x antes de começar
        sereia.atacarInicialmente(jogador);
        if (!jogador.estaVivo()) return false;

        // 5 perguntas médias/difíceis
        List<Question> perguntas = loader.getPorDificuldade(Question.Dificuldade.MEDIO, 5);
        int danoAoErrar = sereia.getAtaque();

        Round round = new Round(perguntas, jogador, sereia, true, danoAoErrar);
        Round.Resultado resultado = round.executar();
        score.registrarRodada(resultado);

        if (!resultado.jogadorSobreviveu) return false;

        System.out.println("\nA Sereia afunda! Uma ponte surge sobre as águas...");
        return true;
    }

    // ─── FASE 4 — Goblin Rei (chefe final, perguntas difíceis) ─────────────
    public boolean Fase4() {
        Enemy.GoblinRei goblin = new Enemy.GoblinRei();
        System.out.println("\n[FASE 4 — FINAL] Lava por todos os lados. Seu amigo está no braço do Goblin Rei!");
        System.out.println(goblin.getDescricao());
        System.out.println("\nOs koopas atacam de todos os lados. Responda certo para sobreviver!\n");

        // 5 perguntas difíceis
        List<Question> perguntas = loader.getPorDificuldade(Question.Dificuldade.DIFICIL, 5);
        int danoAoErrar = goblin.getAtaque();

        Round round = new Round(perguntas, jogador, goblin, true, danoAoErrar);
        Round.Resultado resultado = round.executar();
        score.registrarRodada(resultado);

        return resultado.jogadorSobreviveu;
    }

    // ─── Combate simples com a slime (sem perguntas) ────────────────────────
    private void combateSlime() {
        int vidaSlime = 20;
        System.out.println("\nUma slime aparece! (Vida: " + vidaSlime + ")");
        System.out.println("Pressione ENTER para atacar...");
        while (vidaSlime > 0 && jogador.estaVivo()) {
            scanner.nextLine();
            vidaSlime -= jogador.getAtaque();
            System.out.println("Você atacou a slime! Vida dela: " + Math.max(0, vidaSlime));
            if (vidaSlime > 0) {
                jogador.receberDanoComEscudo(5);
                System.out.println("A slime te atacou. " + jogador.barraDeVida());
            }
        }
    }
}

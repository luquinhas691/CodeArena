import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Validador {
    private QuestionLoader loader;
    private Scanner scanner;
    private int acertos;
    private int erros;

    // Listas para garantir que as perguntas não se repitam
    private List<Question> poolFaceis;
    private List<Question> poolMedias;
    private List<Question> poolDificeis;
    private List<Question> poolMuitoDificeis;

    // Índices para rastrear a posição atual em cada lista
    private int idxF, idxM, idxD, idxMD;

    public Validador(QuestionLoader loader) {
        this.loader = loader;
        this.scanner = new Scanner(System.in);
        this.acertos = 0;
        this.erros = 0;
        inicializarPools();
    }

    /**
     * Prepara as listas aleatórias no início do jogo.
     */
    public void inicializarPools() {
        // Criamos cópias para não afetar os dados originais do loader
        this.poolFaceis = new ArrayList<>(loader.getFaceis());
        this.poolMedias = new ArrayList<>(loader.getMedias());
        this.poolDificeis = new ArrayList<>(loader.getDificeis());
        this.poolMuitoDificeis = new ArrayList<>(loader.getMuitoDificeis());

        // Embaralhamento (O sorteio acontece aqui, uma única vez por pool)
        Collections.shuffle(poolFaceis);
        Collections.shuffle(poolMedias);
        Collections.shuffle(poolDificeis);
        Collections.shuffle(poolMuitoDificeis);

        // Resetamos os ponteiros para o início
        idxF = 0;
        idxM = 0;
        idxD = 0;
        idxMD = 0;
    }

    // --- MÉTODOS DE RODADA ---

    public boolean rodarParte1() {
        if (idxF < poolFaceis.size()) {
            return executarQuestao(poolFaceis.get(idxF++));
        }
        System.err.println("Erro: Banco de questões fáceis esgotado.");
        return false;
    }

    public boolean rodarParte2() {
        if (idxM < poolMedias.size()) {
            return executarQuestao(poolMedias.get(idxM++));
        }
        System.err.println("Erro: Banco de questões médias esgotado.");
        return false;
    }

    public boolean rodarParte3() {
        if (idxD < poolDificeis.size()) {
            return executarQuestao(poolDificeis.get(idxD++));
        }
        System.err.println("Erro: Banco de questões difíceis esgotado.");
        return false;
    }

    public boolean rodarParte4() {
        if (idxMD < poolMuitoDificeis.size()) {
            return executarQuestao(poolMuitoDificeis.get(idxMD++));
        }
        System.err.println("Erro: Banco de questões muito difíceis esgotado.");
        return false;
    }

    /**
     * Lógica central de exibição e validação.
     */
    private boolean executarQuestao(Question q) {
        q.mostrarEnunciado();
        q.mostrarAlternativas();
        
        System.out.print("> ");
        String respostaUser = scanner.nextLine();

        if (q.validarResposta(respostaUser)) {
            this.acertos++;
            return true;
        } else {
            this.erros++;
            System.out.println("Tente de Novo");
            return false;
        }
    }

    // --- GETTERS E UTILITÁRIOS ---

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }

    public void resetarPontuacao() {
        this.acertos = 0;
        this.erros = 0;
    }
}
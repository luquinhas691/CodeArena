package question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

// Implementação das questões com limite de tempo
public class TimedQuestionImpl implements TimedQuestion {

    private final Question question;
    private final int tempoLimite; // segundos

    public TimedQuestionImpl(Question question, int tempoLimite) {
        this.question    = question;
        this.tempoLimite = tempoLimite;
    }

    @Override
    public int getTempoLimite() {
        return tempoLimite;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public void mostrarEnunciadoComTempo() {
        System.out.println("\n⏳ [QUESTÃO CRONOMETRADA — " + tempoLimite + " SEGUNDOS]");
        System.out.println(question.getEnunciado());
    }

    @Override
    public void mostrarAlternativas() {
        question.mostrarAlternativas();
    }

    @Override
    public boolean validarResposta(String respostaUsuario) {
        return question.validarResposta(respostaUsuario);
    }

    /**
     * Lê a resposta do usuário em uma thread separada.
     * Se o tempo esgotar, interrompe a leitura e retorna null.
     */
    @Override
    public String lerRespostaComTempo() {
        AtomicReference<String> resultado = new AtomicReference<>(null);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Thread leitor = new Thread(() -> {
            try {
                // Aguarda até a linha ficar disponível ou a thread ser interrompida
                while (!Thread.currentThread().isInterrupted() && !br.ready()) {
                    Thread.sleep(50);
                }
                if (!Thread.currentThread().isInterrupted()) {
                    resultado.set(br.readLine());
                }
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.print("> ");
        leitor.start();

        // Contador regressivo visível
        for (int seg = tempoLimite; seg > 0; seg--) {
            if (!leitor.isAlive()) break; // usuário já respondeu
            System.out.print("\r> [" + seg + "s restantes] ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        leitor.interrupt();
        try { leitor.join(200); } catch (InterruptedException ignored) {}

        if (resultado.get() == null) {
            System.out.println("\n⌛ Tempo esgotado!");
        } else {
            System.out.println(); // quebra de linha após contador
        }

        return resultado.get(); // null = tempo esgotou
    }
}

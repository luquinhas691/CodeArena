import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionLoader {
    private List<Question> questoes = new ArrayList<>(); // onde ficam as questoes
    private int pontuacaoTotal = 0;
    private Scanner scanner;

    public QuestionLoader(String caminhoArquivo) {
        scanner = new Scanner(System.in);
        carregarDados(caminhoArquivo);
    }

    private void carregarDados(String caminho) {
        try (Scanner leitor = new Scanner(new File(caminho), "UTF-8")) {
            while (leitor.hasNextLine()) {
                String enunciado = leitor.nextLine().trim();
                if (enunciado.isEmpty()) continue;

                String linhaAlts = leitor.hasNextLine() ? leitor.nextLine() : "";
                String resposta = leitor.hasNextLine() ? leitor.nextLine().trim() : "";
                String scoreStr = leitor.hasNextLine() ? leitor.nextLine().trim() : "1";

                String[] alternativas = linhaAlts.split("\\|");
                int score = 1;
                try {
                    score = Integer.parseInt(scoreStr);
                } catch (NumberFormatException e) {}

                questoes.add(new Question(enunciado, alternativas, resposta, score));
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERRO: Arquivo nao encontrado: " + caminho);
            System.exit(1);
        }
    } // Lê o caminho e carrega as questoes


    // Método para responder a questão, recebe o número da questão
    public void responderQuestao(int numero) {
        Question q = getQuestaoPorNumero(numero);
        if (q == null) {
            System.out.println("Questao " + numero + " nao existe.");
            return;
        }

        q.mostrarEnunciado();
        q.mostrarAlternativas();

        System.out.print("Insira sua resposta: ");
        String respostaUsuario = scanner.nextLine().trim();

        boolean acertou = respostaUsuario.equalsIgnoreCase(q.getResposta().trim());
        
        // Valida a questao
        if (acertou) {
            System.out.println("Certa resposta");
            pontuacaoTotal += q.getScore();
        } else {
            System.out.println("Jumento, a resposta e " + q.getResposta());
        }
    }

    private Question getQuestaoPorNumero(int numero) {
        int idx = numero - 1;
        if (idx >= 0 && idx < questoes.size()) return questoes.get(idx);
        return null;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }
}
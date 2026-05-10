import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class LoaderQuestion {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Seusuario\\Desktop\\CodeArena-main\\CodeArena-main\\Arquivos\\portuguesequestion.txt"); // Aqui deve-se alterar o diretório selecionado
        
        Scanner scFile = null;
        Scanner keyboard = new Scanner(System.in);
        int totalScore = 0;

        try {
            scFile = new Scanner(file);

            while (scFile.hasNextLine()) {
                String enunciado = scFile.nextLine();

                // Pula linhas em branco entre as questões
                if (enunciado.trim().isEmpty()) {
                    continue; 
                }

                // Se não for vazia, lê as próximas 3 linhas do bloco
                String alternativas = scFile.nextLine();
                String respostaCorreta = scFile.nextLine();
                int pontosDaQuestao = Integer.parseInt(scFile.nextLine().trim());

                System.out.println("\n========================================");
                System.out.println("QUESTÃO: " + enunciado);
                System.out.println(alternativas);
                System.out.print("Sua escolha: ");

                String escolhaUsuario = keyboard.nextLine();

                if (escolhaUsuario.trim().equalsIgnoreCase(respostaCorreta.trim())) {
                    System.out.println("CORRETO. +" + pontosDaQuestao + " ponto(s).");
                    totalScore += pontosDaQuestao;
                } else {
                    System.out.println("INCORRETO. A resposta era: " + respostaCorreta);
                }
                
                System.out.println("Score acumulado: " + totalScore);
            }

            System.out.println("\n----------------------------------------");
            System.out.println("SESSÃO ENCERRADA, SENHOR.");
            System.out.println("PONTUAÇÃO FINAL: " + totalScore);
            System.out.println("----------------------------------------");

        } catch (IOException e) {
            System.out.println("ERRO AO ACESSAR O ARQUIVO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO DE PROCESSAMENTO: Verifique a estrutura do TXT.");
        } finally {
            if (scFile != null) scFile.close();
        }
    }
}

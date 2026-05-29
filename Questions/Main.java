public class Main {
    public static void main(String[] args) {
        // Coloque o arquivo "questoesPt01.txt" na mesma pasta que este código.
        // Se quiser caminho absoluto, troque a string.
        QuestionLoader loader = new QuestionLoader("questoesPt01.txt"); // aqui coloca teu caminho
        int a = 1;
        // Exemplo: responder a questão 11
         loader.responderQuestao(77);
       
        
        // teste que fiz com um while
         while (a < 5) {
            loader.responderQuestao(a);
            a++;
        }
       

        // Para responder outra questão depois, chame de novo:
        // loader.responderQuestao(5);
        // loader.responderQuestao(20);

        // Se quiser ver o score total no final, descomente a linha abaixo:
        // System.out.println("\nPontuação total (oculta até agora): " + loader.getPontuacaoTotal());
    }
}
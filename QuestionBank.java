import java.util.ArrayList;

public class QuestionBank{
    ArrayList<Question> eazy_questions = new ArrayList<>();
    
    public void firstLevel() { // Perguntas do nível 1

        eazy_questions.add(new Question(". Qual é o coletivo correto para um grupo de peixes?", 
            new String[]{"Alcateia", "Cardume", "Enxame", "Constelação"}, 1, 10
        ));

        eazy_questions.add(new Question("Assinale a alternativa em que a palavra está escrita corretamente:", 
        new String[]{"Eceção", "Analisar", "Meza", "Egetar" }, 1, 10));

    }

    
}
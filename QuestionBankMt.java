import java.util.ArrayList; // a lib usada

public class QuestionBankMt {
    ArrayList<Question> eazy_questions = new ArrayList<>(); // aqui declaramos um array list, que só recebe da classe Question

    public void firstLevel(){ // funcao q preenche essas perguntas

        eazy_questions.add(new Question("Enunciado aq", new String[]{"Alternativas", "Aqui"}, 0, 0)); 
        // os dois ultimos sao as respostas, pelo indice da lista, e o ultimo o score

    }
}

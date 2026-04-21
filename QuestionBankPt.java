import java.util.ArrayList;

public class QuestionBankPt{
    ArrayList<Question> eazy_questions = new ArrayList<>();
    ArrayList<Question> medium_questions = new ArrayList<>();
    ArrayList<Question> hard_questions = new ArrayList<>();
    
    public void firstLevel() { // Perguntas do nível 1

        eazy_questions.add(new Question(". Qual é o coletivo correto para um grupo de peixes?", 
            new String[]{"Alcateia", "Cardume", "Enxame", "Constelação"}, 1, 10
        ));

        eazy_questions.add(new Question("Assinale a alternativa em que a palavra está escrita corretamente:", 
        new String[]{"Eceção", "Analisar", "Meza", "Egetar" }, 1, 10));

        eazy_questions.add(new Question(". Na frase : O gato preto subiu no telhado, qual termo exerce a função de adjetivo?", 
        new String[]{"Gato", "Preto", "Subiu", "Telhado"}, 1, 10));

        eazy_questions.add(new Question("Qual é o plural correto da palavra Cidadão?", 
        new String[]{"Cidadões", "Cidadães", "Cidadãos", "Cidadeiro"}, 2, 10));

        eazy_questions.add(new Question("Qual é o antônimo da palavra Rápido?", 
        new String[]{"Veloz", "O sistema Siga", "Lento", "Forte"}, 2, 12));

        eazy_questions.add(new Question("Indique a frase onde a vírgula é usada corretamente para marcar um chamamento (vocativo):", 
        new String[]{"João, venha almoçar agora", "João venha, almoçar agora", "João venha almoçar, agora", "JOão venha almoçar agora"}, 1, 14));

        eazy_questions.add(new Question("Qual destas palavras pertence ao gênero feminino?", 
        new String[]{"O mapa", "A cal", "O pijama", "O sistema"}, 1, 8));

        eazy_questions.add(new Question("Identifique a frase que se encontra no tempo Futuro do Presente:", 
        new String[]{"Eu estudei ontem", "Eu estudo sempre", "Eu estudarei amanhã", "Eu estudei com MaRciel"}, 2, 8));

    }

    public void secondLevel(){ // Perguntas no nível 2
        
        medium_questions.add(new Question("Qual é a figura de linguagem presente na frase: O jardim olhava para mim com tristeza?", 
        new String[]{"Metáfora", "Personificação", "Hipérbole", "Eufemismo"}, 0, 12));

        medium_questions.add(new Question("Classifique o sujeito da oração: Anunciaram as novas medidas econômicas ontem.", 
        new String[]{"Sujeito simples", "Sujeito composto", "Sujeito indeterminado", "Sujeito oculto"}, 3, 14));

        medium_questions.add(new Question("Na frase : Os alunos saíram felizes, o termo \"felizes\" é um Predicativo do Sujeito.", 
        new String[]{"Verdadeiro", "Falso"}, 0, 12));

        medium_questions.add(new Question("A palavra \"autoestima\" deve ser escrita sem hífen, conforme as regras atuais do Acordo Ortográfico.", 
        new String[]{"Veradeiro", "Falso"}, 0, 12));

        medium_questions.add(new Question("Qual a classe gramatical da palavra destacada em: \"Ele correu *muito* durante o treino\"?", 
        new String[]{"Adjetivo", "Advérbio", "Substantivo", "Preposição"}, 1, 14));

        medium_questions.add(new Question("Identifique a alternativa que apresenta um Objeto Direto:", 
        new String[]{"Nós precismos de ajuda", "Eles gostam de música", "Os meninos leram o livro", "Eu confio em você"}, 2, 16));

        medium_questions.add(new Question("Assinale a frase em que o uso da vírgula serve para isolar um vocativo:", 
        new String[]{"Comprei pão, leite e café", "Maria, traga os relatórios", "Ontem a noite, saímos para jantar", "O menino, que é inteligente, passou"},
         3, 16));

        medium_questions.add(new Question("Na frase \"O caminho era longo, mas a vista era linda\", a conjunção em destaque indica:", 
        new String[]{"Adição", "Conclusão", "Oposição", "Explicação"}, 2, 12));
    }

    public void third_level(){ // Níveis dificeis
        
        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));

        hard_questions.add(new Question(null, 
            new String[]{}, 0, 0));


    }
    
}
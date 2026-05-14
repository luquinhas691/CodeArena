import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // leitor
        
        // Carregadores
        QuestionLoader loaderPt = new QuestionLoader("C:\\Users\\Bandim\\Desktop\\CodeCode\\CodeArena_Copy-main\\portuguesequestion.txt");
        QuestionLoader loaderMt = new QuestionLoader("C:\\Users\\Bandim\\Desktop\\CodeCode\\CodeArena_Copy-main\\math.txt");
        RoteiroLoader jogo = new RoteiroLoader("C:\\Users\\Bandim\\Desktop\\CodeCode\\CodeArena_Copy-main\\roteiro.txt");
       
        // Perguntas portugues
        Faceis nivelFacilPt = new Faceis(loaderPt);
        Medias nivelMedioPt = new Medias(loaderPt);
        Dificeis nivelDificilPt = new Dificeis(loaderPt);
        MuitoDificeis nivelMuitoDificilPt = new MuitoDificeis(loaderPt);   
        
        // Validadores
        Validador validadorPt = new Validador(loaderPt);
        Validador validadorMt = new Validador(loaderMt);

        // Jogadores e viloes
        Validador validador = null; // Inicializado para evitar erro de compilação
        Npcs.inicializarNpcs();

        // Perguntas matematica
        Faceis nivelFacilMt = new Faceis(loaderMt);
        Medias nivelMedioMt = new Medias(loaderMt);
        Dificeis nivelDificilMt = new Dificeis(loaderMt);
        MuitoDificeis nivelMuitoDificilMt = new MuitoDificeis(loaderMt);

        // Introdução
        jogo.introducao();
        System.out.println("Pete, Estudante de Matemática. Dizem que ele é bom com o tempo, como se passasse mais devagar...");
        System.out.println("Hanny, Estudante de Português. Os seus amigos sempre dizem que ela é uma boa dedutora, como se conseguisse eliminar possiveis erros...");
        
        Player jogadorSelecionado = null;

        // Loop de seleção corrigido
        while(true){
            System.out.print("> ");
            int escolha = teclado.nextInt();
            teclado.nextLine(); // Limpa o buffer para não pular as perguntas de texto depois

            if (escolha == 2) {
                jogadorSelecionado = Npcs.hanny;
                validador = validadorPt;
                jogo.introducaoHanny();
                break; 
            } else if (escolha == 1){
                jogadorSelecionado = Npcs.pete;
                validador = validadorMt;
                jogo.introducaoPete();
                break;   
            } else {
                System.out.println("Escolha inválida! Digite 1 ou 2.");
            }
        }

        // Jogo iniciado e configurado com o personagem escolhido
        BattleManager battleManager = new BattleManager(validador, jogo, jogadorSelecionado);
           
        // Fases
        jogo.fase1();
        battleManager.iniciarLuta1();
        jogo.fase2();
        battleManager.iniciarLuta2();
    } 
}
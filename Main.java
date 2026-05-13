import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    // Carragadores
    QuestionLoader loader = new QuestionLoader("C:\\Users\\10965761479\\Desktop\\CodeArena_Copy-main\\Questions\\portuguesequestion.txt");
    ScriptLoader sLoader = new ScriptLoader("C:\\Users\\10965761479\\Desktop\\CodeArena_Copy-main\\roteiro.txt");
    
    // Perguntas
    Faceis nivelFacil = new Faceis(loader);
    Medias nivelMedio = new Medias(loader);
    Dificeis nivelDificil = new Dificeis(loader);
    MuitoDificeis nivelMuitoDificil = new MuitoDificeis(loader);    

    // Fases
    GameManager game = new GameManager(sLoader);
    
    // PRintando roteiro
    game.exibirIntro();
    // Escolhendo personagem
    while (true) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Selecione: 1 Pete 2 Hanny");
        int escolha = sc.nextInt();
        if (escolha == 1) {
            game.escolherPersonagem("PETE");
            break;
        } else if (escolha == 2) {
            game.escolherPersonagem("HANNY");
            break;
        } else {
            System.out.println("Escolha inválida");
            
        }
    }

    nivelFacil.printarTodas();
    nivelDificil.printarTodas();
    game.iniciarFase1(false);} {   
    
    
    
    } 
    
}

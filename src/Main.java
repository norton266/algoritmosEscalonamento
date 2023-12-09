import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");

        ArrayList<Integer> entradasManuais = new ArrayList<>();
        int qtdProcessos = 0;

        System.out.println("Digite a quantidade de processos para execução:");
        qtdProcessos = input.nextInt();

        for(int i = 0; i < qtdProcessos; i++){
            Processos p = new Processos(i);
        }

        Escalonamentos e = new Escalonamentos();

        e.SJFNaoPreemptivo();





    }
}
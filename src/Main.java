import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");

        int qtdProcessos = 0;
        int tempoChegada = 0;
        int tempoExecucao = 0;
        int tempoRestante = 0;
        int prioridade = 0;
        int opcao = 999;
        int timeSlice = 0;

        System.out.println("Digite a quantidade de processos para execução:");
        qtdProcessos = input.nextInt();

        System.out.println("Os processos serão populados de forma randômica? \n" +
                "1- Não.\n" +
                "2- Sim.");
        int escolhaRandomica = input.nextInt();

        if(escolhaRandomica == 1){
            for(int i = 0; i < qtdProcessos; i++){
                System.out.println("Digite o tempo de chegada do processo ["+i+"]:");
                tempoChegada = input.nextInt();
                System.out.println("Digite o tempo de execução do processo ["+i+"]:");
                tempoExecucao = input.nextInt();
                System.out.println("Digite a prioridade do processo ["+i+"]:");
                prioridade = input.nextInt();

                Processos manuais = new Processos(i, tempoChegada, tempoExecucao, prioridade);
            }
        } else {
            for (int i = 0; i < qtdProcessos; i++) {
                Processos p = new Processos(i);
            }
        }

        while(opcao != 0) {
            Escalonamentos e = new Escalonamentos();
            System.out.println("1 - Algorítmo FCFS.");
            System.out.println("2 - Algorítmo SJF (Não preemptivo).");
            System.out.println("3 - Algorítmo SJF (Preemptivo).");
            System.out.println("4 - Algorítmo Prioridade (Não preemptivo).");
            System.out.println("5 - Algorítmo Prioridade (Preemptivo).");
            System.out.println("6 - Algorítmo RoundRobin.");
            System.out.println("7 - Imprimir lista de processos.");
            System.out.println("8 - Popular processos novamente.");
            System.out.println("0 - Sair.");

            opcao = input.nextInt();

            switch (opcao){
                case 1:
                    e.FCFS();
                    break;
                case 2:
                    e.SJFNaoPreemptivo();
                    break;
                case 3:
                    e.SJFPreemptivo();
                    break;
                case 4:
                    e.PrioridadeNaoPreemptivo();
                    break;
                case 5:
                    e.PrioridadePreemptivo();
                    break;
                case 6:
                    System.out.println("Digite o Time-Slice: ");
                    timeSlice = input.nextInt();

                    e.RoundRobin(timeSlice);
                    break;
                case 7:
                    e.info();
                    break;
                case 8:
                    Processos deleta = new Processos();
                    deleta.deletaArrayProcessos();
                    System.out.println("Digite a quantidade de processos para execução:");
                    qtdProcessos = input.nextInt();

                    System.out.println("Os processos serão populados de forma randômica? \n" +
                            "1- Não.\n" +
                            "2- Sim.");
                    escolhaRandomica = input.nextInt();

                    if(escolhaRandomica == 1){
                        for(int i = 0; i < qtdProcessos; i++){
                            System.out.println("Digite o tempo de chegada do processo ["+i+"]:");
                            tempoChegada = input.nextInt();
                            System.out.println("Digite o tempo de execução do processo ["+i+"]:");
                            tempoExecucao = input.nextInt();
                            System.out.println("Digite a prioridade do processo ["+i+"]:");
                            prioridade = input.nextInt();

                            Processos manuais = new Processos(i, tempoChegada, tempoExecucao, prioridade);
                        }
                    } else {
                        for (int i = 0; i < qtdProcessos; i++) {
                            Processos p = new Processos(i);
                        }
                    }

                case 0:
                    break;
            }
        }

    }
}
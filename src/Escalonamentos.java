import java.util.ArrayList;

public class Escalonamentos {

    ArrayList<Processos> arrayProcessosEscalonamento = new ArrayList<>();


    public Escalonamentos(){
        Processos p = new Processos();
        arrayProcessosEscalonamento = p.escalonamento();

        info();
    }

    public void info(){
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            System.out.println("Processo: " + arrayProcessosEscalonamento.get(i).getProcesso());
            System.out.println("Tempo  chegada: " + arrayProcessosEscalonamento.get(i).getTempoChegada());
            System.out.println("Tempo execução: " + arrayProcessosEscalonamento.get(i).getTempoExecucao());
            System.out.println("Tempo restante: " + arrayProcessosEscalonamento.get(i).getTempoRestante());
            System.out.println("Prioridade: " + arrayProcessosEscalonamento.get(i).getPrioridade());
            System.out.println(" ");
        }
    }
}

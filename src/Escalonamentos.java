import java.util.ArrayList;

public class Escalonamentos {

    static ArrayList<Processos> arrayProcessosEscalonamento = new ArrayList<>();


    public Escalonamentos(){
        Processos p = new Processos();
        arrayProcessosEscalonamento = p.escalonamento();

        info();
    }

    public void info(){
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            System.out.println("Processo: " + arrayProcessosEscalonamento.get(i).getProcesso());
            System.out.println("Tempo chegada: " + arrayProcessosEscalonamento.get(i).getTempoChegada());
            System.out.println("Tempo execução: " + arrayProcessosEscalonamento.get(i).getTempoExecucao());
            System.out.println("Tempo restante: " + arrayProcessosEscalonamento.get(i).getTempoRestante());
            System.out.println("Prioridade: " + arrayProcessosEscalonamento.get(i).getPrioridade());
            System.out.println(" ");
        }
    }

    public void FCFS(){
        int tempoTotal = 0;
        int tempoExecucao = 1;


        //calcula o tempo total de execução
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoTotal += arrayProcessosEscalonamento.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: "+ tempoTotal);


        //faz o FCFS sem levar em consideração o tempo de chegada
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            while(arrayProcessosEscalonamento.get(i).getTempoRestante() > 0){
                //remove 1 unidade de tempo restante
                arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);

                //imprime
                System.out.println("Tempo["+ tempoExecucao + "]: Processo["+arrayProcessosEscalonamento.get(i).getProcesso()+
                        "] restante "+ arrayProcessosEscalonamento.get(i).getTempoRestante());

                tempoExecucao++;
            }
        }
    }

    public void SJFNaoPreemptivo() {
        int tempoTotal = 0;
        int tempoExecucao = 1;


        for (int i = 0; i < arrayProcessosEscalonamento.size(); i++) {
            tempoTotal += arrayProcessosEscalonamento.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);


        //outra tentativa, criar o objeto com os atributos da classe Processos e fazer o processo desta forma
        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        while (primeiroProcesso.getTempoRestante() > 0) {
            //remove 1 unidade de tempo restante
            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            //imprime
            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);
        }

        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            Processos processoMaisCurto = new Processos();

            processoMaisCurto = (processoMaisCurto.procuraProcessoExecucao());

            while(processoMaisCurto.verificaTempoExecucao(processoMaisCurto, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;
            }

            while (processoMaisCurto.getTempoRestante() > 0) {
                processoMaisCurto.setTempoRestante(processoMaisCurto.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisCurto.getProcesso() +
                            "] restante " + processoMaisCurto.getTempoRestante());

                tempoExecucao++;
                processoMaisCurto.ajustaTempoRestante(processoMaisCurto);
            }

        }
    }

}

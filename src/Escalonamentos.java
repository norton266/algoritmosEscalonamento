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


        //cria o objeto, passa os parametros pra ele e faz a execução
        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        //verifica o tempo de chegada e o tempo de execução
        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        //executa
        while (primeiroProcesso.getTempoRestante() > 0) {
            //remove 1 unidade de tempo restante
            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            //imprime
            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);
        }

        //faz o mesmo processo de antes porém agora leva em consideração o processo mais curto
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

    public void SJFPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;


        for (int i = 0; i < arrayProcessosEscalonamento.size(); i++) {
            tempoTotal += arrayProcessosEscalonamento.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);


        //cria o objeto, passa os parametros pra ele e faz a execução
        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        //verifica o tempo de chegada e o tempo de execução
        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        //executa
        while (primeiroProcesso.getTempoRestante() > 0) {
            if(primeiroProcesso.getTempoRestante() == 999){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado");
                tempoExecucao++;
            }
            //remove 1 unidade de tempo restante
            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            //imprime
            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);

            //cria outro objeto para comparação de novos valores
            Processos proximoProcesso = primeiroProcesso;

            //procura um objeto que tenha um tempo de execução restante menor, e que seja maior que 0
            proximoProcesso = (proximoProcesso.procuraProcessoExecucaoRestante(tempoExecucao));

            //condição para verificar se ainda existem processos com tempo restante maiores que 0, caso não exista
            //vai pro else, que é um break para sair do loop
            if(proximoProcesso.verificaTempoExecucao(proximoProcesso, tempoExecucao) && proximoProcesso.getTempoRestante() != 0){
                primeiroProcesso = proximoProcesso;

                //remove 1 unidade de tempo restante
                primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

                //imprime
                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                        "] restante " + primeiroProcesso.getTempoRestante());

                tempoExecucao++;
                primeiroProcesso.ajustaTempoRestante(primeiroProcesso);

                if(primeiroProcesso.getTempoRestante() == 0){
                    primeiroProcesso = primeiroProcesso.procuraProcessoExecucaoRestante(tempoExecucao);
                }
            } else {
                break;
            }
        }
    }

    public void PrioridadeNaoPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;


        for (int i = 0; i < arrayProcessosEscalonamento.size(); i++) {
            tempoTotal += arrayProcessosEscalonamento.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: " + tempoTotal);

        //cria o objeto, passa os parametros pra ele e faz a execução
        Processos primeiroProcesso = new Processos();

        primeiroProcesso = (primeiroProcesso.procuraProcessoChegada());

        //verifica o tempo de chegada e o tempo de execução
        while(!primeiroProcesso.verificaTempoExecucao(primeiroProcesso, tempoExecucao)){
            System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
            tempoExecucao++;
        }

        //executa
        while (primeiroProcesso.getTempoRestante() > 0) {
            //remove 1 unidade de tempo restante
            primeiroProcesso.setTempoRestante(primeiroProcesso.getTempoRestante() - 1);

            //imprime
            System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + primeiroProcesso.getProcesso() +
                    "] restante " + primeiroProcesso.getTempoRestante());

            tempoExecucao++;
            primeiroProcesso.ajustaTempoRestante(primeiroProcesso);
        }

        //faz o mesmo processo de antes porém agora leva em consideração o processo com o menor índice de prioridade
        //(levando em consideração que quanto menor o índice maior a prioridade)
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            Processos processoMaisPrioritario = new Processos();

            processoMaisPrioritario = (processoMaisPrioritario.procuraProcessoPrioridade());

            while(processoMaisPrioritario.verificaTempoExecucao(processoMaisPrioritario, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;
            }

            while (processoMaisPrioritario.getTempoRestante() > 0) {
                processoMaisPrioritario.setTempoRestante(processoMaisPrioritario.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisPrioritario.getProcesso() +
                        "] restante " + processoMaisPrioritario.getTempoRestante());

                tempoExecucao++;
                processoMaisPrioritario.ajustaTempoRestante(processoMaisPrioritario);
            }

        }
    }

}

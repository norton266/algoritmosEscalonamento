import java.util.ArrayList;

public class Escalonamentos {

    static ArrayList<Processos> arrayProcessosEscalonamento = new ArrayList<>();


    public Escalonamentos(){
        Processos p = new Processos();
        arrayProcessosEscalonamento = p.escalonamento();
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
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


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

                //adiciona 1 tempo de espera para os processos que não estão no processador
                for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                    arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                }

                for(int x = 0; x < i; x++){
                    if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                    }
                }
            }
        }

        //calcula o tempo de espera total
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoEspera  += arrayProcessosEscalonamento.get(i).getTempoEspera();
        }

        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

    }

    public void SJFNaoPreemptivo() {
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;

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


            //diminui o tempo restante no array desta classe
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);
                }
            }


            //aumenta o tempo de espera dos outros processos
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    //adiciona 1 tempo de espera para os processos que não estão no processador
                    for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }

        }

        //faz o mesmo processo de antes porém agora leva em consideração o processo mais curto
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            Processos processoMaisCurto = new Processos();

            processoMaisCurto = (processoMaisCurto.procuraProcessoExecucao());

            while(processoMaisCurto.verificaTempoExecucao(processoMaisCurto, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;

                //aumenta em 1 o tempo de espera de todos processos
                for(int x = 0; x < arrayProcessosEscalonamento.size(); x++){
                    arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                }
            }

            while (processoMaisCurto.getTempoRestante() > 0) {
                processoMaisCurto.setTempoRestante(processoMaisCurto.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisCurto.getProcesso() +
                        "] restante " + processoMaisCurto.getTempoRestante());

                tempoExecucao++;
                processoMaisCurto.ajustaTempoRestante(processoMaisCurto);

                //diminui o tempo restante no array desta classe
                for(int x = 0; x <  arrayProcessosEscalonamento.size(); x++){
                    if(processoMaisCurto.getProcesso() == x){
                        arrayProcessosEscalonamento.get(x).setTempoRestante(arrayProcessosEscalonamento.get(x).getTempoRestante() - 1);
                    }
                }

                //aumenta o tempo de espera dos outros processos
                for(int k = 0; k <  arrayProcessosEscalonamento.size(); k++){
                    if(processoMaisCurto.getProcesso() == k){
                        //adiciona 1 tempo de espera para os processos que não estão no processador
                        for(int x = k + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                                arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }
            }

        }

        //calcula o tempo de espera total
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoEspera  += arrayProcessosEscalonamento.get(i).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }
    }

    public void SJFPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


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

            //TODO: COPIAR NAS OUTRAS CLASSES
            //diminui o tempo restante no array desta classe
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);
                }
            }

            //TODO: COPIAR NAS OUTRAS CLASSES
            //aumenta o tempo de espera dos outros processos
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    //adiciona 1 tempo de espera para os processos que não estão no processador
                    for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }

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

                //TODO: COPIAR NAS OUTRAS CLASSES
                //diminui o tempo restante no array desta classe
                for(int x = 0; x <  arrayProcessosEscalonamento.size(); x++){
                    if(primeiroProcesso.getProcesso() == x){
                        arrayProcessosEscalonamento.get(x).setTempoRestante(arrayProcessosEscalonamento.get(x).getTempoRestante() - 1);
                    }
                }

                //TODO: COPIAR NAS OUTRAS CLASSES
                //aumenta o tempo de espera dos outros processos
                for(int k = 0; k <  arrayProcessosEscalonamento.size(); k++){
                    if(primeiroProcesso.getProcesso() == k){
                        //adiciona 1 tempo de espera para os processos que não estão no processador
                        for(int x = k + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                                arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }

                if(primeiroProcesso.getTempoRestante() == 0){
                    primeiroProcesso = primeiroProcesso.procuraProcessoExecucaoRestante(tempoExecucao);
                }
            } else {
                //calcula o tempo de espera total
                for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
                    tempoEspera  += arrayProcessosEscalonamento.get(i).getTempoEspera();
                }

                tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

                System.out.println("Tempo de espera total: "+ tempoEspera);
                System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

                break;
            }
        }

        //TODO: COPIAR NAS OUTRAS CLASSES
        //calcula o tempo de espera total
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoEspera  += arrayProcessosEscalonamento.get(i).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);
        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }
    }

    public void PrioridadeNaoPreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;


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

            //diminui o tempo restante no array desta classe
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);
                }
            }

            //aumenta o tempo de espera dos outros processos
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    //adiciona 1 tempo de espera para os processos que não estão no processador
                    for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }
        }

        //faz o mesmo processo de antes porém agora leva em consideração o processo com o menor índice de prioridade
        //(levando em consideração que quanto menor o índice maior a prioridade)
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            Processos processoMaisPrioritario = new Processos();

            processoMaisPrioritario = (processoMaisPrioritario.procuraProcessoPrioridade(tempoExecucao));

            while(processoMaisPrioritario.verificaTempoExecucao(processoMaisPrioritario, tempoExecucao) == false){
                System.out.println("Tempo[" + tempoExecucao + "] Nenhum processo alocado" );
                tempoExecucao++;

                //TODO: COPIAR PARA CLASSES QUE LEVAM  TEMPO DE CHEGADA  EM CONSIDERAÇÃO
                for(int k = 0; i < arrayProcessosEscalonamento.size(); k++){
                    arrayProcessosEscalonamento.get(k).setTempoEspera(arrayProcessosEscalonamento.get(k).getTempoEspera() + 1);
                }
            }

            while (processoMaisPrioritario.getTempoRestante() > 0) {
                processoMaisPrioritario.setTempoRestante(processoMaisPrioritario.getTempoRestante() - 1);

                System.out.println("Tempo[" + tempoExecucao + "]: Processo[" + processoMaisPrioritario.getProcesso() +
                        "] restante " + processoMaisPrioritario.getTempoRestante());

                tempoExecucao++;
                processoMaisPrioritario.ajustaTempoRestante(processoMaisPrioritario);

                //diminui o tempo restante no array desta classe
                for(int x = 0; x <  arrayProcessosEscalonamento.size(); x++){
                    if(processoMaisPrioritario.getProcesso() == x){
                        arrayProcessosEscalonamento.get(x).setTempoRestante(arrayProcessosEscalonamento.get(x).getTempoRestante() - 1);
                    }
                }

                //aumenta o tempo de espera dos outros processos
                for(int k = 0; k <  arrayProcessosEscalonamento.size(); k++){
                    if(processoMaisPrioritario.getProcesso() == k){
                        //adiciona 1 tempo de espera para os processos que não estão no processador
                        for(int x = k + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                                arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }
            }



        }
        //calcula o tempo de espera total
        for(int k = 0; k < arrayProcessosEscalonamento.size(); k++){
            tempoEspera  += arrayProcessosEscalonamento.get(k).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);
        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }
    }

    public void PrioridadePreemptivo(){
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoEspera = 0;
        double tempoEsperaMedio =  0;



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

            //TODO: COPIAR PARA CLASSES QUE LEVAM  TEMPO DE CHEGADA  EM CONSIDERAÇÃO
            for(int k = 0; k < arrayProcessosEscalonamento.size(); k++){
                arrayProcessosEscalonamento.get(k).setTempoEspera(arrayProcessosEscalonamento.get(k).getTempoEspera() + 1);
            }
        }

        //executa
        while (primeiroProcesso.getTempoRestante() > 0) {
            if(primeiroProcesso.getPrioridade() == 999){
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

            //diminui o tempo restante no array desta classe
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);
                }
            }

            //TODO: COPIAR NAS OUTRAS CLASSES
            //aumenta o tempo de espera dos outros processos
            for(int i = 0; i <  arrayProcessosEscalonamento.size(); i++){
                if(primeiroProcesso.getProcesso() == i){
                    //adiciona 1 tempo de espera para os processos que não estão no processador
                    for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                    }

                    for(int x = 0; x < i; x++){
                        if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                        }
                    }
                }
            }

            //cria outro objeto para comparação de novos valores
            Processos proximoProcesso = primeiroProcesso;

            //procura um objeto que tenha um tempo de execução restante menor, e que seja maior que 0
            proximoProcesso = (proximoProcesso.procuraProcessoPrioridade(tempoExecucao));

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

                //TODO: COPIAR NAS OUTRAS CLASSES
                //diminui o tempo restante no array desta classe
                for(int x = 0; x <  arrayProcessosEscalonamento.size(); x++){
                    if(primeiroProcesso.getProcesso() == x){
                        arrayProcessosEscalonamento.get(x).setTempoRestante(arrayProcessosEscalonamento.get(x).getTempoRestante() - 1);
                    }
                }

                //TODO: COPIAR NAS OUTRAS CLASSES
                //aumenta o tempo de espera dos outros processos
                for(int k = 0; k <  arrayProcessosEscalonamento.size(); k++){
                    if(primeiroProcesso.getProcesso() == k){
                        //adiciona 1 tempo de espera para os processos que não estão no processador
                        for(int x = k + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                            arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() +1);
                        }

                        for(int x = 0; x < k; x++){
                            if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                                arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                            }
                        }
                    }
                }

                if(primeiroProcesso.getTempoRestante() == 0){
                    primeiroProcesso = primeiroProcesso.procuraProcessoPrioridade(tempoExecucao);
                }
            } else {
                break;
            }
        }

        //calcula o tempo de espera total
        for(int k = 0; k < arrayProcessosEscalonamento.size(); k++){
            tempoEspera  += arrayProcessosEscalonamento.get(k).getTempoEspera();
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);

        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }
    }
    public void removeProcessos(){
        Processos p = new Processos();
        p.deletaArrayProcessos();
    }

    public void RoundRobin(int timeSlice) {
        int tempoTotal = 0;
        int tempoExecucao = 1;
        int tempoExecutando = 0;
        int tempoEspera = 0;
        double tempoEsperaMedio = 0;

        Processos pivo = new Processos();


        //calcula o tempo total de execução
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoTotal += arrayProcessosEscalonamento.get(i).getTempoExecucao();
        }
        System.out.println("Tempo total de execução: "+ tempoTotal);


        //faz o RoundRobin sem levar em consideração o tempo de chegada
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            while(arrayProcessosEscalonamento.get(i).getTempoRestante() > 0){
                //remove 1 unidade de tempo restante
                arrayProcessosEscalonamento.get(i).setTempoRestante(arrayProcessosEscalonamento.get(i).getTempoRestante() - 1);

                tempoExecutando++;

                //imprime
                System.out.println("Tempo["+ tempoExecucao + "]: Processo["+arrayProcessosEscalonamento.get(i).getProcesso()+
                        "] restante "+ arrayProcessosEscalonamento.get(i).getTempoRestante());

                tempoExecucao++;

                //adiciona 1 tempo de espera para os processos que não estão no processador
                for(int x = i + 1; x <= arrayProcessosEscalonamento.size() - 1; x++){
                    if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                    }
                }

                for(int x = 0; x < i; x++){
                    if(arrayProcessosEscalonamento.get(x).getTempoRestante() > 0) {
                        arrayProcessosEscalonamento.get(x).setTempoEspera(arrayProcessosEscalonamento.get(x).getTempoEspera() + 1);
                    }
                }


                //adiciona o processo que está executando no final da fila
                if(tempoExecutando == timeSlice){
                    pivo = arrayProcessosEscalonamento.remove(i);
                    arrayProcessosEscalonamento.add(pivo);

                    tempoExecutando = 0;


                }
                if(arrayProcessosEscalonamento.get(i).getTempoRestante()  == 0){
                    pivo = arrayProcessosEscalonamento.remove(i);
                    arrayProcessosEscalonamento.add(pivo);
                }
            }
        }
        //calcula o tempo de espera total
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            tempoEspera  += arrayProcessosEscalonamento.get(i).getTempoEspera();
        }

        //reinicia o tempo restante dos processos para que possa ser feito novo algoritmo de escalonamento
        for(int i = 0; i < arrayProcessosEscalonamento.size(); i++){
            arrayProcessosEscalonamento.get(i).reiniciaTempoRestante(arrayProcessosEscalonamento.get(i));
        }

        tempoEsperaMedio = tempoEspera/(arrayProcessosEscalonamento.size());

        System.out.println("Tempo de espera total: "+ tempoEspera);
        System.out.println("Tempo de espera médio: "+  tempoEsperaMedio);
    }

}

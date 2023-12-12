import java.util.ArrayList;
import java.util.Random;

public class Processos {
    private int processo;
    private int tempoChegada;
    private int tempoExecucao;
    private int tempoRestante;
    private int prioridade;
    public static ArrayList<Processos> arrayProcessos = new ArrayList();

    //construtor para os objetos criados manualmente
    public Processos(int processo, int tempoChegada, int tempoExecucao, int tempoRestante, int prioridade){
        this.processo = processo;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoRestante;
        this.prioridade = prioridade;

        arrayProcessos.add(this);
    }


    //construtor para os objetos criados de forma aleatória
    public Processos(int processo){
        Random rand = new Random();
        this.processo = processo;
        this.tempoChegada = rand.nextInt(10) + 1;
        this.tempoExecucao = rand.nextInt(10) + 1;
        this.tempoRestante = tempoExecucao;
        this.prioridade = rand.nextInt(10) + 1;

        arrayProcessos.add(this);
    }

    public Processos (){

    }


    //método que popula o array de processos da classe Escalonamentos
    public ArrayList<Processos> escalonamento(){

        ArrayList<Processos> processo = new ArrayList<>();

        for(int i = 0; i < arrayProcessos.size(); i++){
            Processos p = new Processos();
            p.setProcesso(arrayProcessos.get(i).getProcesso());
            p.setPrioridade(arrayProcessos.get(i).getPrioridade());
            p.setTempoChegada(arrayProcessos.get(i).getTempoChegada());
            p.setTempoExecucao(arrayProcessos.get(i).getTempoExecucao());
            p.setTempoRestante(arrayProcessos.get(i).getTempoRestante());

            processo.add(p);
        }

        return processo;
    }


    //método para testes
    public void info(){
        for(int i = 0; i < arrayProcessos.size(); i++){
            System.out.println("Processo: " + arrayProcessos.get(i).getProcesso());
            System.out.println("Tempo chegada: " + arrayProcessos.get(i).getTempoChegada());
            System.out.println("Tempo execução: " + arrayProcessos.get(i).getTempoExecucao());
            System.out.println("Tempo restante: " + arrayProcessos.get(i).getTempoRestante());
            System.out.println("Prioridade: " + arrayProcessos.get(i).getPrioridade());
            System.out.println(" ");
        }

    }

    //procura o processo que irá chegar primeiro ao processador
    public Processos procuraProcessoChegada() {
        Processos menorTempoChegada = new Processos();
        menorTempoChegada.setTempoChegada(999);

        for (int i = 0; i < arrayProcessos.size(); i++) {
            if(arrayProcessos.get(i).getTempoChegada() < menorTempoChegada.getTempoChegada()){
                if(arrayProcessos.get(i).getTempoRestante() > 0) {
                    menorTempoChegada = arrayProcessos.get(i);
                }
            }
        }

        return menorTempoChegada;
    }

    public void ajustaTempoRestante(Processos processo){
        for(int i = 0;i < arrayProcessos.size(); i++){
            if(arrayProcessos.get(i).equals(processo)){
                arrayProcessos.get(i).setTempoRestante(processo.getTempoRestante());
            }
        }
    }

    public Processos procuraProcessoExecucao(){
        Processos menorTempoExecucao = new Processos();
        menorTempoExecucao.setTempoExecucao(999);
        for (int i = 0; i < arrayProcessos.size(); i++) {
            if(arrayProcessos.get(i).getTempoExecucao() < menorTempoExecucao.getTempoExecucao()){
                if(arrayProcessos.get(i).getTempoRestante() > 0) {
                    menorTempoExecucao = arrayProcessos.get(i);
                }
            }
        }

        return menorTempoExecucao;
    }

    public Processos procuraProcessoExecucaoRestante(int tempoExecucao){
        Processos menorTempoRestante = new Processos();
        menorTempoRestante.setTempoRestante(999);
        for (int i = 0; i < arrayProcessos.size(); i++) {
            if(arrayProcessos.get(i).getTempoRestante() < menorTempoRestante.getTempoRestante()){
                if(arrayProcessos.get(i).getTempoChegada() <= tempoExecucao) {
                    if(arrayProcessos.get(i).getTempoRestante() > 0) {
                        menorTempoRestante = arrayProcessos.get(i);
                    }
                }
            }
        }
        if(menorTempoRestante.getTempoRestante() == 999){
            menorTempoRestante.setTempoRestante(0);
            return menorTempoRestante;

        } else {
            return menorTempoRestante;
        }
    }

    public boolean verificaTempoExecucao(Processos processos, int tempoExecucaoProcesso){
        if(processos.getTempoChegada() <= tempoExecucaoProcesso){
            return true;
        } else {
            return false;
        }
    }

    public Processos procuraProcessoPrioridade(){
        Processos maiorPrioridade = new Processos();
        maiorPrioridade.setPrioridade(999);
        for (int i = 0; i < arrayProcessos.size(); i++) {
            if(arrayProcessos.get(i).getPrioridade() < maiorPrioridade.getPrioridade()){
                if(arrayProcessos.get(i).getTempoRestante() > 0) {
                    maiorPrioridade = arrayProcessos.get(i);
                }
            }
        }

        return maiorPrioridade;
    }


    //getters and setters
    public int getProcesso() {
        return processo;
    }

    public void setProcesso(int processo) {
        this.processo = processo;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }



}

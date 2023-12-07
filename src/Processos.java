import java.util.ArrayList;
import java.util.Random;

public class Processos {
    int processo;
    int tempoChegada;
    int tempoExecucao;
    int tempoRestante;
    int prioridade;
    public static ArrayList<Processos> arrayProcessos = new ArrayList();

    public Processos(int processo, int tempoChegada, int tempoExecucao, int tempoRestante, int prioridade){
        this.processo = processo;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoRestante;
        this.prioridade = prioridade;

        arrayProcessos.add(this);
    }

    public Processos(int processo){
        Random rand = new Random();
        this.processo = processo;
        this.tempoChegada = rand.nextInt(10) + 1;
        this.tempoExecucao = rand.nextInt(10) + 1;
        this.tempoRestante = rand.nextInt(10) + 1;
        this.prioridade = rand.nextInt(10) + 1;

        arrayProcessos.add(this);
    }

    public Processos (){

    }

    public ArrayList<Processos> escalonamento(){

        ArrayList<Processos> processo = new ArrayList<>();

        for(int i = 0; i < arrayProcessos.size(); i++){
            Processos p = new Processos();
            p.setProcesso(arrayProcessos.get(i).getProcesso());
            p.setPrioridade(arrayProcessos.get(i).getPrioridade());
            p.setTempoChegada(arrayProcessos.get(i).getPrioridade());
            p.setTempoExecucao(arrayProcessos.get(i).getTempoExecucao());
            p.setTempoRestante(arrayProcessos.get(i).getTempoRestante());

            processo.add(p);
        }

        return processo;
    }

    public void info(){
        for(int i = 0; i < arrayProcessos.size(); i++){
            System.out.println("Processo: " + arrayProcessos.get(i).getProcesso());
            System.out.println("Tempo  chegada: " + arrayProcessos.get(i).getTempoChegada());
            System.out.println("Tempo execução: " + arrayProcessos.get(i).getTempoExecucao());
            System.out.println("Tempo restante: " + arrayProcessos.get(i).getTempoRestante());
            System.out.println("Prioridade: " + arrayProcessos.get(i).getPrioridade());
            System.out.println(" ");
        }

    }

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

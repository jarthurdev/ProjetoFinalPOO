package locadora.model;

import java.time.LocalDate;
import java.time.Period;

public class Locacao {
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private int valorLocacao;
    private int id;
    
    protected Veiculo veiculo;
    protected Cliente cliente;

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }
    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public int getValorLocacao() {
        return valorLocacao;
    }
    public void setValorLocacao(int valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public Locacao(Veiculo veiculo, Cliente cliente, Locacao locacao) {
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    public int returnPeriodo(){
        return Period.between(dataLocacao, dataDevolucao).getDays();
    }
    
    public int calcularCustoLocacao(){
        return this.veiculo.calcularCustoLocacao() * returnPeriodo();
    }
    
}
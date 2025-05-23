package locadora.model;

import java.time.LocalDate;
import java.time.Period;

public class Locacao {
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private double valorLocacao;
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
    public double getValorLocacao() {
        return valorLocacao;
    }
    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Locacao(Veiculo veiculo, Cliente cliente, LocalDate dataLocacao, LocalDate dataDevolucao) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        setValorLocacao(calcularCustoLocacao());
    }
    
    public int returnPeriodo(){
        return Period.between(dataLocacao, dataDevolucao).getDays();
    }
    
    public int calcularCustoLocacao(){
        return this.veiculo.calcularCustoLocacao() * returnPeriodo();
    }
    
    public String toString(){

        return "Cliente: " + cliente.getNome() + 
        "\nVeículo: " + veiculo.getModelo() + 
        "\nID: " + this.id + 
        "\nValor: " + this.valorLocacao + 
        "\nData de locação: " + this.dataLocacao + 
        "\nData de devolução: " + this.dataDevolucao + "\n";

    }

}
package locadora.model;

import java.time.LocalDate;

public class Pagamento {

    private double valor;
    private int id;
    private String metodo;
    private LocalDate dataPagamento;

    protected Locacao locacao;
    

    public Pagamento(double valor, String metodo, LocalDate dataPagamento,Locacao locacao) {
        this.valor = valor;
        this.metodo = metodo;
        this.dataPagamento = dataPagamento;
        this.locacao = locacao;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public double getValor() {
        return valor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String toString(){
        return "Valor: " + valor + "\n ID: " + id + "\n Método: " + metodo +
        "Data: "+ dataPagamento + "\n\n";
    }

    public double calcularPagamento(){
        if (dataPagamento.isAfter(locacao.getDataDevolucao())) {
            if (locacao.veiculo instanceof Carro) {
                this.valor = valor * 1.1;
                return valor; // 10% multa
            } else if (locacao.veiculo instanceof Moto) {
                this.valor = valor * 1.05;
                return valor; // 5% multa
            } else {
                this.valor = valor * 1.2;
                return valor; // 20% multa 
            }
        } else {
            return valor;
        }
    }
}

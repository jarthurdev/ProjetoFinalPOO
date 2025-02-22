package locadora.model;

import java.time.LocalDate;

public class Pagamento {

    private double valor;
    private int id;
    private String metodo;
    private LocalDate dataPagamento;

    protected Locacao locacao;
    

    public Pagamento(double valor, int id, String metodo){
        this.valor = valor;
        this.id = id;
        this.metodo = metodo;
        this.dataPagamento = LocalDate.now();
    }

    public double getValor() {
        return valor;
    }
    
    public int getId() {
        return id;
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
                return valor * 1.1; // 10% multa
            } else if (locacao.veiculo instanceof Moto) {
                return valor * 1.05; // 5% multa
            } else {
                return valor * 1.2; // 20% multa 
            }
        } else {
            return valor;
        }
    }
}

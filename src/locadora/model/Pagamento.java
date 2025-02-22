package locadora.model;

import java.time.LocalDate;

public class Pagamento {

    private double valor;
    private int id;
    private int idLocacao;
    private String metodo;
    private LocalDate dataPagamento;
    

    public Pagamento(double valor, int id,int idLocacao, String metodo){
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

}

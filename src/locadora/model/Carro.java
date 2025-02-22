package locadora.model;

public class Carro extends Veiculo {
    
    public Carro(String placa, String modelo, int ano, boolean status) {
        super(placa, modelo, ano, status);
    }
    
    public int calcularCustoLocacao() {
        return 80;
    }

}

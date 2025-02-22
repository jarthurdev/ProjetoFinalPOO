package locadora.model;

public class Carro extends Veiculo {
    public int calcularCustoLocacao() {
        return 80;
    }

    public Carro(String placa, String modelo, Integer ano) {
        super(placa, modelo, ano);
    }
}
package locadora.model;

public class Caminhao extends Veiculo {
    public int calcularCustoLocacao() {
        return 160;
    }
    
    public Caminhao(String placa, String modelo, Integer ano) {
        super(placa, modelo, ano, "Caminhao");
    }

    public String toString() {
        return "Caminhão - [ Placa: " + getPlaca() + ", Modelo: " + getModelo() + ", Ano: " + getAno() + " ]";
    }
}
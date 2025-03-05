package locadora.model;

public class Moto extends Veiculo {
    public int calcularCustoLocacao() {
        return 40;
    }

    public Moto(String placa, String modelo, Integer ano) {
        super(placa, modelo, ano, "Moto");
    }

    public String toString() {
        return "Moto - [ Placa: " + getPlaca() + ", Modelo: " + getModelo() + ", Ano: " + getAno() + " ]";
    }
}
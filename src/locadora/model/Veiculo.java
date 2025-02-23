package locadora.model;

public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected Integer ano;
    protected Boolean status;

    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public Integer getAno() {
        return ano;
    }
    
    public Boolean isStatus() {
        return status;
    }

    public abstract int calcularCustoLocacao();

    public Veiculo(String placa, String modelo, Integer ano) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = true;
    }

    public String toString() {
        return String.format("Modelo: %s\nAno: %d\nPlaca: %s", modelo, ano, placa);
    }
}
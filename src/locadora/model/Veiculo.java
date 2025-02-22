package locadora.model;

public abstract class Veiculo {

    protected String modelo;
    protected String placa;
    protected int ano;
    protected boolean status; // true = disponível, false = alugado

    public abstract int calcularCustoLocacao();

    public Veiculo(String placa, String modelo, int ano, boolean status) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = status;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

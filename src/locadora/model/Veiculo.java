package locadora.model;

import com.google.gson.JsonObject;

public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected Integer ano;
    protected Boolean status;
    protected String tipo;

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

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public abstract int calcularCustoLocacao();

    public Veiculo(String placa, String modelo, Integer ano, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = true;
        this.tipo = tipo;
    }

    public static Veiculo fromJson(JsonObject jsonObject) {
        String tipo = jsonObject.get("tipo").getAsString();
        String placa = jsonObject.get("placa").getAsString();
        String modelo = jsonObject.get("modelo").getAsString();
        int ano = jsonObject.get("ano").getAsInt();
        boolean status = jsonObject.get("status").getAsBoolean(); 
    
        Veiculo veiculo = switch (tipo) {
            case "Carro" -> new Carro(placa, modelo, ano);
            case "Moto" -> new Moto(placa, modelo, ano);
            case "Caminhao" -> new Caminhao(placa, modelo, ano);
            default -> throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipo);
        };
    
        veiculo.setStatus(status); 
        return veiculo;
    }

    public String toString() {
        return String.format("Modelo: %s\nAno: %d\nPlaca: %s", modelo, ano, placa);
    }
}
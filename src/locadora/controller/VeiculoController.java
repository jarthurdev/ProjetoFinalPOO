package locadora.controller;
import locadora.model.Veiculo;
import locadora.model.Locacao;
import locadora.model.Moto;
import locadora.model.Carro;
import locadora.model.Caminhao;

public class VeiculoController {

    public Veiculo criarVeiculo(String modelo, String marca, int ano) {
        return new Veiculo(modelo, marca, ano);
    }

}

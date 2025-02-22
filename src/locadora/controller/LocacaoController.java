package locadora.controller;
import locadora.model.Locacao;
import locadora.model.Veiculo;

public class LocacaoController {

    public int calcularCustoLocacao(Veiculo veiculo, Locacao locacao) {
        return veiculo.calcularCustoLocacao() * locacao.returnPeriodo();
    }

}

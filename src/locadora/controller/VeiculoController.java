package locadora.controller;

import java.util.ArrayList;

import locadora.model.Veiculo;

public class VeiculoController {
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public void adicionarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        this.listaVeiculos.remove(veiculo);
    }

    public ArrayList<Veiculo> listarVeiculos() {
        return this.listaVeiculos;
    }

    public Veiculo buscarVeiculoPorModelo(String modelo) {
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.getModelo().equals(modelo)) {
                return veiculo;
            }
        }
        return null;
    }
}

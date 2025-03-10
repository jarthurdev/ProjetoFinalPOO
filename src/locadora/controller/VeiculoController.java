package locadora.controller;

import java.util.ArrayList;
import locadora.model.Veiculo;

public class VeiculoController {
    
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        this.listaVeiculos.remove(veiculo);
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public ArrayList<Veiculo> listarVeiculosDisponiveis() {
        ArrayList<Veiculo> veiculosDisponiveis = new ArrayList<>();
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.isStatus()) {
                veiculosDisponiveis.add(veiculo);
            }
        }
        return veiculosDisponiveis;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) { 
                return veiculo;
            }
        }
        return null;
    }


    public void alterarStatusVeiculoPorPlaca(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.setStatus(false); 
            System.out.println("Status do veículo com placa " + placa + " alterado para false.");
        } else {
            System.out.println("Veículo com placa " + placa + " não encontrado.");
        }
    }

 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Veiculo veiculo : listaVeiculos) {
            sb.append(veiculo.toString()).append("\n");
        }
        return sb.toString();
    }

    public void setListaVeiculos(ArrayList<Veiculo> lista) {
        this.listaVeiculos = lista;
    }

 
}

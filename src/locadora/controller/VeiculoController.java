package locadora.controller;

import java.util.ArrayList;
import locadora.model.Veiculo;

public class VeiculoController {
    
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();

    // Método para cadastrar um veículo
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
    }

    // Método para remover um veículo
    public void removerVeiculo(Veiculo veiculo) {
        this.listaVeiculos.remove(veiculo);
    }

    // Método para listar todos os veículos
    public ArrayList<Veiculo> listarVeiculos() {
        return this.listaVeiculos;
    }

    // Método para listar veículos disponíveis (status == true)
    public ArrayList<Veiculo> listarVeiculosDisponiveis() {
        ArrayList<Veiculo> veiculosDisponiveis = new ArrayList<>();
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.isStatus()) {
                veiculosDisponiveis.add(veiculo);
            }
        }
        return veiculosDisponiveis;
    }

    // Método para buscar um veículo por placa
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) { // Verificando se as placas são iguais, ignorando maiúsculas/minúsculas
                return veiculo;
            }
        }
        return null;
    }

    // Método para alterar o status de um veículo com base na placa
    public void alterarStatusVeiculoPorPlaca(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.setStatus(false); // Altera o status do veículo para false
            System.out.println("Status do veículo com placa " + placa + " alterado para false.");
        } else {
            System.out.println("Veículo com placa " + placa + " não encontrado.");
        }
    }

    // Método para retornar uma representação em String da lista de veículos
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Veiculo veiculo : listaVeiculos) {
            sb.append(veiculo.toString()).append("\n");
        }
        return sb.toString();
    }

    // Método para definir a lista de veículos
    public void setListaVeiculos(ArrayList<Veiculo> lista) {
        this.listaVeiculos = lista;
    }

    // Método para retornar a lista de veículos
    public ArrayList<Veiculo> retornarListaVeiculos() {
        return this.listaVeiculos;
    }
}

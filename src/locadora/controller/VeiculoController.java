package locadora.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.*;
import locadora.model.Veiculo;

public class VeiculoController {
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();

    private final Gson gson;
    private final String arquivo = "C:\\Users\\User\\Documents\\ProjetoFinalPOO\\ProjetoFinalPOO\\src\\locadora\\dao\\VeiculoDAO.json";

    public VeiculoController() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

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

    // Método para buscar um veículo por placa
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : this.listaVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) { // Verificando se as placas são iguais, ignorando maiúsculas/minúsculas
                return veiculo;
            }
        }
        return null;
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

    public void salvarLista() {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(this.listaVeiculos, writer);
            System.out.println("Lista de veículos salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarLista() {
        try (Reader reader = new FileReader(arquivo)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            
            ArrayList<Veiculo> lista = new ArrayList<>();
    
            for (JsonElement element : jsonArray) {
                Veiculo veiculo = Veiculo.fromJson(element.getAsJsonObject());
                lista.add(veiculo);
            }
            this.listaVeiculos = lista;
            System.out.println("Lista de veículos carregada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            e.printStackTrace();
            this.listaVeiculos = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Erro de sintaxe JSON ao carregar o arquivo.");
            e.printStackTrace();
            this.listaVeiculos = new ArrayList<>();
        }
    }    
}
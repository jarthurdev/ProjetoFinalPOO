package locadora.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import locadora.model.Veiculo;

public class VeiculoDAO implements Persistencia<Veiculo>{
    private final Gson gson;
    private final String arquivo = "src/locadora/json/VeiculoDAO.json";

    public VeiculoDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        verificarECriarArquivo();
    }

    public void verificarECriarArquivo() {
        File file = new File(arquivo);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Cria diretórios caso não existam
                file.createNewFile(); // Cria o arquivo JSON
                salvarLista(new ArrayList<>()); // Salva uma lista vazia no JSON
                System.out.println("Arquivo JSON criado: " + arquivo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarLista(ArrayList<Veiculo> listaVeiculos) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaVeiculos, writer);
            System.out.println("Lista de veículos salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public ArrayList<Veiculo> carregarLista() {
            try (Reader reader = new FileReader(arquivo)) {
                JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
                
                ArrayList<Veiculo> lista = new ArrayList<>();
        
                for (JsonElement element : jsonArray) {
                    Veiculo veiculo = Veiculo.fromJson(element.getAsJsonObject());
                    lista.add(veiculo);
                } 
                System.out.println("Lista de veículos carregada com sucesso.");
                return lista;
            
            } catch (IOException e) {
                System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
                e.printStackTrace();
                return new ArrayList<>();
            } catch (JsonSyntaxException e) {
                System.err.println("Erro de sintaxe JSON ao carregar o arquivo.");
                e.printStackTrace();
                return new ArrayList<>();
            }
        }    
}


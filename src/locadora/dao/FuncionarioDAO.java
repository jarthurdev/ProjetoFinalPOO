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

import locadora.model.Funcionario;

public class FuncionarioDAO {
    private final Gson gson;
    private final String arquivo = "src/locadora/dao/FuncionarioDAO.json";

    public FuncionarioDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        verificarECriarArquivo();
    }

    private void verificarECriarArquivo() {
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

    public void salvarLista(ArrayList<Funcionario> listaFuncionarios) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaFuncionarios, writer);
            System.out.println("Lista de Funcionarios salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public ArrayList<Funcionario> carregarLista() {
            try (Reader reader = new FileReader(arquivo)) {
                JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
                
                ArrayList<Funcionario> lista = new ArrayList<>();
        
                for (JsonElement element : jsonArray) {
                    Funcionario funcionario = Funcionario.fromJson(element.getAsJsonObject());
                    lista.add(funcionario);
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


package locadora.dao;

import locadora.model.Veiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class VeiculoDAO implements Persistencia<Veiculo> {
    private final Gson gson;

    // Diretório externo onde os arquivos JSON serão armazenados.
    private final String pastaDados = System.getProperty("user.home") 
            + File.separator + "Locadora" 
            + File.separator + "json";
    
    // Caminho completo do arquivo JSON.
    private final String arquivo = pastaDados + File.separator + "VeiculoDAO.json";

    public VeiculoDAO() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        
        verificarECriarArquivo();
    }

    /**
     * Verifica se o arquivo JSON existe no diretório externo.
     * Se não existir, cria a pasta e tenta copiar o arquivo padrão que está
     * embutido no JAR (em /locadora/json/VeiculoDAO.json).
     * Caso o recurso padrão não seja encontrado, cria um arquivo novo com uma lista vazia.
     */
    public void verificarECriarArquivo() {
        File file = new File(arquivo);
        if (!file.exists()) {
            // Cria o diretório se ele não existir
            File diretorio = new File(pastaDados);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Tenta copiar o arquivo padrão do recurso no JAR
            try (InputStream in = getClass().getResourceAsStream("/locadora/json/VeiculoDAO.json")) {
                if (in != null) {
                    try (OutputStream out = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                        System.out.println("Arquivo JSON padrão copiado para: " + arquivo);
                    } catch (IOException e) {
                        System.err.println("Erro ao copiar o arquivo JSON padrão: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    // Se o recurso padrão não for encontrado, cria o arquivo com uma lista vazia
                    file.createNewFile();
                    salvarLista(new ArrayList<>());
                    System.out.println("Arquivo JSON criado vazio em: " + arquivo);
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo JSON: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Salva a lista de veículos no arquivo JSON externo.
     */
    public void salvarLista(ArrayList<Veiculo> listaVeiculos) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaVeiculos, writer);
            System.out.println("Lista de veículos salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega e retorna a lista de veículos do arquivo JSON externo.
     */
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

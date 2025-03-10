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

    private final String pastaDados = System.getProperty("user.home") 
            + File.separator + "Locadora" 
            + File.separator + "json";
    
    private final String arquivo = pastaDados + File.separator + "VeiculoDAO.json";

    public VeiculoDAO() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        
        verificarECriarArquivo();
    }

    public void verificarECriarArquivo() {
        File file = new File(arquivo);
        if (!file.exists()) {

            File diretorio = new File(pastaDados);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }


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

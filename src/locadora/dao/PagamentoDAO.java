package locadora.dao;

import locadora.model.Pagamento;
import locadora.model.Veiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class PagamentoDAO implements Persistencia<Pagamento> {
    private final Gson gson;

    // Diretório externo onde os arquivos JSON serão armazenados.
    private final String pastaDados = System.getProperty("user.home") 
            + File.separator + "Locadora" 
            + File.separator + "json";
    
    // Caminho completo do arquivo JSON.
    private final String arquivo = pastaDados + File.separator + "PagamentoDAO.json";

    public PagamentoDAO() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Pagamento.class, new PagamentoAdapter())
                .registerTypeAdapter(Veiculo.class, new VeiculoAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        
        verificarECriarArquivo();
    }

    /**
     * Verifica se o arquivo JSON existe no diretório externo.
     * Se não existir, cria a pasta e tenta copiar o arquivo padrão que está
     * embutido no JAR (em /locadora/json/PagamentoDAO.json).
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
            try (InputStream in = getClass().getResourceAsStream("/locadora/json/PagamentoDAO.json")) {
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
     * Salva a lista de pagamentos no arquivo JSON externo.
     */
    public void salvarLista(ArrayList<Pagamento> listaPagamentos) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaPagamentos, writer);
            System.out.println("Lista de pagamentos salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega e retorna a lista de pagamentos do arquivo JSON externo.
     */
    public ArrayList<Pagamento> carregarLista() {
        try (Reader reader = new FileReader(arquivo)) {
            Type tipoLista = new TypeToken<ArrayList<Pagamento>>(){}.getType();
            ArrayList<Pagamento> lista = gson.fromJson(reader, tipoLista);
            
            if (lista == null || lista.isEmpty()) {
                System.out.println("Lista vazia, criando nova lista.");
                return new ArrayList<>();
            }

            System.out.println("Lista de pagamentos carregada com sucesso.");
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

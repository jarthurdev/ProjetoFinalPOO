package locadora.dao;

import locadora.model.Locacao;
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
import java.time.Period;
import java.util.ArrayList;

public class LocacaoDAO implements Persistencia<Locacao> {
    private final Gson gson;

    // Diretório externo onde os arquivos JSON serão armazenados.
    private final String pastaDados = System.getProperty("user.home") 
            + File.separator + "Locadora" 
            + File.separator + "json";
    
    // Caminho completo do arquivo JSON.
    private final String arquivo = pastaDados + File.separator + "LocacaoDAO.json";

    public LocacaoDAO() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Period.class, new PeriodAdapter())
                .registerTypeAdapter(Veiculo.class, new VeiculoAdapter())
                .setPrettyPrinting()
                .create();
        
        verificarECriarArquivo();
    }

    /**
     * Verifica se o arquivo JSON existe no diretório externo.
     * Se não existir, cria a pasta e tenta copiar o arquivo padrão que está
     * embutido no JAR (em /locadora/json/LocacaoDAO.json).
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
            try (InputStream in = getClass().getResourceAsStream("/locadora/json/LocacaoDAO.json")) {
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
     * Salva a lista de locações no arquivo JSON externo.
     */
    public void salvarLista(ArrayList<Locacao> listaLocacoes) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaLocacoes, writer);
            System.out.println("Lista de locações salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega e retorna a lista de locações do arquivo JSON externo.
     */
    public ArrayList<Locacao> carregarLista() {
        try (Reader reader = new FileReader(arquivo)) {
            Type tipoLista = new TypeToken<ArrayList<Locacao>>(){}.getType();
            ArrayList<Locacao> lista = gson.fromJson(reader, tipoLista);
            
            if (lista == null) {
                System.out.println("Lista vazia, criando nova lista.");
                return new ArrayList<>();
            }

            System.out.println("Lista de locações carregada com sucesso.");
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

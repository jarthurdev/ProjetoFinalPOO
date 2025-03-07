package locadora.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import locadora.model.Locacao;

public class LocacaoDAO {

    private final String arquivo = "src/locadora/dao/LocacaoDAO.json";
    private final Gson gson;

    public LocacaoDAO(){
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .registerTypeAdapter(Period.class, new PeriodAdapter())
        .setPrettyPrinting().create();

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

    public void salvarLista(ArrayList<Locacao> listaLocacaos) {

        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaLocacaos, writer);
            System.out.println("Lista de locações salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Locacao> carregarLista() {
        try (Reader reader = new FileReader(arquivo)) {

            Type tipoLista = new TypeToken<ArrayList<Locacao>>(){}.getType();
            ArrayList<Locacao> lista = gson.fromJson(reader, tipoLista);
            
            if (lista==null){
                System.out.println("Lista Vazia, criando nova lista");
                return new ArrayList<Locacao>();
            }

            System.out.println("Lista de locações carregada com sucesso.");
            return lista;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<Locacao>();
        }
    }    
}

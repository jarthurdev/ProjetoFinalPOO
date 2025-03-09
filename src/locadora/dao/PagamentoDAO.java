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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.dao.LocalDateAdapter;
import locadora.model.Pagamento;
import locadora.model.Veiculo;

public class PagamentoDAO implements Persistencia<Pagamento> {

private final String arquivo = "src/locadora/json/PagamentoDAO.json";
    private final Gson gson;

    public PagamentoDAO(){
        gson = new GsonBuilder()
                .registerTypeAdapter(Pagamento.class, new PagamentoAdapter())
                .registerTypeAdapter(Veiculo.class, new VeiculoAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Para lidar com LocalDate
                .setPrettyPrinting()
                 .create();

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

    public void salvarLista(ArrayList<Pagamento> listaLocacaos) {

        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaLocacaos, writer);
            System.out.println("Lista de pagamentos salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pagamento> carregarLista() {
        try (Reader reader = new FileReader(this.arquivo)) {
            Type tipoLista = new TypeToken<ArrayList<Pagamento>>() {}.getType();
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
        }
    }
}






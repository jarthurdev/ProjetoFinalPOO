package locadora.dao;
import locadora.model.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

public class ClienteDAO {


    private final String arquivo = "src/locadora/dao/ClienteDAO.json";
    private final Gson gson;

    public ClienteDAO(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarLista(ArrayList<Cliente> listaCLiente) {

        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(listaCLiente, writer);
            System.out.println("Lista de clientes salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cliente> carregarLista() {
        try (Reader reader = new FileReader(arquivo)) {

            Type tipoLista = new TypeToken<ArrayList<Cliente>>(){}.getType();
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
    
            lista = gson.fromJson(reader, tipoLista);
            
            if (lista==null){
                return new ArrayList<Cliente>();
            }

            System.out.println("Lista de clientes carregada com sucesso.");
            return lista;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<Cliente>();
        }
    }    

}
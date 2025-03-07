package locadora.controller;

import com.google.gson.*;
import locadora.model.Cliente;
import java.io.*;
import java.util.ArrayList;

public class ClienteController {

    private ArrayList<Cliente> listaCliente;
    private final Gson gson;
    private final String arquivo = "src/locadora/dao/ClienteDAO.json"; // Caminho do arquivo JSON

    public ClienteController() {
        this.listaCliente = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        carregarListaClientes();  // Carregar clientes do arquivo ao iniciar o Controller
    }

    // Método para cadastrar um cliente
    public void cadastrarCliente(Cliente cliente) {
        this.listaCliente.add(cliente);
        salvarListaClientes(); // Salvar a lista após adicionar o cliente
    }

    // Método para buscar um cliente por nome
    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : this.listaCliente) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    // Método para remover um cliente
    public boolean removerCliente(String nome) {
        for (Cliente c : listaCliente) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                listaCliente.remove(c);
                salvarListaClientes(); // Salvar a lista após remover o cliente
                return true;
            }
        }
        return false;
    }

    // Método para salvar a lista de clientes no arquivo JSON
    private void salvarListaClientes() {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(this.listaCliente, writer);
            System.out.println("Lista de clientes salva em " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar a lista de clientes do arquivo JSON
    public void carregarListaClientes() {
        try (Reader reader = new FileReader(arquivo)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            ArrayList<Cliente> lista = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                Cliente cliente = Cliente.fromJson(element.getAsJsonObject());
                lista.add(cliente);
            }
            this.listaCliente = lista;
            System.out.println("Lista de clientes carregada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            e.printStackTrace();
            this.listaCliente = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Erro de sintaxe JSON ao carregar o arquivo.");
            e.printStackTrace();
            this.listaCliente = new ArrayList<>();
        }
    }

    // Método para retornar a lista de clientes
    public ArrayList<Cliente> retornarListaClientes() {
        return this.listaCliente;
    }

    // Método para obter uma representação em string de todos os clientes
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : listaCliente) {
            sb.append(cliente.toString()).append("\n\n");
        }
        return sb.toString();
    }
}

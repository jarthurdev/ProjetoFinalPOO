package locadora.controller;

import locadora.model.Cliente;
import java.util.ArrayList;

public class ClienteController {

    private ArrayList<Cliente> listaCliente;
   
    public ClienteController() {
        this.listaCliente = new ArrayList<>();
    }

    // Método para cadastrar um cliente
    public void cadastrarCliente(Cliente cliente) {
        this.listaCliente.add(cliente);
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
                return true;
            }
        }
        return false;
    }

    public void setListaClientes(ArrayList<Cliente> lista){
        this.listaCliente = lista;
    }

    // Método para retornar a lista de clientes
    public ArrayList<Cliente> getListaClientes() {
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

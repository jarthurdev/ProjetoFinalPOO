package locadora.controller;

import locadora.model.Cliente;
import java.util.ArrayList;

public class ClienteController {

    private ArrayList<Cliente> listaCliente;
   
    public ClienteController() {
        this.listaCliente = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        this.listaCliente.add(cliente);
    }

    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : this.listaCliente) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

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

    public ArrayList<Cliente> getListaClientes() {
        return this.listaCliente;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : listaCliente) {
            sb.append(cliente.toString()).append("\n\n");
        }
        return sb.toString();
    }
}

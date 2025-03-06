package locadora.controller;
import java.util.ArrayList;
import locadora.model.Cliente;


public class ClienteController {

    private ArrayList<Cliente> listaCliente;

    public ClienteController() {
        this.listaCliente = new ArrayList<>();
    }
   
    public void cadastrarCliente(Cliente cliente){

        this.listaCliente.add(cliente);

    }

    public Cliente buscarCliente(String nome){

        for(Cliente cliente : this.listaCliente){

            if(cliente.getNome().equalsIgnoreCase(nome)){
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

    public String toString(){

        String lista = "";

        for(Cliente cliente : listaCliente){

            lista += cliente.toString() + "\n\n";

        }
        
        return lista;
    }

    public ArrayList<Cliente> retornarListaClientes(){
        return this.listaCliente;
    }

    public void carregarListaClientes(ArrayList<Cliente> lista){

        this.listaCliente = lista;

    }

}
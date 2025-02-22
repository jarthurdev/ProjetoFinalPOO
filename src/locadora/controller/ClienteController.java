package locadora.controller;
import java.util.ArrayList;
import java.util.Iterator;
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

        for(Cliente cliente : listaCliente){

            if(cliente.getNome().equalsIgnoreCase(nome)){
                return cliente;
            }
        }
        return null;  
    }

    public void removerCliente(String nome) {
        // Usa um Iterator para evitar ConcurrentModificationException
        Iterator<Cliente> iterator = listaCliente.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                iterator.remove(); // Remove de forma segura
                break; // Sai do loop após remover o primeiro encontrado
            }
        }
    }  

    public String toString(){

        String lista = "";

        for(Cliente cliente : listaCliente){

            lista += cliente.toString() + "\n\n";

        }
        
        return lista;

    }

}
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

    public Cliente buscarNome(String nome){

        for(Cliente cliente : listaCliente){

            if(cliente.getNome().equalsIgnoreCase(nome)){
                return cliente;
            }
        }
        return null;  
    }

<<<<<<< HEAD
    public Cliente buscarCPF(String cpf){

        for(Cliente cliente : listaCliente){

            if(cliente.getCpf().equalsIgnoreCase(cpf)){
                return cliente;
            }
        }
        return null;  
    }

    public void removerCliente(String nome){

        for(Cliente cliente : listaCliente){

            if(cliente.getNome().equalsIgnoreCase(nome)){
             listaCliente.remove(cliente);
=======
    public boolean removerCliente(String nome) {
        for (Cliente c : listaCliente) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                listaCliente.remove(c);
                return true;
>>>>>>> main
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

}
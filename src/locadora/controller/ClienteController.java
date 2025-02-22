package locadora.controller;
import locadora.model.Cliente;
import java.util.ArrayList;


public class ClienteController {

    private ArrayList<Cliente> listaCliente;
   

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

    public void removerCliente(String nome){

        for(Cliente cliente : listaCliente){

            if(cliente.getNome().equalsIgnoreCase(nome)){
             listaCliente.remove(cliente);
            }
        }
    }   

    public String toString(){

        String lista = "";

        for(Cliente cliente : listaCliente){

            lista += cliente.toString() + "\n";

        }
        
        return lista;

    }






}
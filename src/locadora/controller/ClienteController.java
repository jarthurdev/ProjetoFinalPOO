package locadora.controller;
import locadora.model.Cliente;
import java.util.ArrayList;


public class ClienteController {

    private ArrayList<Cliente> listaCliente;
   

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
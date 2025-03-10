package locadora.controller;

import java.util.ArrayList;

import locadora.model.Administrador;
import locadora.model.Atendente;
import locadora.model.Funcionario;
import locadora.model.Gerente;

public class FuncionarioController {
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

    public void cadastrarAtendente(String usuario, String senha, String tipo) {
        Funcionario atendente = new Atendente(usuario, senha, tipo);
        funcionarios.add(atendente);
    }

    public void cadastrarGerente(String usuario, String senha, String tipo) {
        Funcionario gerente = new Gerente(usuario, senha, tipo);
        funcionarios.add(gerente);
    }

    public void cadastrarAdministrador(String usuario, String senha, String tipo) {
        Funcionario administrador = new Administrador(usuario, senha, tipo);
        funcionarios.add(administrador);
    }

    public String buscarFuncionario(String usuario, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getUsuario().equals(usuario) && funcionario.getSenha().equals(senha)) {
                return funcionario.getClass().getSimpleName();
            }
        }
        return "Funcionário não encontrado";
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public Boolean verificarLogin(String usuario, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getUsuario().equals(usuario) && funcionario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

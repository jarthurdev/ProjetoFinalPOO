package locadora.model;

import com.google.gson.JsonObject;

public abstract class Funcionario {
    protected String usuario;
    protected String senha;
    protected String tipo;

    public Funcionario(String usuario, String senha, String tipo) {
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    public static Funcionario fromJson(JsonObject jsonObject) {
        String usuario = jsonObject.get("usuario").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String tipo = jsonObject.get("tipo").getAsString();
    
        Funcionario funcionario = switch (tipo) {
            case "Atendente" -> new Atendente(usuario, senha, tipo);
            case "Gerente" -> new Gerente(usuario, senha, tipo);
            case "Administrador" -> new Administrador(usuario, senha, tipo);

            default -> throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipo);
        };
        return funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    
}

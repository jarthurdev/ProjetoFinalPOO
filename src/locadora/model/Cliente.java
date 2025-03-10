package locadora.model;

import com.google.gson.JsonObject;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Cliente fromJson(JsonObject jsonObject) {
        String nome = jsonObject.get("nome").getAsString();
        String cpf = jsonObject.get("cpf").getAsString();
        String telefone = jsonObject.get("telefone").getAsString();
        String email = jsonObject.get("email").getAsString();
        return new Cliente(nome, cpf, telefone, email);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nTelefone: " + telefone + "\nEmail: " + email;
    }
}

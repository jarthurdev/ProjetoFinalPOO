package locadora.model;

public class Cliente {
    private String nome;
    private String cpf;
    private Integer telefone;
    private String email;
    
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
    public Integer getTelefone() {
        return telefone;
    }
    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente(String nome, String cpf, Integer telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String toString(){

        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nTelefone: " + telefone + "Email: " + email;

    }
}
package locadora.model;

public class Cliente {

    private String nome;
    private String cpf;
    private int telefone;
    private String email;

    public Cliente(String nome, String cpf, String endereco, int telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }


    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email + '}';
    }
    
}

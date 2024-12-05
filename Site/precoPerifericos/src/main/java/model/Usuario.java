package model;

public class Usuario {
    private int id_user;
    private String nome;
    private String email;
    private String senha;
    private Boolean status;

    // Getters and Setters
    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

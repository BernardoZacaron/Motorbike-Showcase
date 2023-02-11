package bz.java.motoreasy.model.dto;

public class UsuarioDTO {
    private String nome, email, username, senha, senhaRepetida;

    public UsuarioDTO(String nome, String email, String username, String senha) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
    }

    public UsuarioDTO(String nome, String email, String username, String senha, String senhaRepetida) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.senhaRepetida = senhaRepetida;
    }

    public UsuarioDTO() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaRepetida() {
        return senhaRepetida;
    }

    public void setSenhaRepetida(String senhaRepetida) {
        this.senhaRepetida = senhaRepetida;
    }
}

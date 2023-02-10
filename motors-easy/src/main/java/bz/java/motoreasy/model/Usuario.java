package bz.java.motoreasy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome, email, login, senha;
    @OneToMany
    private List<Moto> listaDesejo;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String login, String senha, List<Moto> listaDesejo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.listaDesejo = listaDesejo;
    }

    public Usuario(String nome, String email, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Moto> getListaDesejo() {
        return listaDesejo;
    }

    public void setListaDesejo(List<Moto> listaDesejo) {
        this.listaDesejo = listaDesejo;
    }
}

package bz.java.motoreasy.model;

import bz.java.motoreasy.model.dto.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nome, email, senha;
    @OneToMany(mappedBy="usuario")
    private List<Moto> favoritas = new ArrayList<>();
    private boolean administrador;

    public Usuario() {
    }

    public Usuario(Long id, String username, String nome, String email, String senha, List<Moto> favoritas, boolean administrador) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.favoritas = favoritas;
        this.administrador = administrador;
    }

    public Usuario(Long id, String username, String nome, String email, String senha, boolean administrador) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.username = dto.getUsername();
        this.senha = dto.getSenha();
        this.administrador = false;
    }

    public Usuario(String nome, String username, String email, String senha) {
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void adicionarFavorita(Moto moto){
        favoritas.add(moto);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPassword() {
        return senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public List<Moto> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Moto> favoritas) {
        this.favoritas = favoritas;
    }
}

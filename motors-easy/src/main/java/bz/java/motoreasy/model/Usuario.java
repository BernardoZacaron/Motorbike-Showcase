package bz.java.motoreasy.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nome, email, senha;
    /*@OneToMany
    private List<Moto> listaDesejo;*/
    private List<String> roles = new ArrayList<>();

    private boolean administrador;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String username, String senha, List<Moto> listaDesejo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.listaDesejo = listaDesejo;
    }

    public Usuario(String nome, String email, String username, String senha) {
        this.nome = nome;
        this.email = email;
        this.username = username;
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

    public List<String> getRoles() {
        roles.add("ROLE_CLIENTE");
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));

        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

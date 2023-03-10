package bz.java.motoreasy.model;

import bz.java.motoreasy.model.dto.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
    /*Criar uma classe intermediaria tipo do Zetalab
    private List<Moto> favoritas;*/
    private boolean administrador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "listaFavoritos_id", referencedColumnName = "id")
    private ListaFavoritos lista;


    public Usuario() {
    }

    public Usuario(Long id, String username, String nome, String email, String senha, boolean administrador, ListaFavoritos lista) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
        this.lista = lista;
    }

    public Usuario(String nome, String username, String email, String senha, boolean administrador, ListaFavoritos lista) {
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
        this.lista = lista;
    }

    public Usuario(String nome, String username, String email, String senha, boolean administrador) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

//    public List<Moto> getFavoritas() {
//        return favoritas;
//    }
//
//    public void setFavoritas(List<Moto> favoritas) {
//        this.favoritas = favoritas;
//    }


    public ListaFavoritos getLista() {
        return lista;
    }

    public void setLista(ListaFavoritos lista) {
        this.lista = lista;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        if (isAdministrador()){
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
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


}

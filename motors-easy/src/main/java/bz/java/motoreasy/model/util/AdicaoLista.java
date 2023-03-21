package bz.java.motoreasy.model.util;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "LISTA_FAVORITOS_MOTOS")
public class AdicaoLista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Usuario usuario;

    @ManyToOne
    Moto moto;

    public AdicaoLista() {
    }

    public AdicaoLista(Long id, Usuario usuario, Moto moto) {
        this.id = id;
        this.usuario = usuario;
        this.moto = moto;
    }

    public AdicaoLista(Usuario usuario, Moto moto) {
        this.usuario = usuario;
        this.moto = moto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}

package bz.java.motoreasy.model.util;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;

import javax.persistence.*;

@Entity
@Table(name = "LISTA_FAVORITOS_MOTOS")
public class AdicaoLista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @Column(name = "LISTA_FAVORITOS_ID")
    ListaFavoritos lista;

    @ManyToOne
    @Column(name = "MOTOS_ID")
    Moto moto;

    public AdicaoLista() {
    }

    public AdicaoLista(Long id, ListaFavoritos lista, Moto moto) {
        this.id = id;
        this.lista = lista;
        this.moto = moto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ListaFavoritos getLista() {
        return lista;
    }

    public void setLista(ListaFavoritos lista) {
        this.lista = lista;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}

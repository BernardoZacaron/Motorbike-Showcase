package bz.java.motoreasy.model.util;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "LISTA_FAVORITOS_MOTOS")
public class ListaFavoritosMotos {
    @Column(name = "LISTA_FAVORITOS_ID")
    private ListaFavoritos listaFavoritosId;
    @Column(name = "MOTOS_ID")
    private Moto motosId;

    public ListaFavoritosMotos() {
    }

    public ListaFavoritosMotos(ListaFavoritos listaFavoritosId, Moto motosId) {
        this.listaFavoritosId = listaFavoritosId;
        this.motosId = motosId;
    }

    public ListaFavoritos getListaFavoritosId() {
        return listaFavoritosId;
    }

    public void setListaFavoritosId(ListaFavoritos listaFavoritosId) {
        this.listaFavoritosId = listaFavoritosId;
    }

    public Moto getMotosId() {
        return motosId;
    }

    public void setMotosId(Moto motosId) {
        this.motosId = motosId;
    }
}

package bz.java.motoreasy.model.util;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "LISTA_FAVORITOS_MOTOS")
public class ListaFavoritosMotos {
    @Column(name = "LISTA_FAVORITOS_ID")
    private long listaFavoritosId;
}

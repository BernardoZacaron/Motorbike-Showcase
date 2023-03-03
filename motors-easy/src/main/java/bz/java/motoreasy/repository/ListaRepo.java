package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {

    static void removerMoto(ListaFavoritos lista, Moto moto){
        lista.getMotos().remove(moto);
    }
}

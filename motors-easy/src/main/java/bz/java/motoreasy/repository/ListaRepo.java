package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {

    @Query("DELETE FROM lista_moto lm WHERE listafavoritos_id = ?1 and moto_id = ?2")
    void removerMoto(ListaFavoritos lista, Moto moto);
}

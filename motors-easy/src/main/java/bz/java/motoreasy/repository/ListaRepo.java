package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {

    @Query("DELETE FROM ListaFavoritos lf WHERE lf.id = ?1 and lf.motos = ?2")
    void removerMoto(ListaFavoritos lista, long motoId);
}

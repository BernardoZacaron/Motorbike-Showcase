package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {

//    @Query("WITH selectLista as (Select * from ListaFavoritos lf JOIN Moto m ON m.id = lf.motoId Where lf.id = ?1 AND lf.motoId = ?2) Delete from selectLista")
//    void removerMoto(long listaId, long motoId);
}

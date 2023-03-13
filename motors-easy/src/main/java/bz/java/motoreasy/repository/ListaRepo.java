package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {

    @Query("delete from ListaFavoritos lf Moto m where lf.id=?1 and lf.motos.moto_id = ?2")
    void removerMoto(long listaId, long motoId);
}

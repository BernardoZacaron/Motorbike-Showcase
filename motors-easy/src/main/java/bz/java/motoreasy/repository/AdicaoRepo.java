package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.util.AdicaoLista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdicaoRepo extends JpaRepository<AdicaoLista, Long> {

//    @Query("DELETE FROM LISTA_FAVORITOS_MOTOS lfm WHERE lfm.LISTA_FAVORITOS_ID  = ?1 and lfm.MOTOS_ID = ?2")
//    void removerMoto(long listaId, long motoId);
}

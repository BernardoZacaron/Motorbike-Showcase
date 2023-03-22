package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.util.AdicaoLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdicaoRepo extends JpaRepository<AdicaoLista, Long> {

    @Query("DELETE FROM AdicaoLista WHERE USUARIO = ?1 AND MOTO = ?2")
    void removerMotoDaLista(long usuarioId, long motoId);

    List<Moto> findByUsuario(Usuario usuario);
}

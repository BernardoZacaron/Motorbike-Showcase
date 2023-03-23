package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.util.AdicaoLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdicaoRepo extends JpaRepository<AdicaoLista, Long> {

    @Modifying
    @Query(value = "DELETE FROM AdicaoLista WHERE USUARIO_ID = ?1 AND MOTO_ID = ?2")
    void removerMotoDaLista(long usuarioId, long motoId);

    List<AdicaoLista> findByUsuario(Usuario usuario);
}

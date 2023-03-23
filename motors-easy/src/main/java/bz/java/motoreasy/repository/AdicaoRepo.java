package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.util.Adicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdicaoRepo extends JpaRepository<Adicao, Long> {

    @Modifying
    @Query(value = "DELETE FROM Adicao WHERE USUARIO_ID = ?1 AND MOTO_ID = ?2")
    void removerMotoDaLista(long usuarioId, long motoId);

    List<Adicao> findByUsuario(Usuario usuario);
}

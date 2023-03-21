package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.util.AdicaoLista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdicaoRepo extends JpaRepository<AdicaoLista, Long> {

    void deleteByUsuarioAndMoto(Usuario usuario, Moto moto);

    List<Moto> findByUsuario(Usuario usuario);
}

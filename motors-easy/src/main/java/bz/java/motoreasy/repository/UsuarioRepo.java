package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}

package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.ListaFavoritos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ListaRepo extends JpaRepository<ListaFavoritos, Long> {
}

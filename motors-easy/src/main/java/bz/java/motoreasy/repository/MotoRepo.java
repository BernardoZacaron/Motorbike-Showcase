package bz.java.motoreasy.repository;

import bz.java.motoreasy.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MotoRepo extends JpaRepository<Moto, Long> {
}

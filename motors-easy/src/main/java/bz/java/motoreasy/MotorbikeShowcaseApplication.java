package bz.java.motoreasy;

import bz.java.motoreasy.model.ListaFavoritos;
import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.repository.ListaRepo;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
import bz.java.motoreasy.seguranca.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MotorbikeShowcaseApplication implements CommandLineRunner {
	@Autowired
	MotoRepo motoRepo;

	@Autowired
	UsuarioRepo userRepo;

	@Autowired
	ListaRepo listaRepo;

	@Autowired
	UserService service;

	@Autowired
	PasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(MotorbikeShowcaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Moto m1 = new Moto("Honda", "Twister", 250, 22000, false, true, "Asfalto", true);
		motoRepo.save(m1);
		Moto m2 = new Moto("Yamaha", "Fazer", 250, 24000, false, true, "Asfalto", true);
		motoRepo.save(m2);
		Moto m3 = new Moto("Honda", "XRE", 190, 20000, false, true, "Misto", true);
		motoRepo.save(m3);
		Moto m4 = new Moto("Honda", "Fan", 160, 15000, false, false, "Asfalto", true);
		motoRepo.save(m4);
		Moto m5 = new Moto("Teste", "Desativadah", 000, 0000, true, true, "Estrada", false);
		motoRepo.save(m5);

//		ListaFavoritos l1 = new ListaFavoritos();
//		listaRepo.save(l1);
//		ListaFavoritos l2 = new ListaFavoritos();
//		listaRepo.save(l2);

		Usuario u1 = new Usuario("Bernardo", "ber", "bernardo@email", pe.encode("senha"), false);
		userRepo.save(u1);
		Usuario u2 = new Usuario("Admilton", "adm", "admilton@email", pe.encode("senha"), true);
		userRepo.save(u2);
	}
}

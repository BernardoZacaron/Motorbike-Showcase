package bz.java.motoreasy;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.UsuarioDTO;
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
	UserService service;

	@Autowired
	PasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(MotorbikeShowcaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Moto m1 = new Moto("Honda", "Twister", 250, 22000, false, true, "Asfalto", true, "https://s2.glbimg.com/7vLB6RQP_SYpNSwPMUBX0mJZMTk=/0x0:1600x1202/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2018/o/o/KlVxpdSRaV4DIphR8Dhg/twister.jpg");
		motoRepo.save(m1);
		Moto m2 = new Moto("Yamaha", "Fazer", 250, 24000, false, true, "Asfalto", true, "https://http2.mlstatic.com/D_NQ_NP_687581-MLB49707168914_042022-O.jpg");
		motoRepo.save(m2);
		Moto m3 = new Moto("Honda", "XRE", 190, 20000, false, true, "Misto", true, "https://images.squarespace-cdn.com/content/v1/5c8ff4a1fd67933da7cdf5d8/1625159636891-6BBRCDHM7077123S9TCU/Galeria+-+1920x980px_0002_XRE+300ADV+3_4+FD.jpg?format=1000w");
		motoRepo.save(m3);
		Moto m4 = new Moto("Honda", "Fan", 160, 15000, false, false, "Asfalto", true, "https://motonewsbrasil.com/wp-content/uploads/2018/09/honda-cg-160-fan-2019-1-e1586525290984.jpg");
		motoRepo.save(m4);
		Moto m5 = new Moto("Teste", "Desativadah", 000, 0000, true, true, "Estrada", false, "https://boracolorir.com.br/wp-content/uploads/2022/02/desenhos-de-motos-para-colorir-7-1024x585.jpg");
		motoRepo.save(m5);
		Moto m6 = new Moto("Harley-Davidson", "Sportster", 883, 94000, true, true, "Misto", true, "https://cdn.motor1.com/images/mgl/z6xvK/s1/2021-harley-davidson-sportster-s---vivid-black-main.jpg");
		motoRepo.save(m6);


		Usuario u1 = new Usuario("Bernardo", "ber", "bernardo@email", pe.encode("senha"), false);
		userRepo.save(u1);
		Usuario u2 = new Usuario("Admilton", "adm", "admilton@email", pe.encode("senha"), true);
		userRepo.save(u2);
	}
}

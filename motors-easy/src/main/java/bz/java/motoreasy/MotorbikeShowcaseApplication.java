package bz.java.motoreasy;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.repository.MotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MotorbikeShowcaseApplication implements CommandLineRunner {
	@Autowired
	MotoRepo motoRepo;

	public static void main(String[] args) {
		SpringApplication.run(MotorbikeShowcaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Moto m1 = new Moto("Twister",200, 18000, false);
		motoRepo.save(m1);
		Moto m2 = new Moto("CG Titan",160, 15700, false);
		motoRepo.save(m2);
		Moto m3 = new Moto("Honda XRE",190, 18700, false);
		motoRepo.save(m3);
		Moto m4 = new Moto("Dafra Citycom",300, 25400, true);
		motoRepo.save(m4);
	}
}

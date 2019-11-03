package hh.swd20.sauna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hh.swd20.sauna.domain.Reservation;
import hh.swd20.sauna.domain.ReservationRepository;
import hh.swd20.sauna.domain.Sauna;
import hh.swd20.sauna.domain.SaunaRepository;
import hh.swd20.sauna.domain.User;
import hh.swd20.sauna.domain.UserRepository;



@SpringBootApplication
public class SaunaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SaunaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SaunaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner reservationDemo(ReservationRepository rRepository, SaunaRepository sRepository, UserRepository uRepository) { 
		return (args) -> {
			log.info("save a couple of reservations");
			Sauna saunaOne = new Sauna("S1", 3.00);
			sRepository.save(saunaOne);
			
			Sauna saunaTwo = new Sauna("S2", 4.00);
			sRepository.save(saunaTwo);
			
			Sauna saunaThree = new Sauna("S3", 5.00);
			sRepository.save(saunaThree);
			
			// create 3 users and admin
			User user1 = new User("user1", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"A43", "Paavo", "Petäistö", "paavo.m.petaisto@suomi24.fi", "USER");
			User user2 = new User("user2", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"C26", "Reijo", "Järvi", "reijo.jarvi@gmail.com", "USER");
			User user3 = new User("user3", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"D3", "Raija", "Nieminen", "raija@hotmail.com", "USER");
			User admin = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"B13", "Paula", "Pääkäyttäjä", "paula@admin.com", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
			uRepository.save(user3);
			uRepository.save(admin);
			
			rRepository.save(new Reservation("16.10.2019", "18:00", "19:00", "Yksityinen", saunaOne, user1));
			rRepository.save(new Reservation("18.10.2019", "19:00", "20:00", "Lenkkisauna M", saunaTwo, admin));
			rRepository.save(new Reservation("01.12.2019", "20:00", "21:00", "Yksityinen", saunaThree, user3));
			
			
			
			
//			log.info("fetch all reservations");
//			for (Reservation reservation : rRepository.findAll()) {
//				log.info(reservation.toString());
//			}

		};
	}
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

}

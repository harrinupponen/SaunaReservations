package hh.swd20.sauna;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.sauna.domain.Reservation;
import hh.swd20.sauna.domain.ReservationRepository;
import hh.swd20.sauna.domain.Sauna;
import hh.swd20.sauna.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTests {

    @Autowired
    private ReservationRepository rRepository;
    
    @Test
    public void findByDateShouldReturnReservation() {
    	
    	List<Reservation> reservations = rRepository.findByDay(LocalDate.of(2019, 11, 20));
    	assertThat(reservations.get(0).getType()).isEqualTo("Yksityinen");
    }

    @Test
    public void createNewReservation() {
    	LocalDate day = LocalDate.of(2019, 11, 20);
    	Sauna sauna = new Sauna("TestSauna", 5, "Description", 5.00);
    	User user = new User("user1", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"A43", "Paavo", "Petäistö", "paavo.m.petaisto@suomi24.fi", "USER");
    	Reservation reservation = new Reservation(day, "18:00", "19:00", "Yksityinen", sauna, user);
    	rRepository.save(reservation);
    	assertThat(reservation.getId()).isNotNull();
    }
    
    @Test
    public void deleteReservation() {
    	List<Reservation> reservations = rRepository.findByDay(LocalDate.of(2019, 11, 20));
    	rRepository.deleteById(reservations.get(0).getId());
    	assertThat(reservations.isEmpty());
    }
}
package hh.swd20.sauna.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	List<Reservation> findByDay(LocalDate day);
	
}
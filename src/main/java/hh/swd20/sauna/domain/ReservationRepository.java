package hh.swd20.sauna.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	List<Reservation> findByDate(String date);

}
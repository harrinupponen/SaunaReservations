package hh.swd20.sauna.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SaunaRepository extends CrudRepository<Sauna, Long> {
	
	List<Sauna> findByName(String name);

}

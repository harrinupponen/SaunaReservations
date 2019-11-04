package hh.swd20.sauna;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.sauna.domain.Sauna;
import hh.swd20.sauna.domain.SaunaRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class SaunaRepositoryTests {

    @Autowired
    private SaunaRepository sRepository;
    
    @Test
    public void findByNameShouldReturnSauna() {
    	List<Sauna> saunas = sRepository.findByName("S1");
    	assertThat(saunas.get(0).getSaunaId()).isNotNull();
    }

    @Test
    public void createNewSauna() {
    	Sauna sauna = new Sauna("TestSauna", 5, "Description", 3.00);
    	sRepository.save(sauna);
    	assertThat(sauna.getSaunaId()).isNotNull();
    }
    
    @Test
    public void deleteCategory() {
    	List<Sauna> saunas = sRepository.findByName("S2");
    	sRepository.deleteById(saunas.get(0).getSaunaId());
    	assertThat(saunas.isEmpty());
    }
}

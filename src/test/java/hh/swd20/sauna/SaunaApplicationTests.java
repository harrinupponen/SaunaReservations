package hh.swd20.sauna;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.sauna.web.SaunaController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaunaApplicationTests {
	
	@Autowired
	private SaunaController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

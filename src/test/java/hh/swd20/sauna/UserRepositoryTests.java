package hh.swd20.sauna;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.sauna.domain.User;
import hh.swd20.sauna.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository urepository;
    
    @Test
    public void findByUsernameShouldReturnUser() {
    	User user = urepository.findByUsername("user1");
    	assertThat(user.getEmail()).isEqualTo("paavo.m.petaisto@suomi24.fi");
    }

    @Test
    public void createNewUser() {
    	User testUser = new User("testuser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
    			"A43", "Paavo", "Petäistö", "testuser@sauna.com", "USER");
    	urepository.save(testUser);
    	assertThat(testUser.getUserId()).isNotNull();
    }
    
    @Test
    public void deleteUser() {
    	User user = urepository.findByUsername("user2");
    	urepository.deleteById(user.getUserId());
    	assertThat(user.getUserId().equals(null));
    }
}

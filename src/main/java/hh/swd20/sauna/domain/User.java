package hh.swd20.sauna.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.sauna.validation.StringUpperCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Transient
	@Size(min=5, max=15)
	private String plainPassword;

    @Column(name = "password", nullable = false)
    public String passwordHash;
    
    @Column(name = "apartmentNr", nullable = false)
    private String apartmentNr;
    
    @Column(name = "firstName", nullable = false)
    private String firstName;
    
    @Column(name = "lastName", nullable = false)
    private String lastName;
    
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @StringUpperCase()
    @Column(name = "role", nullable = false)
    private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Reservation> reservationList;
	
	public void setPlainPassword(String plainPassword) {
		this.passwordHash = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}

	public User(String username, String passwordHash, String apartmentNr, String firstName, String lastName,
			String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.apartmentNr = apartmentNr;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}	
}

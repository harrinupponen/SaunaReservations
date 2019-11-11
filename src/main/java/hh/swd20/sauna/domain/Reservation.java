package hh.swd20.sauna.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate day;
	private String start;
	private String end;
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="saunaId")
	private Sauna sauna;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="userId")
	private User user;

	public Reservation(LocalDate day, String start, String end, String type, Sauna sauna, User user) {
		super();
		this.day = day;
		this.start = start;
		this.end = end;
		this.type = type;
		this.sauna = sauna;
		this.user = user;
	}
}

package hh.swd20.sauna.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sauna {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long saunaId;
	private String name;
	private int personCapacity;
	private String desc;
	private double price;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sauna")
	private List<Reservation> reservationList;
	
	
	public Sauna(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
}

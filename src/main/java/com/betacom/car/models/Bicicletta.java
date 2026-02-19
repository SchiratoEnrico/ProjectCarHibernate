package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity (name = "biciclette")
public class Bicicletta {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
			name = "numero_marce",
			nullable = false
			)
	private Integer numeroMarce;
	
	private TipoFreno freno;
	
	private TipoSospensione sospensione;
	
	@Column(
			nullable = false
			)
	private Boolean pieghevole;
}

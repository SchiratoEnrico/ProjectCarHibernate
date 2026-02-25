package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

@Entity
@Table(name = "moto")
@PrimaryKeyJoinColumn(name = "id")

public class Moto extends Veicolo{

	@Column(
			name = "cc",
			nullable = false
			)
	private Integer cc;
	
	@Column(
			name = "numero_marce",
			nullable = false
			)
	private Integer numeroMarce;
	
	
	@Column(name = "targa", nullable = false, unique = true)
	@Pattern(
		    regexp = "^[A-HJ-NPR-TV-Z]{2}[0-9]{3}[A-HJ-NPR-TV-Z]{2}$",
		    message = "Formato targa non valido (es: AB123CD)"
		)
	private String targa;
}

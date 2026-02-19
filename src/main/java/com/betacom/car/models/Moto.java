package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity (name = "moto")
public class Moto {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	
	
	@Column(name = "targa", nullable = false)
	@NotBlank(message = "La targa non può essere vuota")
	@Pattern(
	    regexp = "^[XY][BCDFGHJKLMNPRSTVWXYZ2-9]{5}$",
	    message = "Formato targa non valido (es: AB123CD)"
	)
	private String targa;
}

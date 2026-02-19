package com.betacom.car.models;

import org.hibernate.annotations.NotFound;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "macchine")

//con questa annotazione dico che la primary key di macchina è la stessa primary key di veicolo
//si uniscono su id
@PrimaryKeyJoinColumn(name = "id") // PK della macchina = FK verso veicolo.id

public class Macchina extends Veicolo{
	
	
	@Column(
			name = "cc",
			nullable = false
			)
	private Integer cc;
	
	@Column(
			name = "numero_porte",
			nullable = false
			)
	private Integer numeroPorte;
	
	
	@Column(name = "targa", nullable = false, unique = true)
	@Pattern(
	    regexp = "^[A-HJ-NPR-TV-Z]{2}[0-9]{3}[A-HJ-NPR-TV-Z]{2}$",
	    message = "Formato targa non valido (es: AB123CD)"
	)
	private String targa;
}

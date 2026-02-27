package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private String targa;
}

package com.betacom.car.models;

import java.time.LocalDate;

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

@Entity (name = "veicoli")
public class Veicolo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Colore colore;
	
	private Marca marca;
	
	private TipoAlimentazione tipoAlimentazione;
	
	private Categoria categoria;
	
	private TipoVeicolo tipoVeicolo;
	
	@Column(
			name = "numero_ruote",
			nullable = false
			)
	private Integer numeroRuote;
	
	@Column(
			name = "anno_produzione",
			nullable = false
			)
	private LocalDate annoProduzione;
}

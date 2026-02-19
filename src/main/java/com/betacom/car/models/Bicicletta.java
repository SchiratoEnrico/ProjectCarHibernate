package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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
@Table(name = "biciclette")
@PrimaryKeyJoinColumn(name = "id")
public class Bicicletta extends Veicolo{
	
	
	@Column(
			name = "numero_marce",
			nullable = false
			)
	private Integer numeroMarce;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_freno")
	private TipoFreno freno;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sospensione")
	private TipoSospensione sospensione;
	
	@Column(
			nullable = false
			)
	private Boolean pieghevole;
}

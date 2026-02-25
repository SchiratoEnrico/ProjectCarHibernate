package com.betacom.car.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VeicoloFilterRequest {
	
	 	private Integer numeroRuote;
	    private Integer anno;
	    private String marca;
	    private String modello;
	    private String colore;
	    private String categoria;
	    private String tipoAlimentazione;
	    private String tipoVeicolo;
}

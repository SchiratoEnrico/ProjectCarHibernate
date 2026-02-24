package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class VeicoloFilterRequest {
	
	 	private Integer numeroRuote;
	    private Integer anno;
	    private String marca;
	    private String modello;
	    private String colore;
	    private String categoria;
	    private String tipoAlimentazione;
	    private String tipoVeicolo;
}

package com.betacom.car.dto.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class VeicoloFilter {	

	    private Integer numeroRuote;

	    private Integer anno;

	    private Integer idMarca;
	    private Integer idModello;
	    private Integer idColore;
	    private Integer idCategoria;
	    private Integer idTipoAlimentazione;
	    private Integer idTipoVeicolo;
}

package com.betacom.car.dto.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class VeicoloFilter {	
		
		private Integer id;
	
	    private Integer numeroRuote;

	    private Integer anno;

	    private Integer idMarca;
	    private String modello;
	    private Integer idColore;
	    private Integer idCategoria;
	    private Integer idTipoAlimentazione;
	    private Integer idTipoVeicolo;
	    
	    private Integer numeroMarce;
	    private Integer idFreno;
	    private Integer idSospensione;
	    private Boolean pieghevole;
	    
		private Integer cc;

	    private Integer numeroPorte;

	    private String targa;
}

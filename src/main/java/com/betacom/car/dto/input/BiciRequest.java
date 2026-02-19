package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BiciRequest extends VeicoloRequest{

	    private Integer numero_marce;
	    private Integer id_tipo_freno;
	    private Integer id_tipo_sospensione;
	    private boolean pieghevole;
	    
}

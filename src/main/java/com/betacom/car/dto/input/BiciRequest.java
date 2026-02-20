package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BiciRequest extends VeicoloRequest{

	    private Integer numeroMarce;
	    private String freno;
	    private String sospensione;
	    private Boolean pieghevole;
	    
}

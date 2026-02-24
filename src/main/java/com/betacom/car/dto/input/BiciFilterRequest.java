package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiciFilterRequest extends VeicoloFilterRequest{

	   private Integer numeroMarce;
	   private String freno;
	   private String sospensione;
	   private Boolean pieghevole;
}

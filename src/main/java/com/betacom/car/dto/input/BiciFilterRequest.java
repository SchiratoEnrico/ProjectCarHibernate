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
public class BiciFilterRequest extends VeicoloFilterRequest{

	   private Integer numeroMarce;
	   private String freno;
	   private String sospensione;
	   private Boolean pieghevole;
}

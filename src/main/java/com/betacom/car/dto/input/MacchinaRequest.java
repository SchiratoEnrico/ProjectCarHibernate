package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MacchinaRequest extends VeicoloRequest{
	
	private Integer numeroPorte;
	private Integer cc;
	private String targa;

}

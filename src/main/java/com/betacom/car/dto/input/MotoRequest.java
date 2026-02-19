package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MotoRequest extends VeicoloRequest {
	
	private Integer cc;
	private int numero_marce;
	private String targa;

}

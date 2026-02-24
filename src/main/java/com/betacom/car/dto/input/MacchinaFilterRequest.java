package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MacchinaFilterRequest extends VeicoloFilterRequest{
	
	private Integer cc;

    private Integer numeroPorte;

    private String targa;
}

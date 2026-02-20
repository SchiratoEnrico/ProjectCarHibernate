package com.betacom.car.dto.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MacchinaFilter extends VeicoloFilter{
	
	private Integer cc;

    private Integer numeroPorte;

    private String targa;
}

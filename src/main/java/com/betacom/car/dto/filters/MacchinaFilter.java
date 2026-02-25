package com.betacom.car.dto.filters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MacchinaFilter extends VeicoloFilter{
	
	private Integer cc;

    private Integer numeroPorte;

    private String targa;
}

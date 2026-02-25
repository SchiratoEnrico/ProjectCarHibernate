package com.betacom.car.dto.filters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MotoFilter extends VeicoloFilter{
	
	private Integer cc;

    private Integer numeroMarce;

    private String targa;
}

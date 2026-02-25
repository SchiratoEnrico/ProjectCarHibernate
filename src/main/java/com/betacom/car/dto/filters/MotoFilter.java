package com.betacom.car.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotoFilter extends VeicoloFilter{
	
	private Integer cc;

    private Integer numeroMarce;

    private String targa;
}

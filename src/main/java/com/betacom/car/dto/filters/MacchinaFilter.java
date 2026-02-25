package com.betacom.car.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MacchinaFilter extends VeicoloFilter{
	
	private Integer cc;

    private Integer numeroPorte;

    private String targa;
}

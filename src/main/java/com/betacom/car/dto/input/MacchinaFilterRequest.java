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
public class MacchinaFilterRequest extends VeicoloFilterRequest{
	
	private Integer cc;

    private Integer numeroPorte;

    private String targa;
}

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
public class BiciFilter extends VeicoloFilter{
	

    private Integer numeroMarce;
    private Integer idFreno;
    private Integer idSospensione;
    private Boolean pieghevole;
}

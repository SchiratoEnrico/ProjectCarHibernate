package com.betacom.car.dto.filters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BiciFilter extends VeicoloFilter{
	

    private Integer numeroMarce;
    private Integer idFreno;
    private Integer idSospensione;
    private Boolean pieghevole;
}

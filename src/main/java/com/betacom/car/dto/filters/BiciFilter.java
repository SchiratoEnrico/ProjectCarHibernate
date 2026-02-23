package com.betacom.car.dto.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BiciFilter extends VeicoloFilter{
	

    private Integer numeroMarce;
    private Integer idFreno;
    private Integer idSospensione;
    private Boolean pieghevole;
}

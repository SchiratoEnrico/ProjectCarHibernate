package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoFilterRequest extends VeicoloFilterRequest{

	private Integer cc;

    private Integer numeroMarce;

    private String targa;
}

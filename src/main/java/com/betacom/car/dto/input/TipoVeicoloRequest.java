package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TipoVeicoloRequest {

	 private Integer id_tipo_veicolo;
	 private String tipo_veicolo;
}

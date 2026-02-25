package com.betacom.car.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BiciclettaDTO extends VeicoloDTO {
	private Integer numeroMarce;
	private TipoFrenoDTO tipoFreno;        //tamburo, disco
	private TipoSospensioneDTO tipoSospensione;  // senza, mono, bi-
	private Boolean pieghevole;

}

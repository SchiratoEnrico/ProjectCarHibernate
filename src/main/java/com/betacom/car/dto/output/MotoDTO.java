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
@Builder
@ToString
public class MotoDTO extends VeicoloDTO {
	private Integer cc;
	private int numeroMarce;
	private String targa;
}

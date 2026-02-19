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
public class MacchinaDTO extends VeicoloDTO {
	private Integer numeroPorte;
	private Integer cc;
	private String targa;
}

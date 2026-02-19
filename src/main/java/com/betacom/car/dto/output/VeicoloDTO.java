package com.betacom.car.dto.output;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class VeicoloDTO {
	private Integer idVeicolo;
	private TipoVeicoloDTO tipoVeicolo;
	private Integer numeroRuote;
	private TipoAlimentazioneDTO tipoAlimentazione;
	private CategoriaDTO categoria;
	private ColoreDTO colore;
	private MarcaDTO marca;
	private LocalDate annoProduzione;
	private String modello;
}

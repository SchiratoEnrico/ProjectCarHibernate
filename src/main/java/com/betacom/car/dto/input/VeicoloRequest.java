package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public abstract class VeicoloRequest {
	
    private Integer id;
    private String colore;
    private String marca;
    private String modello;
    private String alimentazione;
    private String categoria;
    private String tipoVeicolo;         
    private Integer numeroRuote;
    private Integer annoProduzione;
	
}

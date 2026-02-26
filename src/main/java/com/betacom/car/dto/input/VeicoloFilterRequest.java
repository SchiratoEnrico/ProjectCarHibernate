package com.betacom.car.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VeicoloFilterRequest {
	
	 // Veicolo base
    private Integer numeroRuote;
    private Integer anno;
    private String marca;
    private String colore;
    private String categoria;
    private String modello;
    private String tipoAlimentazione;
    private String tipoVeicolo;
    private Integer id;
    
    // Moto
    private String targa;
    private Integer cc;
    private Integer numeroMarce;

    // Macchina
    private Integer numeroPorte;

    // Bicicletta
    private String freno;
    private String sospensione;
    private Boolean pieghevole;

}

package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VeicoloRequest {
	
    private Integer id;
    private Integer id_colore;
    private Integer id_marca;
    private Integer id_modello;
    private Integer id_tipo_alimentazione;
    private Integer id_categoria;
    private Integer id_tipo_veicolo;         
    private Integer numero_ruote;
    private int anno_produzione;
	
}

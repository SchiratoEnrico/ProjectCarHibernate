package com.betacom.car.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.VeicoloFilterRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.IVeicoloServices;
import com.betacom.car.utilities.FilterTranslator;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/veicolo")
public class VeicoloController {
	private final IVeicoloServices veiS;
	private final IMessagesServices msgS;
	private final FilterTranslator filT;
	
	@GetMapping ("/list")
	private ResponseEntity<Object> list(){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
            r = veiS.findAll();
        } catch (VeicoloException e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam(required = true) Integer id) {
		Object r = new Object();
        HttpStatus status = HttpStatus.OK;

        try {
            r = veiS.findById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
	
	@GetMapping("/filter")
	public ResponseEntity<Object> filter(
	        @RequestParam(required = false) Integer numeroRuote,
	        @RequestParam(required = false) Integer anno,
	        @RequestParam(required = false) String marca,
	        @RequestParam(required = false) String colore,
	        @RequestParam(required = false) String categoria,
	        @RequestParam(required = false) String modello,
	        @RequestParam(required = false) String tipoAlimentazione,
	        @RequestParam(required = false) String tipoVeicolo,
	        @RequestParam(required = false) String freno,
	        @RequestParam(required = false) String sospensione,
	        @RequestParam(required = false) Integer numeroMarce,
	        @RequestParam(required = false) Boolean pieghevole
	) {
	    Object r = new Object();
	    HttpStatus status = HttpStatus.OK;

	    try {
	    	VeicoloFilterRequest filter = VeicoloFilterRequest.builder()
	        							.numeroRuote(numeroRuote)
	        							.anno(anno)
	        							.colore(Utils.formatStringParam(colore))
	        							.categoria(Utils.formatStringParam(categoria))
	        							.marca(Utils.formatStringParam(marca))
	        							.tipoAlimentazione(Utils.formatStringParam(tipoAlimentazione))
	        							.tipoVeicolo(Utils.formatStringParam(tipoVeicolo))
	        							.modello(Utils.formatStringParam(modello))
	        							.build();

	    	VeicoloFilter myF = new VeicoloFilter();
	        r = veiS.filter(filT.toVeicoloFilter(myF, filter));
	    } catch (VeicoloException e) {
	        r = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }
	    return ResponseEntity.status(status).body(r);
	}
}

package com.betacom.car.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.car.dto.input.BiciRequest;
import com.betacom.car.dto.input.VeicoloFilterRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.response.Resp;
import com.betacom.car.services.interfaces.IBiciclettaServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.utilities.FilterTranslator;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/bicicletta")
public class BiciclettaController {
	private final IBiciclettaServices biciS;
	private final IMessagesServices msgS;
	private final FilterTranslator filT;
	private final String tipoVeicolo = "BICI";
	
	
	@PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) BiciRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            Integer id = biciS.create(req);
            r.setMsg(msgS.get("rest_created")  + " con id: " + id);
        } catch (VeicoloException e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Resp> delete(@PathVariable(required = true) Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            biciS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (VeicoloException e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

	@PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) BiciRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            biciS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (VeicoloException e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

	@GetMapping ("/list")
	private ResponseEntity<Object> list(){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
            r = biciS.findAll();
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
            r = biciS.findById(id);
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
	        							.freno(Utils.formatStringParam(freno))
	        							.numeroMarce(numeroMarce)
	        							.pieghevole(pieghevole)
	        							.sospensione(Utils.formatStringParam(sospensione))
	        							.modello(Utils.formatStringParam(modello))
	        							.build();

	        r = biciS.filter(filT.toVeicoloFilter(filter));
	    } catch (VeicoloException e) {
	        r = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }
	    return ResponseEntity.status(status).body(r);
	}
}

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

import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.dto.input.MacchinaRequest;
import com.betacom.car.response.Resp;
import com.betacom.car.services.interfaces.IMacchinaServices;
import com.betacom.car.services.interfaces.IMessagesServices;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/macchina")
public class MacchinaController {
	
	private final IMacchinaServices macchinaS;
	private final IMessagesServices msgS;
	



	@PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) MacchinaRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            Integer id = macchinaS.create(req);
            r.setMsg(msgS.get("rest_created") + " con id: " + id);
        } catch (Exception e) {
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
            macchinaS.delete(id);
            r.setMsg(msgS.get("rest_delited"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
	
	@PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) MacchinaRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            macchinaS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
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
            r = macchinaS.findAll();
        } catch (Exception e) {
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
            r = macchinaS.findById(id);
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
	        @RequestParam(required = false) Integer idMarca,
	        @RequestParam(required = false) Integer idColore,
	        @RequestParam(required = false) Integer idCategoria,
	        @RequestParam(required = false) Integer idTipoAlimentazione,
	        @RequestParam(required = false) Integer idTipoVeicolo,
	        // campi specifici di MacchinaFilter
	        @RequestParam(required = false) String targa,
	        @RequestParam(required = false) Integer numeroPorte,
	        @RequestParam(required = false) Integer cc
	) {
	    Object r = new Object();
	    HttpStatus status = HttpStatus.OK;

	    try {
	        MacchinaFilter filter = new MacchinaFilter();
	        // campi ereditati da VeicoloFilter
	        filter.setNumeroRuote(numeroRuote);
	        filter.setAnno(anno);
	        filter.setIdMarca(idMarca);
	        filter.setIdColore(idColore);
	        filter.setIdCategoria(idCategoria);
	        filter.setIdTipoAlimentazione(idTipoAlimentazione);
	        filter.setIdTipoVeicolo(idTipoVeicolo);
	        // campi specifici macchina
	        filter.setTarga(targa);
	        filter.setNumeroPorte(numeroPorte);
	        filter.setCc(cc);

	        r = macchinaS.filter(filter);
	    } catch (Exception e) {
	        r = e.getMessage();
	        status = HttpStatus.BAD_REQUEST;
	    }
	    return ResponseEntity.status(status).body(r);
	}
	
}

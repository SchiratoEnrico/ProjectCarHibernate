package com.betacom.car.controllers;
import com.betacom.car.response.Resp;
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
import com.betacom.car.dto.filters.MotoFilter;
import com.betacom.car.dto.input.MacchinaFilterRequest;
import com.betacom.car.dto.input.MotoFilterRequest;
import com.betacom.car.dto.input.MotoRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.IMotoServices;
import com.betacom.car.utilities.FilterTranslator;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/moto")
public class MotoController {

	private final FilterTranslator filtT;
	private final IMotoServices motS;
	private final IMessagesServices msgS;
	
	@PostMapping("/create")
	public ResponseEntity<Resp> create(@RequestBody(required=true) MotoRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			Integer id = motS.create(req);
			r.setMsg(msgS.get("rest_created") + " con id: " + id);
		}
		catch(VeicoloException e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Resp> update(@RequestBody(required=true) MotoRequest req){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			motS.update(req);
			r.setMsg(msgS.get("rest_updated"));
		}
		catch(VeicoloException e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Resp> delete(@PathVariable(required=true) Integer id){
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			motS.delete(id);
			r.setMsg(msgS.get("rest_deleted"));
		} catch (VeicoloException e) {
			r.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);		
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> list(){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= motS.findAll();
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Object> findById (@RequestParam (required = true)  Integer id){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r= motS.findById(id);
		} catch (Exception e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	//filter
	@GetMapping("/filter")
	public ResponseEntity<Object> filter(
			@RequestParam(required=false) Integer id,
			@RequestParam(required=false) Integer cc,
			@RequestParam(required=false) Integer numeroMarce,
			@RequestParam(required=false) Integer numeroRuote,
			@RequestParam(required=false) String targa,
			@RequestParam(required=false) String colore,
			@RequestParam(required=false) String marca,
			@RequestParam(required=false) String tipoA,
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) Integer annoProduzione,
			@RequestParam(required=false) String modello,
			@RequestParam(required=false) String tipoVei
			){
		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			MotoFilter filter = filtT.toMotoFilter(MotoFilterRequest.builder()
					.id(id)
					.cc(cc)
					.numeroMarce(numeroMarce)
					.targa(Utils.formatStringParam(targa))
					.colore(Utils.formatStringParam(colore))
					.marca(Utils.formatStringParam(marca))
					.tipoAlimentazione(Utils.formatStringParam(tipoA))
					.categoria(Utils.formatStringParam(cat))
					.anno(annoProduzione)
					.tipoVeicolo(Utils.formatStringParam(tipoVei))
					.modello(Utils.formatStringParam(modello))
					.numeroRuote(numeroRuote)
	        		.build());
	   
	        r = motS.filter(filter);
		}
		catch(VeicoloException e) {
			r=e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}

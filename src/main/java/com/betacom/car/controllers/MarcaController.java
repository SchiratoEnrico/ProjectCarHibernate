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
import org.springframework.web.bind.annotation.RestController;

import com.betacom.car.dto.input.MarcaRequest;
import com.betacom.car.response.Resp;
import com.betacom.car.services.interfaces.IMarcaServices;
import com.betacom.car.services.interfaces.IMessagesServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/marca")
public class MarcaController {
	private final IMarcaServices marcaS;
    private final IMessagesServices msgS;
    
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
    	Object r = new Object();
        HttpStatus status = HttpStatus.OK;

        try {
            r = marcaS.list();
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) MarcaRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
        	marcaS.create(req);
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
        	marcaS.delete(id);
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) MarcaRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
        	marcaS.update(req);
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

}

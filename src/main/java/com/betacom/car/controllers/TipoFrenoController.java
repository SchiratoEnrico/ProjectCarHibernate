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

import com.betacom.car.dto.input.TipoFrenoRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.response.Resp;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.ITipoFrenoServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/TipoFreno")
public class TipoFrenoController {

	private final ITipoFrenoServices frenoS;
    private final IMessagesServices msgS;
    
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
    	Object r = new Object();
        HttpStatus status = HttpStatus.OK;

        try {
            r = frenoS.list();
        } catch (VeicoloException e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) TipoFrenoRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            frenoS.create(req);
            r.setMsg(msgS.get("rest_created"));
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
            frenoS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (VeicoloException e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) TipoFrenoRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
        	frenoS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (VeicoloException e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
}


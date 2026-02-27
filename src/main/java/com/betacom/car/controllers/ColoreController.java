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

import com.betacom.car.dto.input.ColoreRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.response.Resp;
import com.betacom.car.services.interfaces.IColoreServices;
import com.betacom.car.services.interfaces.IMessagesServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/colore")
public class ColoreController {

	private final IColoreServices coloreS;
    private final IMessagesServices msgS;
    
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
    	Object r = new Object();
        HttpStatus status = HttpStatus.OK;

        try {
            r = coloreS.list();
        } catch (VeicoloException e) {
            r = msgS.get(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody(required = true) ColoreRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            coloreS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (VeicoloException e) {
            r.setMsg(msgS.get(e.getMessage()));
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Resp> delete(@PathVariable(required = true) Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
            coloreS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (VeicoloException e) {
            r.setMsg(msgS.get(e.getMessage()));
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody(required = true) ColoreRequest req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        try {
        	coloreS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (VeicoloException e) {
            r.setMsg(msgS.get(e.getMessage()));
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
    
}


package com.betacom.car.services.implementations;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.MotoRequest;
import com.betacom.car.dto.output.MotoDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicolo;
import com.betacom.car.repositories.IMotoRepository;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.IMotoServices;
import com.betacom.car.specifications.VeicoloSpecs;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MotoImplementation implements IMotoServices{

	private final Utils utils;
	private final IMessagesServices msgS;
	private final IMotoRepository repM;
	
	@Transactional (rollbackFor = VeicoloException.class)
	@Override
	public Integer create(MotoRequest req) throws VeicoloException {
		log.debug("create {}", req);
		
		Moto mot = new Moto();
		
		mot = checkReqMoto(req, mot);
		
		Utils.validateTarga(mot);
		
		if (repM.findByTarga(mot.getTarga()).isPresent())
            throw new VeicoloException(msgS.get("exists_tar"));
		
		int id = repM.save(mot).getId();
		return id;
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete {}", id);
		
		Moto mot = repM.findById(id)
				.orElseThrow(() -> new VeicoloException(msgS.get("!exists_mot")));
		
		repM.delete(mot);
	}

	@Override
	public void update(MotoRequest req) throws VeicoloException {
		log.debug("create {}", req);
		
		
		Moto mot = repM.findById(req.getId()).orElseThrow(()-> new VeicoloException(msgS.get("!exists_mot")));
		
		mot = optReqMoto(req, mot);
		
		Utils.validateTarga(mot);
		
		repM.save(mot);

	}

	@Override
	public List<MotoDTO> findAll() throws VeicoloException {
		log.debug("findAll");
		List<Moto> lM = repM.findAll();
		
		return lM.stream()
                .map(m -> Utils.buildMotoDTO(m))
                		.toList();
	}

	@Override
	public MotoDTO findById(Integer id) throws VeicoloException {
		log.debug("findById: {}", id);
		Moto m = repM.findById(id)
				.orElseThrow(() -> new VeicoloException(msgS.get("!exists_mot")));
		
		return Utils.buildMotoDTO(m);
	}


	@Override
	public List<MotoDTO> filter(VeicoloFilter filter) {
		Specification<Veicolo> spec = VeicoloSpecs.withFilter(filter);
		
		List<Veicolo> entities = repM.findAll(spec);
		
		return entities.stream()
				.map(m->Utils.buildMotoDTO((Moto)m))
				.toList();
	}
	
	
	public Moto checkReqMoto(MotoRequest req, Moto m) throws VeicoloException {

        // richiama il checkReq del veicolo per i campi comuni
	    //log.debug("prima chekc req veicolo");
        utils.checkReq(req, m);
        //log.debug("dopo chekc req veicolo");
        if (req.getCc() != null)
            m.setCc(req.getCc());
        else
            throw new VeicoloException(msgS.get("null_ccc"));

        if (req.getNumeroMarce() != null)
            m.setNumeroMarce(req.getNumeroMarce());
        else
            throw new VeicoloException(msgS.get("null_mar"));

        if (req.getTarga() != null && !req.getTarga().isBlank())
            m.setTarga(req.getTarga().trim().toUpperCase());
        else
            throw new VeicoloException(msgS.get("null_tar"));

        return m;
    }
 
 
	public Moto optReqMoto(MotoRequest req, Moto m) throws VeicoloException {

        // richiama il checkReq del veicolo per i campi comuni
        utils.optReq(req, m);

        if (req.getCc() != null)
            m.setCc(req.getCc());


        if (req.getNumeroMarce() != null)
            m.setNumeroMarce(req.getNumeroMarce());


        if (req.getTarga() != null && !req.getTarga().isBlank())
            m.setTarga(req.getTarga().trim().toUpperCase());

        return m;
    }

}

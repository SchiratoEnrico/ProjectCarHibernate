package com.betacom.car.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.MacchinaRequest;
import com.betacom.car.dto.output.MacchinaDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Veicolo;
import com.betacom.car.repositories.IMacchinaRepository;
import com.betacom.car.services.interfaces.IMacchinaServices;
import com.betacom.car.specifications.VeicoloSpecs;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MacchinaImplementation implements IMacchinaServices{

	//autowired grazie a lombok
	private final IMacchinaRepository repM;
    private final Utils utils;
	
	@Override
	public Integer create(MacchinaRequest req) throws VeicoloException {
		log.debug("create {}", req);
				
        Macchina m = new Macchina();
        m = checkReqMacc(req, m);
        
        // controllo se targa presente
        if (repM.findByTarga(m.getTarga()).isPresent()) {
        	throw new VeicoloException("exists_tar");
    	}
        
        int id = repM.save(m).getId();
        log.debug("macchina creata con id: {}", id);
        return id;
    }
	

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete macchina con id: {}", id);

        Macchina m = repM.findById(id)
                .orElseThrow(() -> new VeicoloException("!exists_mac"));

        repM.delete(m);
		
	}

	@Override
	public void update(MacchinaRequest req) throws VeicoloException {
		log.debug("update {}", req);
				
        Macchina m = repM.findById(req.getId())
                .orElseThrow(() -> new VeicoloException("!exists_mac"));
        
        m = optReqMacc(req, m);
       
        repM.save(m);	
	}
	
	@Override
	public List<MacchinaDTO> findAll() throws VeicoloException {
		log.debug("findAll()");

        return repM.findAll()
                .stream()
                .map(Utils::buildMacchinaDTO)
                .toList();
	}

	@Override
	public MacchinaDTO findById(Integer id) throws VeicoloException {
		log.debug("findById: {}", id);

        Macchina m = repM.findById(id)
                .orElseThrow(() -> new VeicoloException("!exists_mac"));

        return Utils.buildMacchinaDTO(m);
	}

	@Override
	public List<MacchinaDTO> filter(VeicoloFilter filter) {
		
		//faccio le specification col filter
		Specification<Veicolo> spec = VeicoloSpecs.withFilter(filter);
		
		List<Veicolo> entities = repM.findAll(spec);
		
	    return  entities.stream()
				.map(e -> Utils.buildMacchinaDTO((Macchina)e))
				.collect(Collectors.toList());
	}
	
	

	 public Macchina checkReqMacc(MacchinaRequest req, Macchina m) throws VeicoloException {

	        // richiama il checkReq del veicolo per i campi comuni
	        utils.checkReq(req, m);

	        if (req.getCc() != null)
	            m.setCc(req.getCc());
	        else
	            throw new VeicoloException("null_ccc");

	        if (req.getNumeroPorte() != null)
	            m.setNumeroPorte(req.getNumeroPorte());
	        else
	            throw new VeicoloException("null_por");

	        if (req.getTarga() != null && !req.getTarga().isBlank()) {
	            m.setTarga(req.getTarga().trim().toUpperCase());
	            Utils.validateTarga(m);
	        } 
	        else
	            throw new VeicoloException("!valid_tar");

	        return m;
	    }
	 
	 
	 public Macchina optReqMacc(MacchinaRequest req, Macchina m) throws VeicoloException {

	        // richiama il checkReq del veicolo per i campi comuni
	        utils.optReq(req, m);

	        if (req.getCc() != null)
	            m.setCc(req.getCc());


	        if (req.getNumeroPorte() != null)
	            m.setNumeroPorte(req.getNumeroPorte());


	        if (req.getTarga() != null && !req.getTarga().isBlank()) {
	            m.setTarga(req.getTarga().trim().toUpperCase());
	            Utils.validateTarga(m);
	        }
	        
	        return m;
	    }
}

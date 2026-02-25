package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.ColoreRequest;
import com.betacom.car.dto.output.ColoreDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Colore;
import com.betacom.car.repositories.IColoreRepository;
import com.betacom.car.services.interfaces.IColoreServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColoreImplementation implements IColoreServices{

	private final IColoreRepository repC;
	private final IMessagesServices msgS;
	
	@Override
	public void create(ColoreRequest req) throws VeicoloException {
		log.debug("create {}", req);

        if (req.getColore() == null || req.getColore().isBlank())
            throw new VeicoloException(msgS.get("null_col"));

        String myC = req.getColore().trim().toUpperCase();
        if (repC.findByColore(myC).isPresent())
            throw new VeicoloException(msgS.get("dup_col"));

        Colore c = new Colore();
        c.setColore(myC);
        repC.save(c);
    }
		

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete colore con id: {}", id);

		Colore c = repC.findById(id)
                .orElseThrow(() -> new VeicoloException(msgS.get("null_col")));

        repC.delete(c);
		
	}

	@Override
	public List<ColoreDTO> list() {
		 log.debug("colore list()");

	        return repC.findAll()
	                .stream()
	                .map(c -> Utils.buildColoreDTO(c))
	                .toList();
	}

	@Override
	public void update(ColoreRequest req) throws VeicoloException {
		 log.debug("colore update {}", req);

		Colore c = repC.findById(req.getId())
                .orElseThrow(() -> new VeicoloException(msgS.get("null_cid")));
		if ((req.getColore() != null) && (!req.getColore().isEmpty())) {
			String myC = req.getColore().trim().toUpperCase();
			Optional<Colore> c2 = repC.findByColore(myC);
			if (c2.isEmpty()) {
				c.setColore(myC);
				repC.save(c);
			} else {
				throw new VeicoloException(msgS.get("dup_col"));
			}
		} else {
			throw new VeicoloException(msgS.get("null_col"));
		}
        repC.save(c);	
	}

}

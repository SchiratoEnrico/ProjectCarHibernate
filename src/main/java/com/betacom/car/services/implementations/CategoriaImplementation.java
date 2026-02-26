package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.CategoriaRequest;
import com.betacom.car.dto.output.CategoriaDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Categoria;
import com.betacom.car.repositories.ICategoriaRepository;
import com.betacom.car.services.interfaces.ICategoriaServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaImplementation implements ICategoriaServices{

	private final ICategoriaRepository repC;
	private final IMessagesServices msgS;
	
	@Override
	public void create(CategoriaRequest req) throws VeicoloException {
		log.debug("create {}", req);

        if (req.getCategoria() == null || req.getCategoria().isBlank())
            throw new VeicoloException(msgS.get("null_cat"));

        String cat = req.getCategoria().trim().toUpperCase();
        if (repC.findByCategoria(cat).isPresent())
            throw new VeicoloException(msgS.get("exists_cat"));

        Categoria c = new Categoria();
        c.setCategoria(cat);

        repC.save(c);
    }
		

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete categoria con id: {}", id);

        Categoria c = repC.findById(id)
                .orElseThrow(() -> new VeicoloException(msgS.get("null_cat_id")));

        repC.delete(c);
		
	}

	@Override
	public List<CategoriaDTO> list() {
		 log.debug("list()");

	        return repC.findAll()
	                .stream()
	                .map(Utils::buildCategoriaDTO)
	                .toList();
	}


	@Override
	public void update(CategoriaRequest req) throws VeicoloException {
		log.debug("update Categoria {}", req);
		Categoria c = repC.findById(req.getId())
				.orElseThrow(() -> new VeicoloException(msgS.get("null_cat_id")));

		if ((req.getCategoria() != null) && (!req.getCategoria().isBlank())) {
			String myT = req.getCategoria().trim().toUpperCase();
			Optional<Categoria> t = repC.findByCategoria(myT);
			if (t.isEmpty()) {
				c.setCategoria(myT);
				repC.save(c);			}
			else {
				throw new VeicoloException("null_cat");
			}
		}
	}

}

package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoSospensioneRequest;
import com.betacom.car.dto.output.TipoSospensioneDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoSospensione;
import com.betacom.car.repositories.ITipoSospensioneRepository;
import com.betacom.car.services.interfaces.ITipoSospensioneServices;
import com.betacom.car.utilities.Utils;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class TipoSospensioneImplementation implements ITipoSospensioneServices{
	private final ITipoSospensioneRepository repT;

	@Override
    @Transactional (rollbackFor = Exception.class)
	public void create(TipoSospensioneRequest req) throws VeicoloException {
		log.debug("creating TipoSospensione {}", req);

		TipoSospensione t = new TipoSospensione();
		if ((req.getTipoSospensione() != null) && (!req.getTipoSospensione().isBlank())) {
			String myT = req.getTipoSospensione().trim().toUpperCase();
			Optional<TipoSospensione> t2 = repT.findByTipoSospensione(myT);
			if (t2.isEmpty()) {
				t.setTipoSospensione(myT);
				repT.save(t);
			} else {
				throw new VeicoloException("exists_sos");
			}
		} else {
			throw new VeicoloException("null_sos");
		}
	}

	@Override
    @Transactional (rollbackFor= Exception.class)
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete TipoSospensione with ID {}", id);
		TipoSospensione t = repT.findById(id).orElseThrow(() ->
							new VeicoloException("!exists_sos"));
		repT.delete(t);
	}

	@Override
    @Transactional (rollbackFor= Exception.class)
	public void update(TipoSospensioneRequest req) throws VeicoloException {
		log.debug("modifying TipoSospensione {}", req);
		TipoSospensione c = repT.findById(req.getId()).orElseThrow(() ->
							new VeicoloException("!exists_sos"));
		
		if ((req.getTipoSospensione() == null) && (req.getTipoSospensione().isBlank())) {
			throw new VeicoloException("null_cat");
		}
		String myT = req.getTipoSospensione().trim().toUpperCase();
		Optional<TipoSospensione> t = repT.findByTipoSospensione(myT);
		if (t.isEmpty()) {
			c.setTipoSospensione(myT);
			repT.save(c);			
		}
		else {
			throw new VeicoloException("exists_cat");
		}
	}

	@Override
	public List<TipoSospensioneDTO> list() {
		log.debug("list TipoSospensione");
		List<TipoSospensione> lS = repT.findAll();
		return Utils.buildTipoSospensioneDTOs(lS);
	}
}

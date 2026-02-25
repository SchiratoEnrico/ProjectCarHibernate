package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoSospensioneRequest;
import com.betacom.car.dto.output.TipoSospensioneDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoSospensione;
import com.betacom.car.repositories.ITipoSospensioneRepository;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.ITipoSospensioneServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class TipoSospensioneImplementation implements ITipoSospensioneServices{
	private final IMessagesServices msgS;
	private final ITipoSospensioneRepository repT;

	@Override
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
				throw new VeicoloException("dup_sos");
			}
		} else {
			throw new VeicoloException("null_sos");
		}
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete TipoSospensione with ID {}", id);
		TipoSospensione t = repT.findById(id).orElseThrow(() ->
							new VeicoloException(msgS.get("null_sid")));
		repT.delete(t);
	}

	@Override
	public void update(TipoSospensioneRequest req) throws VeicoloException {
		log.debug("modifying TipoSospensione {}", req);
		TipoSospensione t = repT.findById(req.getId()).orElseThrow(() ->
							new VeicoloException(msgS.get("null_sid")));
		
		if ((req.getTipoSospensione() != null) && (!req.getTipoSospensione().isBlank())) {
			String myT = req.getTipoSospensione().trim().toUpperCase();
			Optional<TipoSospensione> t2 = repT.findByTipoSospensione(myT);
			if (t2.isEmpty()) {
				t.setTipoSospensione(myT);
				repT.save(t);			} 
			else {
				throw new VeicoloException("dup_sos");
			}
		}
	}

	@Override
	public List<TipoSospensioneDTO> list() {
		List<TipoSospensione> lS = repT.findAll();
		return Utils.buildTipoSospensioneDTOs(lS);
	}
}

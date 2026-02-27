package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoVeicoloRequest;
import com.betacom.car.dto.output.TipoVeicoloDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoVeicolo;
import com.betacom.car.repositories.ITipoVeicoloRepository;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.ITipoVeicoloServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class TipoVeicoloImplementation implements ITipoVeicoloServices{
	private final IMessagesServices msgS;
	private final ITipoVeicoloRepository repT;
	
	@Override
	public void create(TipoVeicoloRequest req) throws VeicoloException {
		log.debug("creating TipoVeicolo {}", req);

		TipoVeicolo t = new TipoVeicolo();
		if ((req.getTipoVeicolo() == null) && (req.getTipoVeicolo().isBlank())) {
			throw new VeicoloException("null_vei");
		}
		String myT = req.getTipoVeicolo().trim().toUpperCase();
		Optional<TipoVeicolo> t2 = repT.findByTipoVeicolo(myT);
		if (t2.isEmpty()) {
			t.setTipoVeicolo(myT);
			repT.save(t);
		} else {
			throw new VeicoloException("exists_vei");
		}
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("removing TipoVeicolo with ID {}", id);

		TipoVeicolo t = repT.findById(id).orElseThrow(() ->
							new VeicoloException(msgS.get("!exists_vei")));
		repT.delete(t);
		
	}

	@Override
	public void update(TipoVeicoloRequest req) throws VeicoloException {
		log.debug("modifying TipoVeicolo {}", req);
		TipoVeicolo t = repT.findById(req.getId()).orElseThrow(() ->
								new VeicoloException(msgS.get("!exists_vei")));

		if ((req.getTipoVeicolo() == null) && (req.getTipoVeicolo().isBlank())) {
			throw new VeicoloException("null_cat");
		}
		String myT = req.getTipoVeicolo().trim().toUpperCase();
		Optional<TipoVeicolo> t2 = repT.findByTipoVeicolo(myT);
		if (t2.isEmpty()) {
			t.setTipoVeicolo(myT);
			repT.save(t);			
		}
		else {
			throw new VeicoloException("exists_cat");
		}
	}

	@Override
	public List<TipoVeicoloDTO> list() {
		List<TipoVeicolo> lT = repT.findAll();
		return Utils.buildTipoVeicoloDTOs(lT);
	}

}

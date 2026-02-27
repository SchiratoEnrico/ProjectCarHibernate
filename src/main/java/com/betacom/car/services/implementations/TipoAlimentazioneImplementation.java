package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoAlimentazioneRequest;
import com.betacom.car.dto.output.TipoAlimentazioneDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoAlimentazione;
import com.betacom.car.repositories.ITipoAlimentazioneRepository;
import com.betacom.car.services.interfaces.ITipoAlimentazioneServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TipoAlimentazioneImplementation implements ITipoAlimentazioneServices{

	private final ITipoAlimentazioneRepository repTA;
	
	@Override
	public void create(TipoAlimentazioneRequest req) throws VeicoloException {
		log.debug("create {}", req);
		
		if(req.getTipoAlimentazione() == null || req.getTipoAlimentazione().isBlank())
			throw new VeicoloException("null_ali");
		String a = req.getTipoAlimentazione().trim().toUpperCase();
		if(repTA.findByTipoAlimentazione(a).isPresent())
			throw new VeicoloException("exists_ali");
		
		TipoAlimentazione tA = new TipoAlimentazione();
		tA.setTipoAlimentazione(a);
		repTA.save(tA);
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete tipo alimentazione con id: {}", id);

		TipoAlimentazione tA = repTA.findById(id)
                .orElseThrow(() -> new VeicoloException("!exists_ali"));

        repTA.delete(tA);
	}

	@Override
	public List<TipoAlimentazioneDTO> list() {
		log.debug("list()");

        return repTA.findAll()
                .stream()
                .map(Utils::buildTipoAlimentazioneDTO)
                .toList();
	}

	@Override
	public void update(TipoAlimentazioneRequest req) throws VeicoloException {
		log.debug("update Categoria {}", req);
		TipoAlimentazione tA = repTA.findById(req.getId())
				.orElseThrow(() -> new VeicoloException("!exists_ali"));

		if ((req.getTipoAlimentazione() == null) && (req.getTipoAlimentazione().isBlank())) {
			throw new VeicoloException("null_ali");
		}
		
		String myT = req.getTipoAlimentazione().trim().toUpperCase();
		Optional<TipoAlimentazione> t = repTA.findByTipoAlimentazione(myT);
		if (t.isEmpty()) {
			tA.setTipoAlimentazione(myT);
			repTA.save(tA);			
		}
		else {
			throw new VeicoloException("exists_ali");
		}
	}
}

package com.betacom.car.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoAlimentazioneRequest;
import com.betacom.car.dto.output.TipoAlimentazioneDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoAlimentazione;
import com.betacom.car.repositories.ITipoAlimentazioneRepository;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.ITipoAlimentazioneServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TipoAlimentazioneImplementation implements ITipoAlimentazioneServices{

	private final ITipoAlimentazioneRepository repTA;
	private final IMessagesServices msgS;
	
	@Override
	public void create(TipoAlimentazioneRequest req) throws VeicoloException {
		log.debug("create {}", req);
		
		if(req.getTipoAlimentazione() == null || req.getTipoAlimentazione().isBlank())
			throw new VeicoloException(msgS.get("null_tipAl"));
		
		if(repTA.findByTipoAlimentazione(req.getTipoAlimentazione()).isPresent())
			throw new VeicoloException(msgS.get("exists_tipAl"));
		
		TipoAlimentazione tA = new TipoAlimentazione();
		tA.setTipoAlimentazione(req.getTipoAlimentazione().toUpperCase());
	
		repTA.save(tA);
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete tipo alimentazione con id: {}", id);

		TipoAlimentazione tA = repTA.findById(id)
                .orElseThrow(() -> new VeicoloException(msgS.get("null_cat")));

        repTA.delete(tA);
	}

	@Override
	public List<TipoAlimentazioneDTO> list() {
		log.debug("list()");

        return repTA.findAll()
                .stream()
                .map(a -> TipoAlimentazioneDTO.builder()
                        .id(a.getId())
                        .tipoAlimentazione(a.getTipoAlimentazione())
                        .build())
                .toList();
	}

}

package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.TipoFrenoRequest;
import com.betacom.car.dto.output.TipoFrenoDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.TipoFreno;
import com.betacom.car.repositories.ITipoFrenoRepository;
import com.betacom.car.services.interfaces.ITipoFrenoServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TipoFrenoImplementation implements ITipoFrenoServices{

	private final ITipoFrenoRepository repTA;
	
	@Override
	public void create(TipoFrenoRequest req) throws VeicoloException {
		log.debug("create {}", req);
		
		if(req.getTipoFreno() == null || req.getTipoFreno().isBlank())
			throw new VeicoloException("null_fre");
		String t = req.getTipoFreno();
		if(repTA.findByTipoFreno(t).isPresent())
			throw new VeicoloException("exists_fre");
		
		TipoFreno tA = new TipoFreno();
		tA.setTipoFreno(t);
		repTA.save(tA);
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete tipo Freno con id: {}", id);

		TipoFreno tA = repTA.findById(id)
                .orElseThrow(() -> new VeicoloException("!exists_fre"));

        repTA.delete(tA);
	}

	@Override
	public List<TipoFrenoDTO> list() {
		log.debug("list()");

        return repTA.findAll()
                .stream()
                .map(a -> TipoFrenoDTO.builder()
                        .id(a.getId())
                        .tipoFreno(a.getTipoFreno())
                        .build())
                .toList();
	}

	@Override
	public void update(TipoFrenoRequest req) throws VeicoloException {
		TipoFreno t = repTA.findById(req.getId())
                .orElseThrow(() -> new VeicoloException("!exists_fre"));
		
		if ((req.getTipoFreno() == null) && (req.getTipoFreno().isBlank())) {
			throw new VeicoloException("null_fre");
		}
		String myT = req.getTipoFreno().trim().toUpperCase();
		Optional<TipoFreno> t2 = repTA.findByTipoFreno(myT);
		if (t2.isEmpty()) {
			t.setTipoFreno(myT);
			repTA.save(t);			
		}
		else {
			throw new VeicoloException("exists_fre");
		}
	}
}

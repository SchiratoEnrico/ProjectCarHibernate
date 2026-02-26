package com.betacom.car.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.car.dto.filters.BiciFilter;
import com.betacom.car.dto.input.BiciRequest;
import com.betacom.car.dto.output.BiciclettaDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.TipoFreno;
import com.betacom.car.models.TipoSospensione;
import com.betacom.car.repositories.IBiciclettaRepository;
import com.betacom.car.repositories.ITipoFrenoRepository;
import com.betacom.car.repositories.ITipoSospensioneRepository;
import com.betacom.car.services.interfaces.IBiciclettaServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.specifications.BiciSpecs;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BiciclettaImplementation implements IBiciclettaServices{

	private final IBiciclettaRepository repB;
	private final ITipoFrenoRepository repFre;
	private final ITipoSospensioneRepository repSos;
	private final IMessagesServices msgS;
	private final Utils ut;
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public Integer create(BiciRequest req) throws VeicoloException {
		log.debug("creating Bicicletta {}", req);

		Bicicletta b = checkReq(req);

		Integer id = repB.save(b).getId();
		return id;
	}

	@Override
	@Transactional (rollbackFor = Exception.class)
	public void delete(Integer id) throws VeicoloException {
		log.debug("removing Bicicletta with ID {}", id);

		Bicicletta b = repB.findById(id).orElseThrow(() ->
			new VeicoloException(msgS.get("!exists_bic")));
		repB.delete(b);
	}

	@Override
	@Transactional (rollbackFor = Exception.class)
	public void update(BiciRequest req) throws VeicoloException {
		log.debug("modifying Bicicletta {}", req);
		Bicicletta b = optReq(req);
		repB.save(b);
	}

	@Override
	public List<BiciclettaDTO> findAll() throws VeicoloException {
		log.debug("find all Bicicletta");

		List<Bicicletta> lB = repB.findAll();
		return lB.stream()
				 .map(b -> Utils.buildBiciclettaDTO(b))
				 .collect(Collectors.toList());
	}

	@Override
	public BiciclettaDTO findById(Integer id) throws VeicoloException {
		log.debug("find Bicicletta with ID {}", id);
		Bicicletta b = repB.findById(id).orElseThrow(() ->
							new VeicoloException("!exists_bic"));
		return Utils.buildBiciclettaDTO(b);
	}

	@Override
	public List<BiciclettaDTO> filter(BiciFilter filter) {
			
			//faccio le specification col filter
			Specification<Bicicletta> spec = BiciSpecs.withFilter(filter);

			//faccio il find applicando i filtri
		    List<Bicicletta> entities = repB.findAll(spec);
			
			return entities.stream()
							.map(e -> Utils.buildBiciclettaDTO(e))
							.collect(Collectors.toList());
		}

	private Bicicletta checkReq(BiciRequest breq) throws VeicoloException {
		Bicicletta b = new Bicicletta();
		ut.checkReq(breq, b);
		
		if (breq.getNumeroMarce() != null) {
			b.setNumeroMarce(breq.getNumeroMarce());
		} else {
			throw new VeicoloException(msgS.get("null_nmar"));
		}
		
		if ((breq.getFreno()) != null && (!breq.getFreno().isEmpty())) {
			String f = breq.getFreno().trim().toUpperCase();
			TipoFreno tF = repFre.findByTipoFreno(f).orElseThrow(() ->
							new VeicoloException(msgS.get("null_fre")));
			b.setFreno(tF);
		} else {
			throw new VeicoloException(msgS.get("null_fre"));
		}
		
		if ((breq.getSospensione()) != null && (!breq.getSospensione().isEmpty())) {
			String s = breq.getSospensione().trim().toUpperCase();
			TipoSospensione tS = repSos.findByTipoSospensione(s).orElseThrow(() ->
							new VeicoloException(msgS.get("null_sos")));
			b.setSospensione(tS);
		} else {
			throw new VeicoloException(msgS.get("null_sos"));
		}
		
		if ((breq.getPieghevole()) != null) {
			b.setPieghevole(breq.getPieghevole());
		} else {
			throw new VeicoloException(msgS.get("null_pie"));
		}
		return b;
	}

	private Bicicletta optReq(BiciRequest breq) throws VeicoloException {
		Bicicletta b = repB.findById(breq.getId()).orElseThrow(() ->
						new VeicoloException("null_bid"));
		
		b = (Bicicletta) ut.optReq(breq, b);
		
		if (breq.getNumeroMarce() != null) {
			b.setNumeroMarce(breq.getNumeroMarce());
		}
		
		if ((breq.getFreno()) != null && (!breq.getFreno().isEmpty())) {
			String f = breq.getFreno().trim().toUpperCase();
			TipoFreno tF = repFre.findByTipoFreno(f).orElseThrow(() ->
							new VeicoloException(msgS.get("null_fre")));
			b.setFreno(tF);
		}
		
		if ((breq.getSospensione()) != null && (!breq.getSospensione().isEmpty())) {
			String s = breq.getSospensione().trim().toUpperCase();
			TipoSospensione tS = repSos.findByTipoSospensione(s).orElseThrow(() ->
							new VeicoloException(msgS.get("null_sos")));
			b.setSospensione(tS);
		}
				
		if ((breq.getPieghevole()) != null) {
			b.setPieghevole(breq.getPieghevole());
		} 

		return b;
	}

}

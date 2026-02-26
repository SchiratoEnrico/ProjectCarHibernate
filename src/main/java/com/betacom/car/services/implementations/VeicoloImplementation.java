package com.betacom.car.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.output.VeicoloDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Veicolo;
import com.betacom.car.repositories.IVeicoloRepository;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.services.interfaces.IVeicoloServices;
import com.betacom.car.specifications.VeicoloSpecs;
import com.betacom.car.utilities.ObjectDTOMapper;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VeicoloImplementation implements IVeicoloServices<VeicoloDTO, Integer, VeicoloFilter>{
	private final IVeicoloRepository repV;
	private final IMessagesServices msgS;
	private final ObjectDTOMapper myMapper;
	private final Utils util;

	@Override
	public List<VeicoloDTO> findAll() throws VeicoloException {
		log.debug("find all Bicicletta");

		List<Veicolo> lV = repV.findAll();
		return lV.stream()
				 .map(v -> (VeicoloDTO) myMapper.map(v))
				 .collect(Collectors.toList());
	}

	@Override
	public VeicoloDTO findById(Integer id) throws VeicoloException {
		Veicolo v = repV.findById(id).orElseThrow(() -> 
									new VeicoloException(msgS.get("null_veh")));
		return (VeicoloDTO) myMapper.map(v);
	}

	@Override
	public List<VeicoloDTO> filter(VeicoloFilter filter) {
		
		util.validateFilter(filter);
		
		//faccio le specification col filter
		Specification<Veicolo> spec = VeicoloSpecs.withFilter(filter);
	    List<Veicolo> entities = repV.findAll(spec);
		
		return entities.stream()
						.map(e -> (VeicoloDTO) myMapper.map(e))
						.collect(Collectors.toList());
	}

}

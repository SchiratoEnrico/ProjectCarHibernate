package com.betacom.car.services.implementations;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.dto.input.MacchinaRequest;
import com.betacom.car.dto.output.MacchinaDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Macchina;
import com.betacom.car.repositories.IMacchinaRepository;
import com.betacom.car.services.interfaces.IMacchinaServices;
import com.betacom.car.specifications.MacchinaSpecs;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class MacchinaImplementation implements IMacchinaServices{

	//autowired grazie a lombok
	private final IMacchinaRepository repM;
	
	
	@Override
	public Integer create(MacchinaRequest req) throws VeicoloException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MacchinaRequest req) throws VeicoloException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MacchinaDTO> findAll() throws VeicoloException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MacchinaDTO findById(Integer id) throws VeicoloException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MacchinaDTO> filter(MacchinaFilter filter) {
		
		//faccio le specification col filter
		Specification<Macchina> spec = MacchinaSpecs.withFilter(filter);

		//faccio il find applicando i filtri
	    List<Macchina> entities = repM.findAll(spec);
		
		
		//da ssitemare il return
		return null;
	}

}

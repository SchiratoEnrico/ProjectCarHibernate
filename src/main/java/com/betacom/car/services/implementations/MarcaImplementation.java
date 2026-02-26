package com.betacom.car.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.MarcaRequest;
import com.betacom.car.dto.output.MarcaDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Marca;
import com.betacom.car.models.TipoVeicolo;
import com.betacom.car.repositories.IMarcaRepository;
import com.betacom.car.services.interfaces.IMarcaServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MarcaImplementation implements IMarcaServices{
	private final IMessagesServices msgS;
	private final IMarcaRepository repM;

	@Override
	public void create(MarcaRequest req) throws VeicoloException {
		log.debug("creating Marca {}", req);

		Marca t = new Marca();
		if ((req.getMarca() == null) | (req.getMarca().isBlank())) {
			throw new VeicoloException("null_mar");
		}
		String myT = req.getMarca().trim().toUpperCase();
		Optional<Marca> t2 = repM.findByMarca(myT);
		if (t2.isEmpty()) {
			t.setMarca(myT);
			repM.save(t);
		} else {
			throw new VeicoloException("exists_mar");
		}
	}

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("removing Marca with ID {}", id);

		Marca t = repM.findById(id).orElseThrow(() ->
							new VeicoloException(msgS.get("!exists_mar")));
		repM.delete(t);		
	}

	@Override
	public void update(MarcaRequest req) throws VeicoloException {
		log.debug("modifying Marca {}", req);
		Marca t = repM.findById(req.getId()).orElseThrow(() ->
								new VeicoloException(msgS.get("!exists_mar")));

		
		if ((req.getMarca() != null) && (!req.getMarca().isBlank())) {
			String myT = req.getMarca().trim().toUpperCase();
			Optional<Marca> t2 = repM.findByMarca(myT);
			if (t2.isEmpty()) {
				t.setMarca(myT);
				repM.save(t);			}
			else {
				throw new VeicoloException("exists_mar");
			}
		}
	}

	@Override
	public List<MarcaDTO> list() {
		List<Marca> lM = repM.findAll();
		return Utils.buildMarcaDTOs(lM);
	}
}

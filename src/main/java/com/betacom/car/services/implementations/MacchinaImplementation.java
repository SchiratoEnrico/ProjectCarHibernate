package com.betacom.car.services.implementations;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.dto.input.MacchinaRequest;
import com.betacom.car.dto.output.CategoriaDTO;
import com.betacom.car.dto.output.ColoreDTO;
import com.betacom.car.dto.output.MacchinaDTO;
import com.betacom.car.dto.output.MarcaDTO;
import com.betacom.car.dto.output.TipoAlimentazioneDTO;
import com.betacom.car.dto.output.TipoVeicoloDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Macchina;
import com.betacom.car.repositories.IMacchinaRepository;
import com.betacom.car.services.interfaces.IMacchinaServices;
import com.betacom.car.services.interfaces.IMessagesServices;
import com.betacom.car.specifications.MacchinaSpecs;
import com.betacom.car.utilities.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MacchinaImplementation implements IMacchinaServices{

	//autowired grazie a lombok
	private final IMacchinaRepository repM;
    private final IMessagesServices msgS;
    private final Utils utils;
	
	@Override
	public Integer create(MacchinaRequest req) throws VeicoloException {
		log.debug("create {}", req);

        Macchina m = new Macchina();
        checkReqMacc(req, m);
        // controllo targa duplicata
        if (repM.findByTarga(req.getTarga()).isPresent())
            throw new VeicoloException(msgS.get("targa_exists"));

        int id = repM.save(m).getId();
        log.debug("macchina creata con id: {}", id);
        return id;
    }
	

	@Override
	public void delete(Integer id) throws VeicoloException {
		log.debug("delete macchina con id: {}", id);

        Macchina m = repM.findById(id)
                .orElseThrow(() -> new VeicoloException(msgS.get("null_mac")));

        repM.delete(m);
		
	}

	@Override
	public void update(MacchinaRequest req) throws VeicoloException {
		log.debug("update {}", req);

        Macchina m = repM.findById(req.getId())
                .orElseThrow(() -> new VeicoloException(msgS.get("null_mac")));

        checkReqMacc(req, m);
        repM.save(m);
		
	}

	@Override
	public List<MacchinaDTO> findAll() throws VeicoloException {
		log.debug("findAll()");

        return repM.findAll()
                .stream()
                .map(this::buildMacchinaDTO)
                .toList();
        
	}

	@Override
	public MacchinaDTO findById(Integer id) throws VeicoloException {
		log.debug("findById: {}", id);

        Macchina m = repM.findById(id)
                .orElseThrow(() -> new VeicoloException(msgS.get("null_mac")));

        return buildMacchinaDTO(m);
	}

	@Override
	public List<MacchinaDTO> filter(MacchinaFilter filter) {
		
		//faccio le specification col filter
		Specification<Macchina> spec = MacchinaSpecs.withFilter(filter);

	    return repM.findAll(spec)
                .stream()
                .map(this::buildMacchinaDTO)
                .toList();
	}
	
	 private MacchinaDTO buildMacchinaDTO(Macchina m) {
	        MacchinaDTO dto = new MacchinaDTO();

	        dto.setIdVeicolo(m.getId());
	        dto.setNumeroRuote(m.getNumeroRuote());
	        dto.setAnnoProduzione(Utils.fromYear(m.getAnnoProduzione()));
	        dto.setModello(m.getModello());
	        dto.setCc(m.getCc());
	        dto.setNumeroPorte(m.getNumeroPorte());
	        dto.setTarga(m.getTarga());

	        dto.setColore(ColoreDTO.builder()
	                .id(m.getColore().getId())
	                .colore(m.getColore().getColore())
	                .build());

	        dto.setMarca(MarcaDTO.builder()
	                .id(m.getMarca().getId())
	                .marca(m.getMarca().getMarca())
	                .build());

	        dto.setTipoAlimentazione(TipoAlimentazioneDTO.builder()
	                .id(m.getTipoAlimentazione().getId())
	                .tipoAlimentazione(m.getTipoAlimentazione().getTipoAlimentazione())
	                .build());

	        dto.setCategoria(CategoriaDTO.builder()
	                .id(m.getCategoria().getId())
	                .categoria(m.getCategoria().getCategoria())
	                .build());

	        dto.setTipoVeicolo(TipoVeicoloDTO.builder()
	                .id(m.getTipoVeicolo().getId())
	                .tipoVeicolo(m.getTipoVeicolo().getTipoVeicolo())
	                .build());

	        return dto;
	    }
	

	 public Macchina checkReqMacc(MacchinaRequest req, Macchina m) throws VeicoloException {

	        // richiama il checkReq del veicolo per i campi comuni
	        utils.checkReq(req, m);

	        if (req.getCc() != null)
	            m.setCc(req.getCc());
	        else
	            throw new VeicoloException(msgS.get("null_cc"));

	        if (req.getNumeroPorte() != null)
	            m.setNumeroPorte(req.getNumeroPorte());
	        else
	            throw new VeicoloException(msgS.get("null_porte"));

	        if (req.getTarga() != null && !req.getTarga().isBlank())
	            m.setTarga(req.getTarga().toUpperCase());
	        else
	            throw new VeicoloException(msgS.get("null_targa"));

	        return m;
	    }
}

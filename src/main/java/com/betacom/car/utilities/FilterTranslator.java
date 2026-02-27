package com.betacom.car.utilities;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.VeicoloFilterRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.repositories.ICategoriaRepository;
import com.betacom.car.repositories.IColoreRepository;
import com.betacom.car.repositories.IMarcaRepository;
import com.betacom.car.repositories.ITipoAlimentazioneRepository;
import com.betacom.car.repositories.ITipoFrenoRepository;
import com.betacom.car.repositories.ITipoSospensioneRepository;
import com.betacom.car.repositories.ITipoVeicoloRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public  class FilterTranslator {
	
	private final IMarcaRepository marcaR;
	private final ICategoriaRepository catR;
	private final IColoreRepository colR;
	private final ITipoAlimentazioneRepository alimR;
	private final ITipoFrenoRepository frenoR;
	private final ITipoSospensioneRepository sospR;
	private final ITipoVeicoloRepository veicR;
	
	
	public VeicoloFilter toVeicoloFilter(VeicoloFilterRequest req) {

		VeicoloFilter f = new VeicoloFilter();
		
        if (req == null) 
        	return f;

        if(req.getNumeroRuote() != null)
        	f.setNumeroRuote(req.getNumeroRuote());
        
        if(req.getAnno() != null)
        	f.setAnno(req.getAnno());
        
        if (notBlank(req.getMarca())) {
            f.setIdMarca(findMarcaId(req.getMarca()));
        }
        if (notBlank(req.getColore())) {
            f.setIdColore(findColoreId(req.getColore()));
        }
        if (notBlank(req.getCategoria())) {
            f.setIdCategoria(findCategoriaId(req.getCategoria()));
        }
        if (notBlank(req.getTipoAlimentazione())) {
            f.setIdTipoAlimentazione(findTipoAlimId(req.getTipoAlimentazione()));
        }
        if (notBlank(req.getTipoVeicolo())) {
            f.setIdTipoVeicolo(findTipoVeicoloId(req.getTipoVeicolo()));
        }
        
        if (req.getCc() != null) {
	        f.setCc(req.getCc());
	    }

	    if (req.getNumeroPorte() != null) {
	        f.setNumeroPorte(req.getNumeroPorte());
	    }

	    if (req.getTarga() != null && !req.getTarga().isBlank()) {
	        f.setTarga(req.getTarga());
	    }
        
	    if (req.getNumeroMarce() != null) {
	        f.setNumeroMarce(req.getNumeroMarce());
	    }

	    if (req.getPieghevole() != null) {
	        f.setPieghevole(req.getPieghevole());
	    }
	    
	    if (notBlank(req.getFreno())) {
            f.setIdFreno(findFrenoId(req.getFreno()));
        }
	    
	    if (notBlank(req.getSospensione())) {
            f.setIdSospensione(findSospId(req.getSospensione()));
        }
	    
        return f;
    }
	
	
	
	
	//per fare gli if sopra un po' più puliti
	private boolean notBlank(String s) { 
		return s != null && !s.isBlank(); 
	}
	
	 private Integer findMarcaId(String v) {
	        return marcaR.findByMarca(v)
	                .orElseThrow(() -> new VeicoloException("!exists_mar"))
	                .getId(); 
	    }
	 
	 private Integer findTipoVeicoloId(String v) {
	        return veicR.findByTipoVeicolo(v)
	                .orElseThrow(() -> new VeicoloException("!exists_vei"))
	                .getId(); 
	    }

	    private Integer findColoreId(String v) {
	        return colR.findByColore(v)
	                .orElseThrow(() -> new VeicoloException("!exists_col"))
	                .getId();
	    }

	    private Integer findCategoriaId(String v) {
	        return catR.findByCategoria(v)
	                .orElseThrow(() -> new VeicoloException("!exists_mar"))
	                .getId();
	    }

	    private Integer findTipoAlimId(String v) {
	        return alimR.findByTipoAlimentazione(v)
	                .orElseThrow(() -> new VeicoloException("!exists_ali"))
	                .getId();
	    }

	    private Integer findFrenoId(String v) {
	        return frenoR.findByTipoFreno(v)
	                .orElseThrow(() -> new VeicoloException("!exists_fre"))
	                .getId();
	    }

	    private Integer findSospId(String v) {
	        return sospR.findByTipoSospensione(v)
	                .orElseThrow(() -> new VeicoloException("!exists_sos"))
	                .getId();
	    }
}

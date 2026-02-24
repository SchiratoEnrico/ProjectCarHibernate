package com.betacom.car.utilities;

import com.betacom.car.dto.filters.BiciFilter;
import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.dto.filters.MotoFilter;
import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.BiciFilterRequest;
import com.betacom.car.dto.input.MacchinaFilterRequest;
import com.betacom.car.dto.input.MotoFilterRequest;
import com.betacom.car.dto.input.VeicoloFilterRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.repositories.ICategoriaRepository;
import com.betacom.car.repositories.IColoreRepository;
import com.betacom.car.repositories.IMarcaRepository;
import com.betacom.car.repositories.ITipoAlimentazioneRepository;
import com.betacom.car.repositories.ITipoFrenoRepository;
import com.betacom.car.repositories.ITipoSospensioneRepository;
import com.betacom.car.repositories.ITipoVeicoloRepository;
import com.betacom.car.services.interfaces.IMessagesServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public  class FilterTranslator {
	
	private final IMarcaRepository marcaR;
	private final ICategoriaRepository catR;
	private final IColoreRepository colR;
	private final ITipoAlimentazioneRepository alimR;
	private final ITipoFrenoRepository frenoR;
	private final ITipoSospensioneRepository sospR;
	private final ITipoVeicoloRepository veicR;
	private final IMessagesServices msgS;
	
	
	private VeicoloFilter toVeicoloFilter(VeicoloFilterRequest req) {
        VeicoloFilter f = null;
        if (req == null) 
        	return f;

        f.setNumeroRuote(req.getNumeroRuote());
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

        return f;
    }
	
	
	public MacchinaFilter toMacchinaFilter(MacchinaFilterRequest req) {
        
		MacchinaFilter f = new MacchinaFilter();
		
		buildCommonFields(toVeicoloFilter(req), f);

		if (req.getCc() != null) {
	        f.setCc(req.getCc());
	    }

	    if (req.getNumeroPorte() != null) {
	        f.setNumeroPorte(req.getNumeroPorte());
	    }

	    if (req.getTarga() != null && !req.getTarga().isBlank()) {
	        f.setTarga(req.getTarga());
	    }
	    
        return f;
    }
	
	public MotoFilter toMotoFilter(MotoFilterRequest req) {
        
		MotoFilter f = new MotoFilter();
		
		buildCommonFields(toVeicoloFilter(req), f);

		if (req.getCc() != null) {
	        f.setCc(req.getCc());
	    }

	    if (req.getNumeroMarce() != null) {
	        f.setNumeroMarce(req.getNumeroMarce());
	    }

	    if (req.getTarga() != null && !req.getTarga().isBlank()) {
	        f.setTarga(req.getTarga());
	    }
	    
        return f;
    }
	
	public BiciFilter toBiciFilter(BiciFilterRequest req) {
		BiciFilter f = new BiciFilter();
		
		buildCommonFields(toVeicoloFilter(req), f);
		
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
	
	
	private void buildCommonFields(VeicoloFilter origin, VeicoloFilter destination) {
        destination.setNumeroRuote(origin.getNumeroRuote());
        destination.setAnno(origin.getAnno());
        destination.setIdMarca(origin.getIdMarca());
        destination.setIdModello(origin.getIdModello());
        destination.setIdColore(origin.getIdColore());
        destination.setIdCategoria(origin.getIdCategoria());
        destination.setIdTipoAlimentazione(origin.getIdTipoAlimentazione());
    }
	
	 private Integer findMarcaId(String v) {
	        return marcaR.findByMarca(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_marca")))
	                .getId(); 
	    }
	 
	 private Integer findTipoVeicoloId(String v) {
	        return veicR.findByTipoVeicolo(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_tipo")))
	                .getId(); 
	    }

	    private Integer findColoreId(String v) {
	        return colR.findByColore(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_colore")))
	                .getId();
	    }

	    private Integer findCategoriaId(String v) {
	        return catR.findByCategoria(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_cat")))
	                .getId();
	    }

	    private Integer findTipoAlimId(String v) {
	        return alimR.findByTipoAlimentazione(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_alim")))
	                .getId();
	    }

	    private Integer findFrenoId(String v) {
	        return frenoR.findByTipoFreno(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_freno")))
	                .getId();
	    }

	    private Integer findSospId(String v) {
	        return sospR.findByTipoSospensione(v)
	                .orElseThrow(() -> new VeicoloException(msgS.get("null_sosp")))
	                .getId();
	    }
}

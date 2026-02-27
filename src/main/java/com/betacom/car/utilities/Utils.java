package com.betacom.car.utilities;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.dto.input.VeicoloRequest;
import com.betacom.car.dto.output.BiciclettaDTO;
import com.betacom.car.dto.output.CategoriaDTO;
import com.betacom.car.dto.output.ColoreDTO;
import com.betacom.car.dto.output.MacchinaDTO;
import com.betacom.car.dto.output.MarcaDTO;
import com.betacom.car.dto.output.MotoDTO;
import com.betacom.car.dto.output.TipoAlimentazioneDTO;
import com.betacom.car.dto.output.TipoFrenoDTO;
import com.betacom.car.dto.output.TipoSospensioneDTO;
import com.betacom.car.dto.output.TipoVeicoloDTO;
import com.betacom.car.dto.output.VeicoloDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.Categoria;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Marca;
import com.betacom.car.models.Moto;
import com.betacom.car.models.TipoAlimentazione;
import com.betacom.car.models.TipoFreno;
import com.betacom.car.models.TipoSospensione;
import com.betacom.car.models.TipoVeicolo;
import com.betacom.car.models.Veicolo;
import com.betacom.car.repositories.ICategoriaRepository;
import com.betacom.car.repositories.IColoreRepository;
import com.betacom.car.repositories.IMarcaRepository;
import com.betacom.car.repositories.ITipoAlimentazioneRepository;
import com.betacom.car.repositories.ITipoVeicoloRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class Utils {

	private final IColoreRepository repCol;
	private final IMarcaRepository repMar;
	private final ITipoAlimentazioneRepository repAli;
	private final ITipoVeicoloRepository repVei;
	private final ICategoriaRepository repCat;

	public Veicolo checkReq(VeicoloRequest req, Veicolo v) throws VeicoloException {
		
		if (req.getColore() != null && (!req.getColore().isBlank())) {
			String c = req.getColore().trim().toUpperCase();
			Colore col = repCol.findByColore(c).orElseThrow(() ->
								new VeicoloException("!exists_col"));
			v.setColore(col);
		} else {
			throw new VeicoloException("null_col");
		}

		if (req.getMarca() != null && (!req.getMarca().isBlank())) {
			String m = req.getMarca().trim().toUpperCase();
			Marca mar = repMar.findByMarca(m).orElseThrow(() ->
								new VeicoloException("!exists_mar"));
			v.setMarca(mar);
		} else {
			throw new VeicoloException("null_mar");
		}

		if (req.getAlimentazione() != null && (!req.getAlimentazione().isBlank())) {
			String t = req.getAlimentazione().trim().toUpperCase();
			TipoAlimentazione tA = repAli.findByTipoAlimentazione(t).orElseThrow(() ->
								new VeicoloException("!exists_mar"));
			v.setTipoAlimentazione(tA);
		} else {
			throw new VeicoloException("null_ali");
		}
		
		if (req.getCategoria() != null && (!req.getCategoria().isBlank())) {
			String c = req.getCategoria().trim().toUpperCase();
			Categoria cat = repCat.findByCategoria(c).orElseThrow(() ->
								new VeicoloException("!exists_mar"));
			v.setCategoria(cat);
		} else {
			throw new VeicoloException("null_cat");
		}
		
		if (req.getTipoVeicolo() != null && (!req.getTipoVeicolo().isBlank())) {
			String c = req.getTipoVeicolo().trim().toUpperCase();
			TipoVeicolo tV = repVei.findByTipoVeicolo(c).orElseThrow(() ->
								new VeicoloException("!exists_vei"));
			v.setTipoVeicolo(tV);
		} else {
			throw new VeicoloException("null_vei");
		}
				
		if (req.getAnnoProduzione() != null) {
			v.setAnnoProduzione(req.getAnnoProduzione());
		} else {
			throw new VeicoloException("null_ann");
		}
		
		if (req.getNumeroRuote() != null ) {
			v.setNumeroRuote(req.getNumeroRuote());
		} else {
			throw new VeicoloException("null_ruo");
		}

		v.setModello(req.getModello());

		return v;
	}

	public Veicolo optReq(VeicoloRequest req, Veicolo v) throws VeicoloException {
		if (req.getColore() != null && (!req.getColore().isBlank())) {
			String c = req.getColore().trim().toUpperCase();
			Optional<Colore> col = repCol.findByColore(c);
			if (!col.isEmpty()) {
				v.setColore(col.get());
			}
		}
		
		if (req.getMarca() != null && (!req.getMarca().isBlank())) {
			String m = req.getMarca().trim().toUpperCase();
			Optional<Marca> mar = repMar.findByMarca(m);
			if (!mar.isEmpty()) {
				v.setMarca(mar.get());
			}
		}

		if (req.getAlimentazione() != null && (!req.getAlimentazione().isBlank())) {
			String tA = req.getAlimentazione().trim().toUpperCase();
			Optional<TipoAlimentazione> ta = repAli.findByTipoAlimentazione(tA);
			if (!ta.isEmpty()) {
				v.setTipoAlimentazione(ta.get());
			}
		}

		if (req.getCategoria() != null && (!req.getCategoria().isBlank())) {
			String cat = req.getCategoria().trim().toUpperCase();
			Optional<Categoria> c = repCat.findByCategoria(cat);
			if (!c.isEmpty()) {
				v.setCategoria(c.get());
			}
		}
		
		if (req.getTipoVeicolo() != null && (!req.getTipoVeicolo().isBlank())) {
			String c = req.getTipoVeicolo().trim().toUpperCase();
			Optional<TipoVeicolo> tV = repVei.findByTipoVeicolo(c);
			if (!tV.isEmpty()) {
				v.setTipoVeicolo(tV.get());
			}
		}

		if (req.getAnnoProduzione() != null) {
			v.setAnnoProduzione(req.getAnnoProduzione());
		} 
		
		if (req.getNumeroRuote() != null ) {
			v.setNumeroRuote(req.getNumeroRuote());
		}
		
		if ((req.getModello() != null) && (!req.getModello().isBlank()) ) {
			v.setModello(req.getModello());
		}
		return v;
	}
	public static CategoriaDTO buildCategoriaDTO(Categoria c) {		
		return CategoriaDTO.builder()
						   .categoria(c.getCategoria())
						   .id(c.getId())
						   .build();
	}
	
	public static MarcaDTO buildMarcaDTO(Marca m) {		
		return MarcaDTO.builder()
						   .marca(m.getMarca())
						   .id(m.getId())
						   .build();
	}

	public static ColoreDTO buildColoreDTO(Colore c) {		
		return ColoreDTO.builder()
						   .colore(c.getColore())
						   .id(c.getId())
						   .build();
	}
	
	public static TipoAlimentazioneDTO buildTipoAlimentazioneDTO(TipoAlimentazione t) {		
		return TipoAlimentazioneDTO.builder()
						   .tipoAlimentazione(t.getTipoAlimentazione())
						   .id(t.getId())
						   .build();
	}

	public static TipoVeicoloDTO buildTipoVeicoloDTO(TipoVeicolo t) {		
		return TipoVeicoloDTO.builder()
						   .tipoVeicolo(t.getTipoVeicolo())
						   .id(t.getId())
						   .build();
	}
	
	public static List<TipoVeicoloDTO> buildTipoVeicoloDTOs(List<TipoVeicolo> lT) {		
		return lT.stream()
				 .map(t -> buildTipoVeicoloDTO(t))
				 .collect(Collectors.toList());
	}

	public static List<TipoSospensioneDTO> buildTipoSospensioneDTOs(List<TipoSospensione> lT) {		
		return lT.stream()
				 .map(t -> buildTipoSospensioneDTO(t))
				 .collect(Collectors.toList());
	}

	public static List<MarcaDTO> buildMarcaDTOs(List<Marca> lS) {		
		return lS.stream()
				 .map(t -> buildMarcaDTO(t))
				 .collect(Collectors.toList());
	}

	public static LocalDate fromYear(Integer year) {
	    return LocalDate.of(year, 1, 1);
	}
	
	public static VeicoloDTO buildVeicoloDTO(Veicolo v) {		
		return VeicoloDTO.builder()
						.idVeicolo(v.getId())
						.categoria(buildCategoriaDTO(v.getCategoria()))
						.marca(buildMarcaDTO(v.getMarca()))
						.annoProduzione(fromYear(v.getAnnoProduzione()))
						.colore(buildColoreDTO(v.getColore()))
						.modello(v.getModello())
						.numeroRuote(v.getNumeroRuote())
						.tipoAlimentazione(buildTipoAlimentazioneDTO(v.getTipoAlimentazione()))
						.tipoVeicolo(buildTipoVeicoloDTO(v.getTipoVeicolo()))
						.build();
	}
	
	private static void buildCommonFields(VeicoloDTO origin, VeicoloDTO destination) {
        destination.setIdVeicolo(origin.getIdVeicolo());
		destination.setNumeroRuote(origin.getNumeroRuote());
        destination.setAnnoProduzione(origin.getAnnoProduzione());
        destination.setMarca(origin.getMarca());
        destination.setModello(origin.getModello());
        destination.setTipoVeicolo(origin.getTipoVeicolo());
        destination.setColore(origin.getColore());
        destination.setCategoria(origin.getCategoria());
        destination.setTipoAlimentazione(origin.getTipoAlimentazione());
    }
	
	public static TipoFrenoDTO buildTipoFrenoDTO(TipoFreno t) {		
		return TipoFrenoDTO.builder()
						   .tipoFreno(t.getTipoFreno())
						   .id(t.getId())
						   .build();
	}

	public static TipoSospensioneDTO buildTipoSospensioneDTO(TipoSospensione t) {		
		return TipoSospensioneDTO.builder()
						   .tipoSospensione(t.getTipoSospensione())
						   .id(t.getId())
						   .build();
	}

	public static BiciclettaDTO buildBiciclettaDTO(Bicicletta b) {
		

		VeicoloDTO v = buildVeicoloDTO(b);
		BiciclettaDTO bDto = new BiciclettaDTO();
		
		buildCommonFields(v, bDto);
		
		bDto.setNumeroMarce(b.getNumeroMarce());
		bDto.setTipoFreno(buildTipoFrenoDTO(b.getFreno()));
		bDto.setTipoSospensione(buildTipoSospensioneDTO(b.getSospensione()));
		bDto.setPieghevole(b.getPieghevole());
		
		return bDto;
	}
	
	public static MacchinaDTO buildMacchinaDTO(Macchina m) {
	    
		VeicoloDTO v = buildVeicoloDTO(m);
		MacchinaDTO dto = new MacchinaDTO();
		
		buildCommonFields(v, dto);


	    dto.setCc(m.getCc());
	    dto.setNumeroPorte(m.getNumeroPorte());
	    dto.setTarga(m.getTarga());

	    return dto;
	}

	public static MotoDTO buildMotoDTO(Moto m) {
		VeicoloDTO v = buildVeicoloDTO(m);
		MotoDTO dto = new MotoDTO();
		
		buildCommonFields(v, dto);


	    dto.setCc(m.getCc());
	    dto.setNumeroMarce(m.getNumeroMarce());
	    dto.setTarga(m.getTarga());

	    return dto;
	}

	public static String formatStringParam(String param) {
		String myP = null;
		if ((param != null) &&  (!param.isBlank())) {
			myP = param.trim().toUpperCase();
		}
		return myP;
	}
	
	//per validare se i filtri possono essere compatibili (non posso filtrare per tipofreno e targa)
	public static void validateFilter(VeicoloFilter f) throws VeicoloException{

        boolean hasBici = f.getIdFreno()!=null || f.getIdSospensione()!=null || f.getPieghevole()!=null;
        boolean hasMotoMacchina = f.getTarga()!=null || f.getCc()!=null;

        boolean hasMoto = f.getNumeroMarce()!=null;   // moto
        boolean hasMacchina = f.getNumeroPorte()!=null;
        log.debug("" + hasMacchina);
        
        // Bici incompatibile con campi motorizzati
        if(hasBici && hasMotoMacchina) 
            throw new VeicoloException("!exists_fil");

        
        if(hasBici && hasMacchina)
        	throw new VeicoloException("!exists_fil");

        // Moto vs Macchina
        if(hasMoto && hasMacchina)
            throw new VeicoloException("!exists_fil");

    }
	
	
	public static void validateTarga(Veicolo req) {
	    
		if(req instanceof Macchina) {
			String regex = "^[A-HJ-NPR-TV-Z]{2}[0-9]{3}[A-HJ-NPR-TV-Z]{2}$";
			Macchina m = (Macchina) req; 
		    if (m.getTarga() == null || !m.getTarga().matches(regex)) {
		        throw new VeicoloException("!valid_tar");
		    }
	    }else {
			Moto m = (Moto) req;
		    String regex = "";
			
		    if(m.getCc() > 50)
				regex = "^[A-HJ-NPR-TV-Z]{2}[0-9]{5}$";
			else
				regex = "^[XY]{1}[0-9]{5}$";
			
			if (m.getTarga() == null || !m.getTarga().matches(regex)) {
		        throw new VeicoloException("!valid_tar");
		    }
	    }
	}
}

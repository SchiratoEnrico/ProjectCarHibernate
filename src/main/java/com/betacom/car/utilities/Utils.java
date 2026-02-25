package com.betacom.car.utilities;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.VeicoloRequest;
import com.betacom.car.dto.output.BiciclettaDTO;
import com.betacom.car.dto.output.CategoriaDTO;
import com.betacom.car.dto.output.ColoreDTO;
import com.betacom.car.dto.output.MarcaDTO;
import com.betacom.car.dto.output.TipoAlimentazioneDTO;
import com.betacom.car.dto.output.TipoFrenoDTO;
import com.betacom.car.dto.output.TipoSospensioneDTO;
import com.betacom.car.dto.output.TipoVeicoloDTO;
import com.betacom.car.dto.output.VeicoloDTO;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.Categoria;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Marca;
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
import com.betacom.car.services.interfaces.IMessagesServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Utils {
	private final IColoreRepository repCol;
	private final IMarcaRepository repMar;
	private final IMessagesServices msgS;
	private final ITipoAlimentazioneRepository repAli;
	private final ITipoVeicoloRepository repVei;
	private final ICategoriaRepository repCat;

	public Veicolo checkReq(VeicoloRequest req, Veicolo v) throws VeicoloException {
		Colore col = repCol.findByColore(req.getColore()).orElseThrow(() ->
						new VeicoloException(msgS.get("null_col")));
		v.setColore(col);

		Marca mar = repMar.findByMarca(req.getMarca()).orElseThrow(() ->
						new VeicoloException(msgS.get("null_mar")));
		v.setMarca(mar);
		
		TipoAlimentazione tA = repAli.findByTipoAlimentazione(req.getAlimentazione()).orElseThrow(() ->
						new VeicoloException(msgS.get("null_ali")));
		v.setTipoAlimentazione(tA);
		
		Categoria cat = repCat.findByCategoria(req.getCategoria()).orElseThrow(() ->
						new VeicoloException(msgS.get("null_cat")));
		v.setCategoria(cat);
		
		TipoVeicolo tV = repVei.findByTipoVeicolo(req.getTipoVeicolo()).orElseThrow(() ->
						new VeicoloException(msgS.get("null_vei")));
		v.setTipoVeicolo(tV);
		
		if (req.getAnnoProduzione() != null) {
			v.setAnnoProduzione(req.getAnnoProduzione());
		} else {
			throw new VeicoloException(msgS.get("null_date"));
		}
		
		if (req.getNumeroRuote() != null ) {
			v.setNumeroRuote(req.getNumeroRuote());
		} else {
			throw new VeicoloException(msgS.get("null_ruo"));
		}

		v.setModello(req.getModello());

		return v;
	}

	public Veicolo optReq(VeicoloRequest req, Veicolo v) throws VeicoloException {
		Optional<Colore> col = repCol.findByColore(req.getColore());
		if (!col.isEmpty()) {
			v.setColore(col.get());
		}

		Optional<Marca> mar = repMar.findByMarca(req.getMarca());
		if (!col.isEmpty()) {
			v.setMarca(mar.get());
		}


		
		Optional<TipoAlimentazione> tA = repAli.findByTipoAlimentazione(req.getAlimentazione());
		if (tA.isPresent()) {
			v.setTipoAlimentazione(tA.get());
		}
		
		Optional<Categoria> cat = repCat.findByCategoria(req.getCategoria());
		if (cat.isPresent()) {
			v.setCategoria(cat.get());
		}
		
		Optional<TipoVeicolo> tV = repVei.findByTipoVeicolo(req.getTipoVeicolo());
		if (tV.isPresent()) {
			v.setTipoVeicolo(tV.get());
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
		BiciclettaDTO bDto = (BiciclettaDTO) buildVeicoloDTO(b);
		
		bDto.setNumeroMarce(b.getNumeroMarce());
		bDto.setTipoFreno(buildTipoFrenoDTO(b.getFreno()));
		bDto.setTipoSospensione(buildTipoSospensioneDTO(b.getSospensione()));
		bDto.setPieghevole(b.getPieghevole());
		
		return bDto;
	}
}

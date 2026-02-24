package com.betacom.car.utilities;

import org.springframework.stereotype.Service;

import com.betacom.car.dto.input.VeicoloRequest;
import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.models.Categoria;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Marca;
import com.betacom.car.models.TipoAlimentazione;
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
	private final static IColoreRepository repCol;
	private final static IMarcaRepository repMar;
	private final static IMessagesServices msgS;
	private final static ITipoAlimentazioneRepository repAli;
	private final static ITipoVeicoloRepository repVei;
	private final static ICategoriaRepository repCat;

	public static Veicolo checkReq(VeicoloRequest req, Veicolo v) throws VeicoloException {
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
		
		if (req.getAnnoProduzione() != null ) {
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

}

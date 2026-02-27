package com.betacom.car.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicolo;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VeicoloSpecs {

	//la specifications funziona per macchina, moto, bici e veicolo
    public static Specification<Veicolo> withFilter(VeicoloFilter f) {
        
    	//root è una tabella (veicolo, macchina, moto,...)
    	//query è la query che costruirà hibernate
    	//cb è un CriteriaBuilder, serve per creare condizioni 	
    	//il risultato finale è un predicate = una condizione sql
    	
    	
//    	@Override
//        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//            
//        }
    	
    	//root è la tabella
    	
    	
    	//qui faccio una lambda che override la funzione sopra di Specificatio
    	//creo la funzione che costruisce WHERE della query
    	
    	return (root, query, cb) -> {

            List<Predicate> p = new ArrayList<>();

            // campi comuni
            if(f.getNumeroRuote()!=null)
                p.add(cb.equal(root.get("numeroRuote"), f.getNumeroRuote()));
            
            //.equal() unisce tipo anno_produzione = ?
            if(f.getAnno()!=null)
                p.add(cb.equal(root.get("annoProduzione"), f.getAnno()));

            if(f.getModello()!=null)
                p.add(cb.like(cb.lower(root.get("modello")), "%"+f.getModello().toLowerCase()+"%"));

            if(f.getIdMarca()!=null)
                p.add(cb.equal(root.join("marca").get("id"), f.getIdMarca()));

            if(f.getIdColore()!=null)
                p.add(cb.equal(root.join("colore").get("id"), f.getIdColore()));

            if(f.getIdCategoria()!=null)
                p.add(cb.equal(root.join("categoria").get("id"), f.getIdCategoria()));

            if(f.getIdTipoAlimentazione()!=null)
                p.add(cb.equal(root.join("tipoAlimentazione").get("id"), f.getIdTipoAlimentazione()));

            if(f.getIdTipoVeicolo()!=null)
                p.add(cb.equal(root.join("tipoVeicolo").get("id"), f.getIdTipoVeicolo()));

            // ===== Campi comuni Moto + Macchina =====
            if(f.getTarga()!=null) {
                p.add(cb.or(
                    cb.equal(cb.treat(root, Moto.class).get("targa"), f.getTarga()),
                    cb.equal(cb.treat(root, Macchina.class).get("targa"), f.getTarga())
                ));
            }

            if(f.getCc()!=null) {
                p.add(cb.or(
                    cb.equal(cb.treat(root, Moto.class).get("cc"), f.getCc()),
                    cb.equal(cb.treat(root, Macchina.class).get("cc"), f.getCc())
                ));
            }

            // sia per bici che per moto
            if(f.getNumeroMarce()!=null) {
                p.add(cb.or(
                    cb.equal(cb.treat(root, Moto.class).get("numeroMarce"), f.getNumeroMarce()),
                    cb.equal(cb.treat(root, Bicicletta.class).get("numeroMarce"), f.getNumeroMarce())
                ));
            }

            //	per macchina
            if(f.getNumeroPorte()!=null) {
                p.add(cb.equal(cb.treat(root, Macchina.class).get("numeroPorte"), f.getNumeroPorte()));
            }

            // per bici
            if(f.getIdFreno()!=null) {
                p.add(cb.equal(cb.treat(root, Bicicletta.class)
                        .join("freno").get("id"), f.getIdFreno()));
            }

            //.treat() serve per fare i join
            if(f.getIdSospensione()!=null) {
                p.add(cb.equal(cb.treat(root, Bicicletta.class)
                        .join("sospensione").get("id"), f.getIdSospensione()));
            }

            if(f.getPieghevole()!=null) {
                p.add(cb.equal(cb.treat(root, Bicicletta.class)
                        .get("pieghevole"), f.getPieghevole()));
            }
            
            //unisco tutte le condizioni con AND
            return cb.and(p.toArray(new Predicate[0]));
        };
    }
}
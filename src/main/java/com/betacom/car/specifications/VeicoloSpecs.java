package com.betacom.car.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.VeicoloFilter;
import com.betacom.car.models.Veicolo;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class VeicoloSpecs {

	//la specifications funziona per macchina, moto, bici e veicolo
    public static <T extends Veicolo> Specification<T> baseFilter(VeicoloFilter f) {
        
    	//root è una tabella (veicolo, macchina, moto,...)
    	//query è la query che costruirà hibernate
    	//cb è un CriteriaBuilder, serve per creare condizioni 	
    	//il risultato finale è un predicate = una condizione sql
    	
    	
    	//creo la funzione che costruisce WHERE della query
    	return (root, query, cb) -> {

    		// se non ci sono filtri return true che significa nessuna condizione
            if (f == null) return cb.conjunction();

            //questa è una lista di condizioni
            List<Predicate> p = new ArrayList<Predicate>();

            
            //questo è l'equivalente di fare numeroRuote = ?  in sql
            //root.get("numeroRuote) è il campo della entity, ovvero Veicolo
            if (f.getNumeroRuote() != null) {
                p.add(cb.equal(root.get("numeroRuote"), f.getNumeroRuote()));
            }

            
            // Qui assumo Integer (come nel filter).
            if (f.getAnno() != null) {
                p.add(cb.equal(root.get("annoProduzione"), f.getAnno()));
            }

            //qui c'è anche da fare un join verso le varie tabelle di marca,colore, categoria
            if (f.getIdMarca() != null) {
                p.add(cb.equal(root.join("marca", JoinType.INNER).get("id"), f.getIdMarca()));
            }

            if (f.getIdColore() != null) {
                p.add(cb.equal(root.join("colore", JoinType.INNER).get("id"), f.getIdColore()));
            }

            if (f.getIdCategoria() != null) {
                p.add(cb.equal(root.join("categoria", JoinType.INNER).get("id"), f.getIdCategoria()));
            }

            if (f.getIdTipoAlimentazione() != null) {
                p.add(cb.equal(root.join("tipoAlimentazione", JoinType.INNER).get("id"), f.getIdTipoAlimentazione()));
            }

            //qui in pratica se la lista dei filtri è vuota restituisco true, se ci sono filtri li unico con AND
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(new Predicate[0]));
        };
    }
}
package com.betacom.car.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.MotoFilter;
import com.betacom.car.models.Moto;

public class MotoSpecs {
	
	public static Specification<Moto> withFilter(MotoFilter f) {

    	//inizio con i filtri comuni quindi uso veicolospecs
        Specification<Moto> spec = VeicoloSpecs.baseFilter(f);
        
        //qui se  i filtri specifici di macchina non ci sono return spec (quelli comuni)
        if (f == null) return spec;

        //qua analogamente in veicolo aggiungo i filtri se son presenti
        //quindi 
        //cc = ?
        if (f.getCc() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("cc"), f.getCc()));
        }

        //numeroPorte = ?
        if (f.getNumeroMarce() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numeroMarce"), f.getNumeroMarce()));
        }
        
        //UPPER(targa) like '%targachepasso%'
        if (f.getTarga() != null && !f.getTarga().isBlank()) {
            String like = "%" + f.getTarga().trim().toUpperCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.upper(root.get("targa")), like));
        }

        return spec;
    }
}

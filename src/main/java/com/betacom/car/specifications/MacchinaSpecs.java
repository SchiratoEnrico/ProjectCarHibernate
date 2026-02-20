package com.betacom.car.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.models.Macchina;

public class MacchinaSpecs {

    public static Specification<Macchina> withFilter(MacchinaFilter f) {

    	//inizio con i filtri comuni quindi uso veicolospecs
        Specification<Macchina> spec = VeicoloSpecs.baseFilter(f);
        
        //qui se  i filtri specifici di macchina non ci sono return spec (quelli comuni)
        if (f == null) return spec;

        //qua analogamente in veicolo aggiungo i filtri se son presenti
        //quindi 
        //cc = ?
        if (f.getCc() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("cc"), f.getCc()));
        }

        //numeroPorte = ?
        if (f.getNumeroPorte() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numeroPorte"), f.getNumeroPorte()));
        }
        
        //UPPER(targa) like '%targachepasso%'
        if (f.getTarga() != null && !f.getTarga().isBlank()) {
            String like = "%" + f.getTarga().trim().toUpperCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.upper(root.get("targa")), like));
        }

        return spec;
    }
}

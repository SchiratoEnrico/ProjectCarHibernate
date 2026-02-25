package com.betacom.car.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.betacom.car.dto.filters.BiciFilter;
import com.betacom.car.models.Bicicletta;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class BiciSpecs {

    public static Specification<Bicicletta> withFilter(BiciFilter f) {
        return (root, query, cb) -> {

            if (f == null) return cb.conjunction();

            List<Predicate> p = new ArrayList<Predicate>();

            if (f.getNumeroMarce() != null) {
                p.add(cb.equal(root.get("numeroMarce"), f.getNumeroMarce()));
            }

            if (f.getPieghevole() != null) {
                p.add(cb.equal(root.get("pieghevole"), f.getPieghevole()));
            }

            // FK -> TipoFreno
            if (f.getIdFreno() != null) {
                p.add(cb.equal(
                        root.join("freno", JoinType.INNER).get("id"),
                        f.getIdFreno()
                ));
            }

            // FK -> TipoSospensione
            if (f.getIdSospensione() != null) {
                p.add(cb.equal(
                        root.join("sospensione", JoinType.INNER).get("id"),
                        f.getIdSospensione()
                ));
            }

            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(new Predicate[0]));
        };
    }
}
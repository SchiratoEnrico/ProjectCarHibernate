package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.Veicolo;

@Repository
public interface IBiciclettaRepository extends JpaRepository<Bicicletta, Integer> , JpaSpecificationExecutor<Veicolo> {
}

package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicolo;

@Repository
public interface IMotoRepository extends JpaRepository<Moto, Integer>, JpaSpecificationExecutor<Veicolo>  {

	Optional<Moto> findByTarga(String targa); 
}

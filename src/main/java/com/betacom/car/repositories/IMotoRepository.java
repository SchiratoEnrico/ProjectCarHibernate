package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Moto;

@Repository
public interface IMotoRepository extends JpaRepository<Moto, Integer>, JpaSpecificationExecutor<Moto>  {

	Optional<Moto> findByTarga(String targa); 
}

package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Macchina;


@Repository
public interface IMacchinaRepository extends JpaRepository<Macchina, Integer>, JpaSpecificationExecutor<Macchina>  {

	Optional<Macchina> findByTarga(String targa); 

}

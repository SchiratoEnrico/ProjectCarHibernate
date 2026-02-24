package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.TipoSospensione;

@Repository
public interface ITipoSospensioneRepository extends JpaRepository<TipoSospensione, Integer>  {
	public Optional<TipoSospensione> findByTipoSospensione(String tipoSospensione);
}

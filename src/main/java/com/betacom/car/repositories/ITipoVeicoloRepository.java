package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.TipoVeicolo;

@Repository
public interface ITipoVeicoloRepository extends JpaRepository<TipoVeicolo, Integer>  {
	public Optional<TipoVeicolo> findByTipoVeicolo(String tipoVeicolo);
}

package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.TipoAlimentazione;

@Repository
public interface ITipoAlimentazioneRepository extends JpaRepository<TipoAlimentazione, Integer>  {
	public Optional<TipoAlimentazione> findByTipoAlimentazione(String tipoAlimentazione);
}

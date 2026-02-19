package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoAlimentazioneRepository extends JpaRepository<TipoAlimentazione, Integer>  {
}

package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoSospensioneRepository extends JpaRepository<TipoSospensione, Integer>  {
}

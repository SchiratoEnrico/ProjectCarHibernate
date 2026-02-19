package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBiciclettaRepository extends JpaRepository<Bicicletta, Integer>  {
}

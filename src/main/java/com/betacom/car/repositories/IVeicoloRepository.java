package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVeicoloRepository extends JpaRepository<Veicolo, Integer>  {
}

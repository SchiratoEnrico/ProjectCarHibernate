package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Moto;

@Repository
public interface IMotoRepository extends JpaRepository<Moto, Integer>  {
}

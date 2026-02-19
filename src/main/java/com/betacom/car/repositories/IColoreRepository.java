package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Colore;

@Repository
public interface IColoreRepository extends JpaRepository<Colore, Integer>  {
}
package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Macchina;

@Repository
public interface IMacchinaRepository extends JpaRepository<Macchina, Integer>  {
}


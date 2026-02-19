package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer>  {
}

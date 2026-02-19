package com.betacom.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.TipoFreno;

@Repository
public interface ITipoFrenoRepository extends JpaRepository<TipoFreno, Integer>  {
}

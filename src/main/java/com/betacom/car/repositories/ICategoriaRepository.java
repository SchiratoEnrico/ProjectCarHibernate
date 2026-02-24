package com.betacom.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.car.models.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>  {
	public Optional<Categoria> findByCategoria(String categoria);

}
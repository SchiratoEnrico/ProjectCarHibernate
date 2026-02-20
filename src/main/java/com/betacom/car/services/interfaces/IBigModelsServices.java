package com.betacom.car.services.interfaces;
import java.util.List;
import com.betacom.car.exceptions.VeicoloException;

public interface IBigModelsServices<IN, OUT, ID> {
	public Integer create(IN req) throws VeicoloException;
	public void delete(ID id) throws VeicoloException;
	void update(IN req) throws VeicoloException;
	List<OUT> findAll() throws VeicoloException;
	OUT findById(ID id) throws VeicoloException;
}
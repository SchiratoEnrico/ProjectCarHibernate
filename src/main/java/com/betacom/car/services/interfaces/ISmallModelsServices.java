package com.betacom.car.services.interfaces;

import java.util.List;

import com.betacom.car.exceptions.VeicoloException;

public interface ISmallModelsServices<IN, OUT, ID>{
	public void create(IN req) throws VeicoloException;
	public void delete(ID id) throws VeicoloException;
	public List<OUT> list();
}

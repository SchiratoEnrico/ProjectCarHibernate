package com.betacom.car.services.interfaces;

import java.util.List;

import com.betacom.car.exceptions.VeicoloException;

import jakarta.transaction.Transactional;

public interface ISmallModelsServices<IN, OUT, ID>{
	@Transactional (rollbackOn = VeicoloException.class)
    public void create(IN req) throws VeicoloException;
    @Transactional (rollbackOn = VeicoloException.class)
    public void delete(ID id) throws VeicoloException;
    @Transactional (rollbackOn = VeicoloException.class)
    public void update(IN req) throws VeicoloException;
    public List<OUT> list();
}

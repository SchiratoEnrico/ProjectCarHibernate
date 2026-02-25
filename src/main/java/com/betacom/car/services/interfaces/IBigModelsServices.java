package com.betacom.car.services.interfaces;
import java.util.List;
import com.betacom.car.exceptions.VeicoloException;

import jakarta.transaction.Transactional;

public interface IBigModelsServices<IN, OUT, ID, FILT> {
	
	@Transactional (rollbackOn = Exception.class)
    public Integer create(IN req) throws VeicoloException;

    @Transactional (rollbackOn = Exception.class)
    public void delete(ID id) throws VeicoloException;

    @Transactional (rollbackOn = Exception.class)
    void update(IN req) throws VeicoloException;

    List<OUT> findAll() throws VeicoloException;
    OUT findById(ID id) throws VeicoloException;

    List<OUT> filter(FILT filter);
}
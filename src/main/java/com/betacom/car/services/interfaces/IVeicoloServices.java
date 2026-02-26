package com.betacom.car.services.interfaces;

import java.util.List;

import com.betacom.car.exceptions.VeicoloException;

public interface IVeicoloServices<VeicoloDTO, Integer, VeicoloFilter> {
    List<VeicoloDTO> findAll() throws VeicoloException;
    VeicoloDTO findById(Integer id) throws VeicoloException;

    List<VeicoloDTO> filter(VeicoloFilter filter);

}

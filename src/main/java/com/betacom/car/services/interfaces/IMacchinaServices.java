package com.betacom.car.services.interfaces;

import org.springframework.data.jpa.repository.Query;

import com.betacom.car.dto.filters.MacchinaFilter;
import com.betacom.car.dto.input.MacchinaRequest;
import com.betacom.car.dto.output.MacchinaDTO;

public interface IMacchinaServices  extends IBigModelsServices<MacchinaRequest, MacchinaDTO, Integer, MacchinaFilter>{

} 
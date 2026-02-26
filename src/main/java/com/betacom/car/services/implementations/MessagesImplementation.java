package com.betacom.car.services.implementations;

import org.springframework.stereotype.Service;

import com.betacom.car.repositories.IMessaggiRepository;
import com.betacom.car.services.interfaces.IMessagesServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessagesImplementation implements IMessagesServices{

	private final IMessaggiRepository repM;

	@Override
	public String get(String code){
		
		String msg;
		try {
			msg = repM.findByCode(code).get().getMessaggio();
		} catch (Exception e) {
			return "Caricamento messaggio fallito";
		}	
		return msg;
	}
}

package com.betacom.car.processes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.car.exceptions.VeicoloException;
import com.betacom.car.utilities.Command;
import com.betacom.car.utilities.CommandParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class ProcessMain {
	
	public void execute(){
		log.debug("Begin main car process");
		
		//retrieve command list
		List<Command> cmdList = CommandParser.parseFromFile(null);
		
		try {
			cmdList.forEach(c -> executeCommand(c));
		}catch(VeicoloException e) {
			log.debug(e.getMessage());
		}
	}
	
	private void executeCommand(Command cmd) throws VeicoloException{
		
	}
}

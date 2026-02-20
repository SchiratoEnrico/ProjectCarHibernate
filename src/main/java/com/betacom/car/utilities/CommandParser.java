package com.betacom.car.utilities;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.betacom.car.exceptions.VeicoloException;

public class CommandParser {

	// Si occupa di analizzare le stringhe di comando fornite dal main.
	// Converte una stringa del tipo:
	// "metodo=add,tipo=macchina,targa=AB123CD,..."
	// in un oggetto Command, validando il formato dei parametri.

	// NW Command ha variabili:
	// private String metodo; (add/filter)
    // private Map<String, String> args; (resto)

	
	
	//parse di una singola riga del file
	public static Command parse(String line) throws VeicoloException {

		// verifico che non venga passata una stringa vuota
		if (line == null || line.isBlank()) {
			throw new VeicoloException("Comando vuoto");
		}

		String[] parts = line.split(",");

		// faccio una mappa con i parametri
		Map<String, String> args = new HashMap<>();

		for (String p : parts) {
			String token = p.trim();

			// se token non è vuoto dopo il trim faccio cose
			if (!token.isEmpty()) {

				// verifico che il formato key=value sia rispettato
				int eq = token.indexOf('=');
				if (eq <= 0 || eq >= token.length() - 1) {
					throw new VeicoloException("Parametro non valido: '" + token + "' (atteso key=value)");
				}
				
				String k = token.substring(0, eq).trim().toLowerCase(); 	
				
				// aggiunto toLowerCase per evitare di non riconoscere metodi/argomenti in caps lock (errore con ID=)
				String v = token.substring(eq + 1).trim();
				args.put(k, v);	
			}
		}

		// prendo il metodo e se è vuoto mando eccezione (add o list)
		String metodo = args.get("metodo");
		if (metodo == null || metodo.isBlank()) {
			throw new VeicoloException("Manca 'metodo=' nel comando");
		}

		// in caso di successo creo new Command con il metodo e la mappa dei parametri
		return new Command(metodo.trim().toLowerCase(), args);
	}
	
	//parso dal file
	public static List<Command> parseFromFile(String path) throws VeicoloException {
	    
	    List<Command> commands = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

	        String line;
	        
	        //tengo una variabile per comunicare la riga del file in cui c'è un errore
	        int lineNumber = 0;

	        //se la riga che leggo non è null
	        while ((line = br.readLine()) != null) {
	            lineNumber++;

	            // salto righe vuote
	            if (line.isBlank()) continue;

	            try {
	                Command c = parse(line);   // riuso parse per una singola riga
	                commands.add(c);
	            } catch (VeicoloException e) {
	                throw new VeicoloException("Errore alla riga " + lineNumber + ": " + e.getMessage());
	            }
	        }

	    } catch (IOException e) {
	        throw new VeicoloException("Errore lettura file: " + path);
	    }

	    return commands;
	}

}

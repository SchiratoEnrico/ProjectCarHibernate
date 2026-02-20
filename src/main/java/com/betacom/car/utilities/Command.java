package com.betacom.car.utilities;

import java.util.Map;

public class Command {

	
	// Rappresenta un comando dell’applicazione già interpretato.
	// Contiene il metodo da eseguire (add o list) e la mappa dei parametri
	// classe contenitore, senza logica.
	
	private String metodo;
    private Map<String, String> args;

    public Command(String metodo, Map<String, String> args) {
        this.metodo = metodo;
        this.args = args;
    }

    public String getMetodo() {
        return metodo;
    }

    public Map<String, String> getArgs() {
        return args;
    }
}

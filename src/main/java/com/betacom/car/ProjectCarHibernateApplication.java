package com.betacom.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.betacom.car.processes.ProcessMain;

@SpringBootApplication
public class ProjectCarHibernateApplication {

	@Autowired
	private ProcessMain p;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectCarHibernateApplication.class, args);
	}
	
	//funzionalità per intervenire al momento di startup
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> { 
			p.execute();
		};
	}
}

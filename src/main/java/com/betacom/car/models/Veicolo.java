package com.betacom.car.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity 
@Table (name = "veicoli")
//qui in pratica creo una relazione di ereditarietà e non di appartenenza
//non è che un veicolo possiede una macchina, moto o bici
//una macchina moto o bici sono figlie, derivano da veicolo
//ogni classe che estende queste avrà una propria tabella che è collegata a questa
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Veicolo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_colore")
	private Colore colore;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marca")
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alimentazione")
	private TipoAlimentazione tipoAlimentazione;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo")
	private TipoVeicolo tipoVeicolo;
	
	@Column(
			name = "numero_ruote",
			nullable = false
			)
	private Integer numeroRuote;
	
	
	@Column(
			name = "anno_produzione",
			nullable = false
			)
	private Integer annoProduzione;
	
	@Column(
			name = "modello"
			)
	private String modello;
}

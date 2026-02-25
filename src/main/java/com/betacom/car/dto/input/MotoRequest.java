package com.betacom.car.dto.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter

public class MotoRequest extends VeicoloRequest {
	
	private Integer cc;
	private Integer numeroMarce;
	private String targa;
	@Override
	public String toString() {
		return super.toString() + "MotoRequest [cc=" + cc + ", numeroMarce=" + numeroMarce + ", targa=" + targa + "]";
	}

}

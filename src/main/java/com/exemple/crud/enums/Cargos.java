package com.exemple.crud.enums;

public enum Cargos {

	DEV(0, "Desenvolvedor"), DESIGNER(1, "Designer"), SUPORTE(2, "Suporte"), Tester(3, "Tester");

	private int idCargo;
	private String cargo;

	private Cargos(int idCargo, String cargo) {
		this.idCargo = idCargo;
		this.cargo = cargo;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public String getCargo() {
		return cargo;
	}

}

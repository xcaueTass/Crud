package com.exemple.crud.models;

public class ObjProdContact {

	private long id;
	private String nome;
	private String contato;
	private long professionalId;

	public ObjProdContact() {
		super();
	}

	public ObjProdContact(long id, String nome, String contato, long professionalId) {
		super();
		this.id = id;
		this.nome = nome;
		this.contato = contato;
		this.professionalId = professionalId;
	}

	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public long getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(long professionalId) {
		this.professionalId = professionalId;
	}

}

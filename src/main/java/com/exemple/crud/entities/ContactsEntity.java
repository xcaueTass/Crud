package com.exemple.crud.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACTS_TABLE")
public class ContactsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "Nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "CONTATO", length = 60, nullable = false)
	private String contato;

	@ManyToOne
	@JoinColumn(name = "profissional_id", nullable = false)
	private ProfessionalsEntity profissionalId;

	public ContactsEntity() {
		super();
	}

	public ContactsEntity(long id, String nome, String contato, ProfessionalsEntity profissionalId) {
		super();
		this.id = id;
		this.nome = nome;
		this.contato = contato;
		this.profissionalId = profissionalId;
	}

	public long getId() {
		return id;
	}

	public ProfessionalsEntity getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(ProfessionalsEntity profissionalId) {
		this.profissionalId = profissionalId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
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

	@Override
	public String toString() {
		return "ContactsEntity [id=" + id + ", nome=" + nome + ", contato=" + contato + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactsEntity other = (ContactsEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

package com.exemple.crud.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROFISSIONAL_TABLE")
public class ProfessionalsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	private long codUser;

	@Column(name = "CARGO", length = 60, nullable = false)
	private String cargo;

	@Column(name = "NASCIMENTO", length = 12, nullable = false)
	private LocalDateTime nascimento;

	@Column(name = "CREATE_DATA", length = 64, nullable = false)
	private LocalDateTime createData;

	@JsonIgnore
	@OneToMany(mappedBy = "contato")
	private List<ContactsEntity> contatos = new ArrayList<>();

	public ProfessionalsEntity() {
		super();
	}

	public ProfessionalsEntity(long codUser, String cargo, LocalDateTime nascimento, LocalDateTime createData,
			List<ContactsEntity> contatos) {
		super();
		this.codUser = codUser;
		this.cargo = cargo;
		this.nascimento = nascimento;
		this.createData = createData;
		this.contatos = contatos;
	}

	public long getCodUser() {
		return codUser;
	}

	public void setCodUser(long codUser) {
		this.codUser = codUser;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public LocalDateTime getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDateTime nascimento) {
		this.nascimento = nascimento;
	}

	public LocalDateTime getCreateData() {
		return createData;
	}

	public void setCreateData(LocalDateTime createData) {
		this.createData = createData;
	}

	@Override
	public String toString() {
		return "ContactsEntity [codUser=" + codUser + ", cargo=" + cargo + ", nascimento=" + nascimento
				+ ", createData=" + createData + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codUser ^ (codUser >>> 32));
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
		ProfessionalsEntity other = (ProfessionalsEntity) obj;
		if (codUser != other.codUser)
			return false;
		return true;
	}

}

package com.exemple.crud.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.exemple.crud.enums.Cargos;

@Component
public class ObjProducer {

	private long ids;
	private String nomes;
	private Cargos cargos;
	private LocalDateTime dataNascimento;
	private LocalDateTime dataCriada;
	private List<String> contatos;

	public ObjProducer() {
		super();
	}

	public ObjProducer(int i, String nome, int cargos, String dataNascimento, String dataCriada, String contatos) {
		super();
	}

	public ObjProducer(long ids, String nomes, Cargos cargos, LocalDateTime dataNascimento, LocalDateTime dataCriada,
			List<String> contatos) {
		super();
		this.ids = ids;
		this.nomes = nomes;
		this.cargos = cargos;
		this.dataNascimento = dataNascimento;
		this.dataCriada = dataCriada;
		this.contatos = contatos;
	}

	public ObjProducer(int i, String nome, String contatos) {
		super();
	}

	public long getIds() {
		return ids;
	}

	public void setIds(long ids) {
		this.ids = ids;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

	public Cargos getCargos() {
		return cargos;
	}

	public void setCargos(Cargos cargos) {
		this.cargos = cargos;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getDataCriada() {
		return dataCriada;
	}

	public void setDataCriada(LocalDateTime dataCriada) {
		this.dataCriada = dataCriada;
	}

	public List<String> getContatos() {
		return contatos;
	}

	public void setContatos(List<String> contatos) {
		this.contatos = contatos;
	}

}

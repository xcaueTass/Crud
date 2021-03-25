package com.exemple.crud.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.crud.models.ObjProdContact;
import com.exemple.crud.repositories.RepositoryContact;

@Service
public class ContactsService {

	@Autowired
	RepositoryContact repository;

	private static final Logger logger = LoggerFactory.getLogger(ContactsService.class);

	@Transactional
	public String registerUser(ObjProdContact objProd) {
		try {
			logger.info("Inserindo novo registro na tabela");

			repository.save(objProd.getId(), objProd.getProfessionalId(), objProd.getNome(), objProd.getContato());

			logger.info("Dados salvo com sucesso");
			return "Dados salvo com sucesso";
		} catch (Exception e) {
			logger.info(String.format("Erro ao savar no banco de dados: %s", e.getMessage()));
			return "Erro ao savar no banco de dados: Não existe dados na base";
		}
	}

	@Transactional
	public String insertUser(long id, ObjProdContact objProd) {

		if (!repository.findById(id).isEmpty()) {

			repository.update(objProd.getId(), objProd.getNome(), objProd.getContato());

			return "Registro alterado com sucesso!";
		} else {
			return "Erro ao savar no banco de dados: Não existe dados na base";
		}

	}

}

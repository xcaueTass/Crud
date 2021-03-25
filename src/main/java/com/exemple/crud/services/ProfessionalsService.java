package com.exemple.crud.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.crud.entities.ProfessionalsEntity;
import com.exemple.crud.models.ObjProducer;
import com.exemple.crud.repositories.RepositoryContact;
import com.exemple.crud.repositories.RepositoryPro;

@Service
public class ProfessionalsService {

	@Autowired
	RepositoryPro repositoryPro;

	@Autowired
	RepositoryContact repoCont;

	private static final Logger logger = LoggerFactory.getLogger(ProfessionalsService.class);

	@Transactional
	public String registerUser(ObjProducer objProd) {
		try {
			logger.info("Inserindo novo registro na tabela");
			ProfessionalsEntity entityPro = new ProfessionalsEntity();

			if (repositoryPro.findById(objProd.getIds()).isPresent()) {

				logger.info("Dados ja existe na base");
				return "Dados ja existe na base";

			} else {

				entityPro.setCodUser(objProd.getIds());
				entityPro.setCargo(objProd.getCargos().getCargo());
				entityPro.setCreateData(LocalDateTime.now());
				entityPro.setNascimento(objProd.getDataNascimento());

				repositoryPro.save(entityPro);
				repoCont.save(objProd.getIds(), objProd.getNomes(), objProd.getContatos().toString());

				logger.info("Dados salvo com sucesso");
				return "Dados salvo com sucesso";
			}
		} catch (Exception e) {
			logger.info(String.format("Erro ao savar no banco de dados: %s", e.getMessage()));
			return String.format("Erro ao savar no banco de dados: %s", e.getMessage());
		}
	}

	@Transactional
	public boolean insertUser(long id, ObjProducer objProd) {

		if (!repositoryPro.findById(id).isEmpty()) {

			repoCont.update(objProd.getIds(), objProd.getNomes(), objProd.getContatos().toString());

			return true;
		} else {
			return false;
		}

	}

}

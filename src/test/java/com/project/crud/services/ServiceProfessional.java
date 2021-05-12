package com.project.caixaeletronico.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exemple.crud.CrudApplication;
import com.exemple.crud.configs.DataSourceConfig;
import com.exemple.crud.entities.ContactsEntity;
import com.exemple.crud.entities.ProfessionalsEntity;
import com.exemple.crud.enums.Cargos;
import com.exemple.crud.models.ObjProducer;
import com.exemple.crud.repositories.RepositoryContact;
import com.exemple.crud.repositories.RepositoryPro;
import com.exemple.crud.services.ProfessionalsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CrudApplication.class, DataSourceConfig.class })
public class ServiceProfessional {

	@Autowired
	RepositoryContact repoCont;

	@Autowired
	ProfessionalsService service;

	RepositoryPro repoPro;

	private static final Logger logger = LoggerFactory.getLogger(ServiceProfessional.class);

	@BeforeEach
	public void init() {

		logger.info("Inserindo dados na entidade");
		try {

			ContactsEntity entityCont = new ContactsEntity();
			ProfessionalsEntity entityPro = new ProfessionalsEntity();

			List<String> list = new ArrayList<String>();
			list.add("teste");

			entityPro.setCodUser(1);
			entityPro.setCargo(Cargos.DEV.getCargo());
			entityPro.setCreateData(LocalDateTime.now());
			entityPro.setNascimento(LocalDateTime.now());

			entityCont.setId(1);
			entityCont.setNome("Caue");
			entityCont.setContato("teste");
			entityCont.setProfissionalId(entityPro);

			logger.info("Salvando na tabela de testes");

			repoPro.save(entityPro);
			repoCont.save(entityCont);

			logger.info("Dados salvo com sucesso");

		} catch (Exception e) {
			logger.error("Erro ao salvar dados: " + e.getMessage());
		}
	}

	@Test
	void testRepositoryRules() {

		logger.info("Efetuando validações do repositorio");
		try {
			assertNotNull(repoPro.findVar());
			assertTrue(repoPro.update(Cargos.DEV.getCargo(), LocalDateTime.now(), LocalDateTime.now()));
			logger.info("Repositorio validado - OK");

		} catch (Exception e) {
			logger.error("Erro ao efetuar as validações de repositório: " + e.getMessage());
		}

	}

	@Test
	void testServiceRules() {
		long id = 1;
		ObjProducer obj = new ObjProducer();
		List<String> list = new ArrayList<String>();
		list.add("teste");
		obj.setIds(1);
		obj.setNomes("caue");
		obj.setContatos(list);
		obj.setCargos(Cargos.DEV);
		obj.setDataNascimento(LocalDateTime.now());
		obj.setDataCriada(LocalDateTime.now());

		logger.info("Efetuando validações da regra de negócio");
		try {

			assertTrue(service.insertUser(id, obj));
			assertEquals("Dados ja existe na base", service.registerUser(obj));

			logger.info("Regra de negócio validada - OK");

		} catch (Exception e) {
			logger.error("Erro ao efetuar as validações de regra de negócio: " + e.getMessage());
		}

	}
}

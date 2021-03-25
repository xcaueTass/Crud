package com.exemple.crud.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exemple.crud.exceptions.ErrorsMsg;
import com.exemple.crud.models.ObjProducer;
import com.exemple.crud.presenters.DataPresenter;
import com.exemple.crud.repositories.RepositoryContact;
import com.exemple.crud.repositories.RepositoryPro;
import com.exemple.crud.services.ProfessionalsService;

/**
 * @author xcaue
 * 
 */

@RestController
@RequestMapping(value = "/professionals")
public class ControllerProfessionals {

	@Autowired
	ProfessionalsService service;

	@Autowired
	ErrorsMsg error;

	@Autowired
	DataPresenter data;

	@Autowired
	RepositoryPro repoPro;

	@Autowired
	RepositoryContact repoCont;

	private static final Logger logger = LoggerFactory.getLogger(ControllerProfessionals.class);

	/**
	 * @param findById - RETURN O profissional que atende ao ID indicado.
	 * 
	 *                 Exemplo: /professionals/1
	 * 
	 */

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {

			return repoPro.findById(id).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());

		} catch (Exception e) {

			String msg = "Não foi possível encontrar nenhum usuario com o id informado";
			logger.error("Não foi possível encontrar nenhum usuario com o id informado");
			return error.errorCustom(msg);
		}
	}

	/**
	 * @param register - RETURN Sucesso profissional cadastrado.
	 * 
	 *                 Exemplo: { "ids" : "1", "nomes": "cauee", "cargos": 0,
	 *                 "dataNascimento" : "1992-11-22T23:50:04", "dataCriada" : " ",
	 *                 "contatos" : ["teste"] }
	 * 
	 */

	@PostMapping("/professional")
	public ResponseEntity<?> register(HttpServletRequest request, @RequestBody ObjProducer objProd) {

		logger.info("Cadastrando novo usuário");
		String returnService = service.registerUser(objProd);
		try {

			logger.info("Dados computados!");
			data.setData(returnService);
			return ResponseEntity.status(HttpStatus.OK).body(data);

		} catch (Exception e) {
			logger.error("Não foi possível salvar os dados");
			return error.errorCustom(returnService);
		}

	}

	/**
	 * @param alterUser - RETURN Altera o profissional que atende ao ID indicado.
	 * 
	 *                  Exemplo: /professionals/1
	 * 
	 *                  { "ids" : "1", "nomes": "cauee", "cargos": 0,
	 *                  "dataNascimento" : "1992-11-22T23:50:04", "dataCriada" : "
	 *                  ", "contatos" : ["teste"] }
	 * 
	 */

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> alterUser(HttpServletRequest request, @PathVariable long id,
			@RequestBody ObjProducer objProd) {

		logger.info("Alterando usuário");
		if (service.insertUser(id, objProd)) {
			logger.info("Dado alterado com sucesso");
			data.setData("Registro alterado com sucesso!");
			return ResponseEntity.status(HttpStatus.OK).body(data);

		} else {

			logger.error("Não foi possível alterar o dado, favor validar as informações para alteração");
			String msg = "Não foi possível alterar o dado, favor validar as informações para alteração";
			return error.errorCustom(msg);
		}
	}

	/**
	 * @param delete - RETURN Sucesso profissional excluído
	 * 
	 *               Exemplo: /professionals/1
	 * 
	 */

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repoPro.findById(id).map(record -> {
			repoCont.deleteById(id);
			repoPro.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}

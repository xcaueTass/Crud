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
import com.exemple.crud.models.ObjProdContact;
import com.exemple.crud.presenters.DataPresenter;
import com.exemple.crud.repositories.RepositoryContact;
import com.exemple.crud.services.ContactsService;

/**
 * @author xcaue
 * 
 */
@RestController
@RequestMapping(value = "/contacts")
public class ContactsController {

	private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

	@Autowired
	ErrorsMsg error;

	@Autowired
	DataPresenter data;

	@Autowired
	RepositoryContact repository;

	@Autowired
	ContactsService service;

	/**
	 * @param findById - Recupera os contato que atende ao ID indicado.
	 * 
	 *                 Exemplo: /contacts/1
	 * 
	 */
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable long id) {
		try {

			return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());

		} catch (Exception e) {

			String msg = "Não foi possível encontrar nenhum usuario com o id informado";
			logger.error("Não foi possível encontrar nenhum usuario com o id informado");
			return error.errorCustom(msg);
		}
	}

	/**
	 * @param register - RETURN Sucesso contato cadastrado.
	 * 
	 *                 Exemplo: { "id" : 3, "nome" : "Caue", "contato": "111111111",
	 *                 "professionalId" : 1 }
	 * 
	 */
	@PostMapping("/contact")
	public ResponseEntity<?> register(HttpServletRequest request, @RequestBody ObjProdContact objProd) {

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
	 * @param alterUser - RETURN Altera o contato que atende ao ID indicado.
	 * 
	 *                  Exemplo: /contacts/1
	 * 
	 *                  { "ids" : "1", "nomes": "cauee", "cargos": 0,
	 *                  "dataNascimento" : "1992-11-22T23:50:04", "dataCriada" : "
	 *                  ", "contatos" : ["teste"] }
	 */

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> alterUser(HttpServletRequest request, @PathVariable long id,
			@RequestBody ObjProdContact objProd) {

		logger.info("Alterando usuário");

		String returnService = service.insertUser(id, objProd);
		try {

			logger.info("Dado alterado com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(returnService);
		} catch (Exception e) {
			logger.error("Não foi possível alterar o dado");
			return error.errorCustom(returnService);

		}
	}

	/**
	 * @param alterUser - RETURN Sucesso contato excluído
	 * 
	 *                  Exemplo: /contacts/1
	 * 
	 */

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}

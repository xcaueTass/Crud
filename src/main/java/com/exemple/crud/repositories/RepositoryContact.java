package com.exemple.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exemple.crud.entities.ContactsEntity;

public interface RepositoryContact extends JpaRepository<ContactsEntity, Long> {

	@Modifying
	@Query(value = "insert into contacts_table (id, contato, nome, profissional_id) values (:id, :contatos, :nomes, :id)", nativeQuery = true)
	void save(long id, String nomes, String contatos);

	@Modifying
	@Query(value = "insert into contacts_table (id, contato, nome, profissional_id) values (:id, :contatos, :nomes, :professionalId)", nativeQuery = true)
	void save(long id, long professionalId, String nomes, String contatos);

	@Modifying
	@Query(value = "UPDATE contacts_table SET CONTATO = :contatos, NOME = :nomes WHERE id = :id and PROFISSIONAL_ID = :id", nativeQuery = true)
	void update(long id, String nomes, String contatos);

}

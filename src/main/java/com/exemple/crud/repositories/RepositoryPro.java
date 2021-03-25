package com.exemple.crud.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemple.crud.entities.ProfessionalsEntity;

@Repository
public interface RepositoryPro extends JpaRepository<ProfessionalsEntity, Long> {

	@Query(value = "UPDATE contacts_table SET CARGO = :cargo, CREATE_DATA = :now, NASCIMENTO = :dataNascimento WHERE id = :id", nativeQuery = true)
	boolean update(String cargo, LocalDateTime now, LocalDateTime dataNascimento);

	@Query(value = "select nome, contato, cargo, create_data, nascimento from contacts_table inner join profissional_table", nativeQuery = true)
	Optional<ProfessionalsEntity> findVar();

}

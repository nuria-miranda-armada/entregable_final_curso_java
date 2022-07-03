package com.curso.superheroe.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.superheroe.entity.Superheroe;

@Repository
public interface SuperheroeRepoJpa extends JpaRepository<Superheroe, Integer> {

	List<Superheroe>findByNombreContaining(String nombre);
	

}

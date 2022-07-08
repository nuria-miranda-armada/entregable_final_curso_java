package com.curso.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.curso.entity.Poder;
import com.curso.entity.Superheroe;

@Repository
public interface SuperheroeRepoJpa extends JpaRepository<Superheroe, Integer> {

	List<Superheroe>findByNombreContaining(String nombre);
	List<Superheroe> findSuperheroesByPoderesId(Integer poderId);
}
//TODO: meter una query con Poder isNotEmpty ppara poder crear un metodo para eliminar los poderes
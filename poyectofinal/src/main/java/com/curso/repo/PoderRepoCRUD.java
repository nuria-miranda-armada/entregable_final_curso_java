package com.curso.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.entity.Poder;

@Repository
public interface PoderRepoCRUD extends CrudRepository<Poder, Integer>{
	 List<Poder> findPoderesBySuperheroesId(Integer superheroeId);
}

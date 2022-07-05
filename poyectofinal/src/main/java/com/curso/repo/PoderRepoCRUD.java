package com.curso.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.entity.Poder;

@Repository
public interface PoderRepoCRUD extends CrudRepository<Poder, Integer>{

}

package com.curso.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.entity.Universo;

@Repository
public interface UniversoRepoSpringCRUD extends CrudRepository<Universo, Integer>{

}

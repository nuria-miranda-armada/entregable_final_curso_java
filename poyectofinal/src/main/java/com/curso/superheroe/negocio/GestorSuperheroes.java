package com.curso.superheroe.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.superheroe.entity.Superheroe;
import com.curso.superheroe.repo.SuperheroeRepoJpa;



@Service
public class GestorSuperheroes {
	//INFO PARA NURIA: El que puede usar el optional es el crud y jpa recordemos la <T> :)
	//Hibernate vas a tener que crear la interfaz tu D;
	//JPA facil y sencillo e igual que crud  ;)
	@Autowired
	private SuperheroeRepoJpa superheroeRepo;
	
	public List<Superheroe> findAllSuperheroes(){
		return superheroeRepo.findAll();
	}
	
	
	public Optional<Superheroe>getSuperheroById(Integer id){
		return superheroeRepo.findById(id);
	}
	
	public Superheroe crear(Superheroe superheroe){
		return superheroeRepo.save(superheroe);
		
	}
	
	

}

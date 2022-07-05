package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.repo.SuperheroeRepoJpa;

@Service
public class GestorSuperheroes {
	
	@Autowired
	private SuperheroeRepoJpa superheroeRepo;
	
	public List<Superheroe> findAllSuperheroes(){
		return superheroeRepo.findAll();
	}
		
	public Optional<Superheroe>findSuperById(Integer id){
		return superheroeRepo.findById(id);
	}
	
	public Superheroe create(Superheroe superheroe){
		return superheroeRepo.save(superheroe);
		
	}
	
	public List<Superheroe>findByNombreContainingService(String nombre){
		return superheroeRepo.findByNombreContaining(nombre);
	}
	

	
	public Superheroe updateState(Integer id,String estado){
		Optional<Superheroe> superOp = findSuperById(id);
		//superOp.get().setEstado(estado);
		
		Superheroe superheroe = superOp.orElseThrow(()-> new IllegalArgumentException());
		superheroe.setEstado(estado);
		 return superheroeRepo.save(superheroe);
	
		//TODO: Lanzar excepcion
	
	}
	
	//Opcion 1 borrar
	public void DeleteSuperById(Integer id){
		superheroeRepo.deleteById(id);
		
	}
	
	//OPCION 2, AQUI CONTROLO YO LA EXCEPCIÓN
	public void delete(Integer id) {
		Superheroe superheroe = this.findSuperById(id)
				.orElseThrow(()-> new IllegalArgumentException());
		superheroeRepo.delete(superheroe);
	}
	
	
	public Superheroe updateSuper(Integer id,Superheroe superDatos){
		Optional<Superheroe> superOp = findSuperById(id);
		//pasamos el optional a un obj de tipo superheroe
		Superheroe superheroe = superOp.orElseThrow(()-> new IllegalArgumentException());
		//pasamos los datos que nos llegan por parametro al superheroe
		superheroe.setNombre(superDatos.getNombre());
		superheroe.setEstado(superDatos.getEstado());
		superheroe.setUniverso(superDatos.getUniverso());
		superheroe.setPoderes(superDatos.getPoderes());
		return superheroeRepo.save(superheroe);
	}
	
	

}

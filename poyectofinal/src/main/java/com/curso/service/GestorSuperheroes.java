package com.curso.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.entity.Poder;
import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.exceptions.ResourceInUseException;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.SuperheroeRepoJpa;
import com.curso.repo.UniversoRepoHibernate;

@Service
public class GestorSuperheroes {
	
	@Autowired
	private SuperheroeRepoJpa superheroeRepo;
	
	@Autowired
	private UniversoRepoHibernate universoRepo;
	
	public List<Superheroe> findAllSuperheroes(){
		return superheroeRepo.findAll();
	}
		
	public Superheroe findSuperById(Integer id){
		return superheroeRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encuentra el Poder con este id: " + id));
	}
	
	public Superheroe old_create(Superheroe superheroe){
		return superheroeRepo.save(superheroe);
		
	}
	
	public Superheroe create(Superheroe superheroe) {
	    if (superheroe.getUniverso() != null && superheroe.getUniverso().getId() != null) {
	        // Ensure Universo exists in DB before saving Superheroe
	        Universo existingUniverso = universoRepo.findById(superheroe.getUniverso().getId());
	        
	        if (existingUniverso == null) {
	            throw new ResourceNotFoundException("Universo no encontrado con ID: " + superheroe.getUniverso().getId());
	        }

	        superheroe.setUniverso(existingUniverso); // Attach existing Universo
	    }
	    
	    if (superheroe.getPoderes() == null) {
	        superheroe.setPoderes(new HashSet<>());
	    }
	    
	    
	    return superheroeRepo.save(superheroe);
	}
	
	
	public List<Superheroe>findByNombreContainingService(String nombre){
		return superheroeRepo.findByNombreContaining(nombre);
	}
	

	
	public Superheroe updateState(Integer id,String estado){
		Superheroe superheroe = findSuperById(id);
		//superOp.get().setEstado(estado);
		superheroe.setEstado(estado);
		 return superheroeRepo.save(superheroe);
	
	}
	
	/*
	public void DeleteSuperById(Integer id){
		superheroeRepo.deleteById(id);
		
	}*/
	
	//opcion de borrar donde se controla la excepcion
	public void delete(Integer id) {
		Superheroe superheroe = this.findSuperById(id);
			
		if(!superheroe.getPoderes().isEmpty()) {
			 throw new ResourceInUseException("Este superheroe está asignado a poderes. "
			 		+ "Debes primero quitarle los poderes. Puedes hacerlo con update");
		
			}
		superheroeRepo.delete(superheroe);
		//}
	}
	
	
	public Superheroe updateSuper(Integer id,Superheroe superDatos){
		Superheroe superheroe  = findSuperById(id);
		//pasamos el optional a un obj de tipo superheroe
		//pasamos los datos que nos llegan por parametro al superheroe
		superheroe.setNombre(superDatos.getNombre());
		superheroe.setEstado(superDatos.getEstado());
		superheroe.setUniverso(superDatos.getUniverso());
		superheroe.setPoderes(superDatos.getPoderes());
		return superheroeRepo.save(superheroe);
	}
	
	
	
	


}

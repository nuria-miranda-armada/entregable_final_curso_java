package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.entity.Poder;
import com.curso.entity.Superheroe;
import com.curso.exceptions.ResourceInUseException;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.PoderRepoCRUD;
import com.curso.repo.SuperheroeRepoJpa;

@Service
public class GestorPoder {
 //TODO: crear excepciones de recurso no encontrado y subtituir IllegalArg
	@Autowired
	private PoderRepoCRUD poderRepoService;
		
	public List<Poder> findAllPoderes(){
		return (List<Poder>) poderRepoService.findAll();
	}
		
	public Poder findPoderById(Integer id){
		return poderRepoService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encuentra el Poder con este id: " + id));
	}
	
	public Poder create(Poder poder){
		return poderRepoService.save(poder);
	}
	
	public void delete(Integer id) {
		Poder p = this.findPoderById(id);
		if(!p.getSuperheroes().isEmpty()) {
		 throw new ResourceInUseException("Este poder está asignado a superhereoes, no se puede borrar  " + p.getSuperheroes());
	
		}
		poderRepoService.delete(p);
	}
	
	public Poder updatePoder(Integer id,Poder poderDatos){
		Poder poder = findPoderById(id);
		//pasamos el optional a un obj de tipo poder
		//Poder poder = poderOp.orElseThrow(()-> new ResourceNotFoundException("No se encuentra el Poder con este id: " + id));
		//pasamos los datos que nos llegan por parametro al poder
		poder.setNombre(poderDatos.getNombre());
		
		return poderRepoService.save(poder);
	}
	
	
}

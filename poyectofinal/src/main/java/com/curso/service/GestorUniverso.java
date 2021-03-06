package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.entity.Poder;
import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.exceptions.ResourceInUseException;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.UniversoRepoHibernateImplementation;
import com.curso.repo.UniversoRepoSpringCRUD;

@Service 
public class GestorUniverso {
//SE INYECTA HIBERNATE?????? 
	//TODO: Revision de métodos
	@Autowired
	private UniversoRepoSpringCRUD universoRepoCRUD;
	@Autowired
	UniversoRepoHibernateImplementation universoRepoHB;

	public List<Universo> findAllUniversos(){
		return universoRepoHB.findAll();
	}
	
	public List<Universo> findAllUniversosCRUD(){
		return  (List<Universo>) universoRepoCRUD.findAll();
	}
	
	public Universo findbyId(Integer id) {
		return universoRepoHB.findbyId(id);
				
	}
	
	public Universo findByIdCRUD(Integer id){
		return universoRepoCRUD.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encuentra el Poder con este id: " + id));
	}
	
	//BORRAR POR ID
	/*
	public void delete(Integer id){
		Universo universo = this.findbyId(id);
		universoRepoHB.delete(universo);
	}*/
	
	public List<Universo>findByNombre(String nombre){
		return universoRepoHB.findbyNombre(nombre);
	}
	
	/*
	public Universo guardar(Universo universo) {
		return universoRepoHB.guardar(universo);
	}*/
	
	public Universo create(Universo universo) {
		return universoRepoCRUD.save(universo);
	}
	
	public void delete(Integer id) {
		Universo univ = this.findByIdCRUD(id);
						
	  universoRepoCRUD.delete(univ);
	}
	
	
	public Universo updateUniverso(Integer id, Universo universoDatos){
		Universo universo = findByIdCRUD(id);
		//pasamos el optional a un obj de tipo superheroe
		
		//pasamos los datos que nos llegan por parametro al superheroe
		universo.setNombre(universoDatos.getNombre());
		return universoRepoCRUD.save(universo);
	}
	
	
	
	
	
}

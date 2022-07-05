package com.curso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.repo.SuperheroeRepoJpa;
import com.curso.service.GestorSuperheroes;


//CONTROLLER DE SUPERHEROE
//AQUI USAMOS JPA
@RestController
@RequestMapping("/superheroe")
public class SuperHeroeController{
	
	@Autowired
	private GestorSuperheroes superheroeService;

	@GetMapping("/myapp/{name}")
	public String MostrarAlgo(@PathVariable String name){
		return "Hola "+name;	
	}
	
	@GetMapping("/request")
	public String Request(@RequestParam String name){
		return "Hola "+name;	
	}
	
	//OBTENCIÓN LISTA SUPERHEROES
	@GetMapping("/all")
	 public List <Superheroe> getAllSuperheroes(){
		return superheroeService.findAllSuperheroes();
	}
	
	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Superheroe> getById(@PathVariable Integer id){
		return superheroeService.findSuperById(id);
	}
	
	
	@GetMapping("/name/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Superheroe> getByNombrename(@PathVariable String nombre){
		return superheroeService.findByNombreContainingService(nombre);
	}

	
	//AÑADIR SUPER
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public Superheroe create(@RequestBody Superheroe superheroe) {
		return superheroeService.create(superheroe);
	}
	
	
	
	//OPCION MATAR
	@PutMapping("/matar/{id}")
	public Superheroe matar(@PathVariable Integer id) {
		return superheroeService.updateState(id, "muerto");
	}
	
	
	//OPCION REVIVIR4
	@PutMapping("/revivir/{id}")
	public Superheroe revivir(@PathVariable Integer id) {
		return superheroeService.updateState(id, "vivo");
	}
	
	
	/*
	@DeleteMapping("/delete/{id}")
	public void borrar(@PathVariable Integer id) {
		 superheroeService.DeleteSuperById(id);
	}*/
	
	//OPCION PARA BORRAR QUE PERMITE CONTROL SE LA EXCEPCION
	@DeleteMapping("/delete/{id}")
	public void borrar3(@PathVariable Integer id) {
		 superheroeService.delete(id);
	} 
	
	//ACTUALIZAR SUPERHEROE
	@PutMapping("/update/{id}")
	public Superheroe updateSuperheroe(@PathVariable Integer id,
			@RequestBody Superheroe superDatosActualizar) {
		 return superheroeService.updateSuper(id,superDatosActualizar);
	}
	
}

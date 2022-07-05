package com.curso.superheroe.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.curso.superheroe.entity.Superheroe;
import com.curso.superheroe.negocio.GestorSuperheroes;
import com.curso.superheroe.repo.SuperheroeRepoJpa;


//CONTROLLER DE SUPERHEROE
//AQUI USAMOS JPA
@RestController
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
	@GetMapping("/superheroes")
	 public List <Superheroe> getAllSuperheroes(){
		final List<Superheroe> resultadoBaseDatos = superheroeService.findAllSuperheroes();
		return  resultadoBaseDatos;
	
	}
	
	@GetMapping("/id/{id}")
	public Optional<Superheroe> getById(@PathVariable Integer id){
		final Optional <Superheroe> resultadoBD = superheroeService.findSuperById(id);
		return resultadoBD;
			
	}
	
	
	@GetMapping("/name/{nombre}")
	public List<Superheroe> getByNombrename(@PathVariable String nombre){
		return superheroeService.findByNombreContainingService(nombre);
		
	}

	
	//AÑADIR SUPER
	@PostMapping("/addSuper")
	@ResponseStatus(HttpStatus.OK)
	public Superheroe crearSuperheroe(@RequestBody Superheroe superheroe) {
		return superheroeService.crear(superheroe);
	}
	
	
	
	//OPCION MATAR
	@PutMapping("/matar/id/{id}")
	public Superheroe matar(@PathVariable Integer id) {
		return superheroeService.updateState(id, "muerto");
	}
	
	
	//OPCION REVIVIR4
	@PutMapping("/revivir/id/{id}")
	public Superheroe revivir(@PathVariable Integer id) {
		return superheroeService.updateState(id, "vivo");
	}
	
	
	//OPCION BORRAR
	@DeleteMapping("/del/{id}")
	public void borrar(@PathVariable Integer id) {
		 superheroeService.DeleteSuperById(id);
	}
	
	//ACTUALIZAR SUPERHEROE
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Superheroe updateSuperheroe(@PathVariable Integer id,@RequestBody Superheroe superDatosActualizar) {
		return superheroeService.updateSuper(id,superDatosActualizar);
	}
	
}

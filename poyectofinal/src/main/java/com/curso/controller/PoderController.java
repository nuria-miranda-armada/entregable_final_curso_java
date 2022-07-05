package com.curso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.entity.Poder;
import com.curso.service.GestorPoder;

@RestController
@RequestMapping("/poder")
public class PoderController {

	@Autowired
	private GestorPoder poderService;
	
	//OBTENCIÓN LISTA SUPERHEROES
	@GetMapping("/all")
	public List <Poder> getAll(){
		return poderService.findAllPoderes();
	}
		
	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Poder> getById(@PathVariable Integer id){
		return poderService.findPoderById(id);
	}
	
	//AÑADIR PODER
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public Poder create(@RequestBody Poder poder) {
		return poderService.create(poder);
	}	
	
	//OPCION PARA BORRAR DONDE YO CONTROLO LA EXCEPCION
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		poderService.delete(id);
	} 
	
	//ACTUALIZAR PODER
	@PutMapping("/update/{id}")
	public Poder updatePoder(@PathVariable Integer id,
	@RequestBody Poder poderDatosActualizar) {
		return poderService.updatePoder(id,poderDatosActualizar);
	}
	
}

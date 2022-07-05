package com.curso.controller;

import java.util.List;

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

import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.service.GestorUniverso;

@RestController 
@RequestMapping("/universo")
public class UniversoController {

	@Autowired
	private GestorUniverso universoService;
	
	@GetMapping("/all")
	public List<Universo> findAll(){
		return universoService.findAllUniversos();
	}
	
	@GetMapping("/allcrud")
	public List<Universo> findAllCRUD(){
		return universoService.findAllUniversosCRUD();
	}
	
	@PostMapping("/add")
	public Universo create(@RequestBody Universo universo) {
		return universoService.create(universo);
	}

	//OPCION PARA BORRAR DONDE YO CONTROLO LA EXCEPCION
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		universoService.delete(id);
	} 
	
	//ACTUALIZAR UNIVERSO
	@PutMapping("/update/{id}")
	public Universo updateUniverso(@PathVariable Integer id,
			@RequestBody Universo universoDatosActualizar) {
		 return universoService.updateUniverso(id,universoDatosActualizar);
	}
}
package com.curso.superheroe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		
	
	
}

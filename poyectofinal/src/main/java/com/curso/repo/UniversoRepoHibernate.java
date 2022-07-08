package com.curso.repo;

import java.util.List;

import com.curso.entity.Universo;


//INTERFAZ REPO
public interface UniversoRepoHibernate {
	List<Universo> findAll();
	Universo findbyId(Integer id);
	List<Universo> findbyNombre(String nombre);
	List<Universo> findNombreContaining(String nombre);
	Universo guardar(Universo universo);
	Universo crear(Universo universo);
	Universo updateUniverso(Integer id, Universo universoDatos);
	void DeleteUniverso(Integer id);
	void delete(Universo universo);
}

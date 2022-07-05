package com.curso.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.curso.entity.Universo;

//EL REPOSITORIO CREO QUE ESTÁ LISTO
@Repository
public class UniversoRepoHibernateImplementation implements UniversoRepoHibernate{

	@PersistenceContext 
	private EntityManager em;
	
	//LISTA UNIVERSOS
	public List<Universo> findAll(){
		return em.createQuery("FROM Universo",Universo.class).getResultList();
	}

	//BUSCA UNIVERSO POR ID
	@Override
	public Universo findbyId(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Universo.class, id);
	}

	@Override
		public List <Universo> findbyNombre(String nombre) {
		// TODO Auto-generated method stub
		return em.createQuery("FROM universo uni WHERE uni.nombre = :nombre", Universo.class)
				.setParameter("nombre",nombre).getResultList();
	}

	@Override
	public List<Universo> findNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//merge guarda los datos si ya existen
	public Universo guardar(Universo universo) {
		em.merge(universo);
		return universo; 
	}

	@Override
	public Universo crear(Universo universo) {
		em.persist(universo); //persist si los datos ya existen lanza una excepcion.
		return universo; 
	}

	@Override
	public Universo updateUniverso(Integer id, Universo universoDatos) {
		em.merge(universoDatos);
		return universoDatos;
	}

	@Override
	public void DeleteUniverso(Integer id) {
		Universo universo = em.find(Universo.class, id);
		em.remove(universo);
	}
	
	@Override
	public void delete(Universo universo) {
		em.remove(universo);
	}
	
	
	
	
	
}

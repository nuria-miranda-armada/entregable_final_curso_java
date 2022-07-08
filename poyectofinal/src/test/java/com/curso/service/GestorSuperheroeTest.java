package com.curso.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.entity.Poder;
import com.curso.entity.Superheroe;
import com.curso.entity.Universo;
import com.curso.exceptions.ResourceInUseException;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.SuperheroeRepoJpa;

@ExtendWith(MockitoExtension.class)
public class GestorSuperheroeTest{
	
	@InjectMocks
	GestorSuperheroes service;
	
	@Mock
	SuperheroeRepoJpa repository;
	
	@Test 
	void findAllUniversosCRUDTest() {
		
		//definición variables de entrada y resultado.
		List<Superheroe> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Superheroe.class));
		expectedResult.add(Mockito.mock(Superheroe.class));
		//comportamiento esperado
		when(repository.findAll()).thenReturn(expectedResult);
		
		//llamada a método a testear
		List<Superheroe>currentResult = service.findAllSuperheroes();
		//comprobaciones del resultado
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).size().isEqualTo(2);
}


	@Test
	void findSuperByIdTest() {
		
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Integer idParam = Integer.valueOf(2);
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		Superheroe currentResult = service.findSuperById(idParam);
		assertThat(currentResult).isEqualTo(expectedResult);
	}
	


	@Test
	void findSuperByIdErrorTest () {
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Integer idParam = Integer.valueOf(20);
		Superheroe expectedResult = superh;
		//llamada a método a testear
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear//comprobaciones del resultado
			assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.findSuperById(idParam));
	}
	
	@Test
	void findSuperByNameContaining() {
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Superheroe superh2 = new Superheroe();
		superh.setId(1);
		superh.setNombre("Pepa");
		List<Superheroe> expectedResult = new ArrayList<>();
		expectedResult.add(superh);
		expectedResult.add(superh2);
		
		String nombre = "a";
		//comportamiento esperado
		when(repository.findByNombreContaining(nombre)).thenReturn(expectedResult);
		List <Superheroe> currentResult = service.findByNombreContainingService(nombre);
		assertThat(currentResult).isEqualTo(expectedResult);
	}
	


	@Test
	void findSuperByNameContaining_EmptyTest () {
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Pepe");
		
		List<Superheroe> expectedResult = new ArrayList<>();
		String nombre = "a";
		//llamada a método a testear
		//comportamiento esperado
		when(repository.findByNombreContaining(nombre)).thenReturn(expectedResult);
		//llamada a método a testear//comprobaciones del resultado
		List <Superheroe> currentResult = service.findByNombreContainingService(nombre);
		assertThat(currentResult).isEqualTo(expectedResult);
	}
	
	@Test
	void createTest() {
		Superheroe superh = Mockito.mock(Superheroe.class);
		//llamada a método a testear
		//comportamiento esperado
		when(repository.save(superh)).thenReturn(superh);
		//llamada a método a testear
		Superheroe currentResult = service.create(superh);
		//comprobaciones del resultado
		assertThat(currentResult).isEqualTo(superh);
	}
	

	@Test
	void updateStateTest() {
		String estado = "muerto";
		Integer idParam = Integer.valueOf(2);
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setEstado("vivo");
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		when(repository.save(expectedResult)).thenReturn(expectedResult);
		//llamada a método a testear
		Superheroe currentResult = service.updateState(idParam, estado);
		expectedResult.setEstado(estado);
		//comportamiento esperado
		assertThat(currentResult).isEqualTo(expectedResult);
		assertThat(currentResult.getEstado()).isEqualTo(expectedResult.getEstado());
	}
	

	@Test
	void updateStateErrorTest() {
		String estado = "muerto";
		Integer idParam = Integer.valueOf(20);
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//Poder poderResult = service.findPoderById(idParam);
			
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.updateState(idParam,estado));
	}
	
	//METODO UPDATESUPER
	@Test
	void updateSuperTest() {
		Superheroe datosSuper = new Superheroe();
		datosSuper.setNombre("Pepa");
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		superh.setEstado("vivo");
		superh.setUniverso(Mockito.mock(Universo.class));
		Integer idParam = Integer.valueOf(2);
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//llamada a método a testear
		//comportamiento esperado
		when(repository.save(expectedResult)).thenReturn(expectedResult);
		
		Superheroe currentResult = service.updateSuper(idParam, datosSuper);
		expectedResult.setNombre(datosSuper.getNombre());
		expectedResult.setEstado(datosSuper.getEstado());
		expectedResult.setUniverso(datosSuper.getUniverso());
		//expectedResult.setPoderes(datosSuper.getPoderes());
		//comportamiento esperado
		//Poder currentResult = service.updatePoder(idParam, poderResult);
		assertThat(currentResult).isEqualTo(expectedResult);
		assertThat(currentResult.getNombre()).isEqualTo(expectedResult.getNombre());
		assertThat(currentResult.getEstado()).isEqualTo(expectedResult.getEstado());
	}
	
	@Test
	void updateSuperErrorTest() {
		Superheroe datosSuper = new Superheroe();
		datosSuper.setNombre("Isabel");
		Integer idParam = Integer.valueOf(20);
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//Poder poderResult = service.findPoderById(idParam);
			
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.updateSuper(idParam,datosSuper));
	}

	
	@Test 
	void deleteSuperheroe() {
	
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Integer idParam = Integer.valueOf(2);
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//assertThat(expectedResult).isEqualTo(poderResult);
		service.delete(idParam);
		Mockito.verify(repository,times(1)).delete(expectedResult);
		//Mockito.verify(service,times(1)).delete(idParam);
	}
	
	@Test
	void deleteSuperheroeResourceNotFoundExceptionTest() {
		
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Integer idParam = Integer.valueOf(2);
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear y comportamiento esperado
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.delete(idParam));
				
	}
	
	
	@Test 
	void deleteSuperheroeResourceInUseExceptionError() {
		
		Superheroe superh = new Superheroe();
		superh.setId(2);
		superh.setNombre("Maria");
		Poder poder = Mockito.mock(Poder.class);
		Set<Poder> poderes = new HashSet<>();
		poderes.add(poder);
		superh.setPoderes(poderes);
		Integer idParam = Integer.valueOf(2);
		Superheroe expectedResult = superh;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//comprobaciones del resultado
		assertThatExceptionOfType(ResourceInUseException.class)
		.isThrownBy(()->service.delete(idParam));
	}
	
}

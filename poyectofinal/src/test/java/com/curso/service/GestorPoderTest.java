package com.curso.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
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
import com.curso.exceptions.ResourceInUseException;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.PoderRepoCRUD;
import com.curso.service.GestorPoder;
@ExtendWith(MockitoExtension.class)
public class GestorPoderTest {
//PRUEBA DEL SERVICE
	@InjectMocks
	GestorPoder service; //Servicio a testear
	
	@Mock
	PoderRepoCRUD repository;//inyeccion de dependencias necesaria
	
	
	public 
	@Test 
	void findAllPoderesTest() {
		
		//definición variables de entrada y resultado.
		List<Poder> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Poder.class));
		expectedResult.add(Mockito.mock(Poder.class));
		//comportamiento esperado
		when(repository.findAll()).thenReturn(expectedResult);
		
		//llamada a método a testear
		List<Poder>currentResult = service.findAllPoderes();
		//comprobaciones del resultado
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).size().isEqualTo(2);
}
	
	@Test
	void findPoderByIdTest() {
		//definición variables de entrada y resultado.
		
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Integer idParam = Integer.valueOf(2);
		Poder expectedResult = poder;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		Poder currentResult = service.findPoderById(idParam);
		assertThat(currentResult).isEqualTo(expectedResult);
	}
	
	@Test
	void findPoderByIdErrorTest () {
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Integer idParam = Integer.valueOf(20);
		Poder expectedResult =poder ;
		//llamada a método a testear
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear
		
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.findPoderById(idParam));
	}
	
	@Test
	void createTest() {
	
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		
		//llamada a método a testear
		//comportamiento esperado
		when(repository.save(poder)).thenReturn(poder);
		//llamada a método a testear
		Poder currentResult = service.create(poder);
		//comprobaciones del resultado
		assertThat(currentResult).isEqualTo(poder);
	}
	
	
	
	
	@Test
	void updatePoderTest() {
		Poder datosPoder = new Poder();
		datosPoder.setNombre("teletransporte");
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Integer idParam = Integer.valueOf(2);
		Poder expectedResult = poder;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//llamada a método a testear
		//comportamiento esperado
		when(repository.save(expectedResult)).thenReturn(expectedResult);
		
		Poder currentResult = service.updatePoder(idParam, datosPoder);
		expectedResult.setNombre(datosPoder.getNombre());
		//comportamiento esperado
		//Poder currentResult = service.updatePoder(idParam, poderResult);
		assertThat(currentResult).isEqualTo(expectedResult);
		assertThat(currentResult.getNombre()).isEqualTo(expectedResult.getNombre());
	}
	
	
	
	@Test
	void updatePoderErrorTest() {
		Poder datosPoder = new Poder();
		datosPoder.setNombre("teletransporte");
		Integer idParam = Integer.valueOf(20);
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//Poder poderResult = service.findPoderById(idParam);
			
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.updatePoder(idParam,datosPoder));
	}

	
	@Test 
	void deletePoder() {
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Integer idParam = Integer.valueOf(2);
		Poder expectedResult = poder;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//assertThat(expectedResult).isEqualTo(poderResult);
		service.delete(idParam);
		Mockito.verify(repository,times(1)).delete(expectedResult);
		//Mockito.verify(service,times(1)).delete(idParam);
	}

	@Test
	void deletePoderResourceNotFoundTest() {
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Integer idParam = Integer.valueOf(20);
		Poder expectedResult =poder ;
		//llamada a método a testear
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear
		
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.delete(idParam));
		
		
	}
	
	@Test 
	void deletePoderResourceInUseExceptionError() {
		Poder poder = new Poder();
		poder.setId(2);
		poder.setNombre("volar");
		Superheroe superh = Mockito.mock(Superheroe.class);
		Set<Superheroe> superheroes = new HashSet<>();
		superheroes.add(superh);
		poder.setSuperheroes(superheroes);
		Integer idParam = Integer.valueOf(2);
		Poder expectedResult = poder;
		//comportamiento esperado
		when(repository.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//comprobaciones del resultado
		assertThatExceptionOfType(ResourceInUseException.class)
		.isThrownBy(()->service.delete(idParam));
	}
}

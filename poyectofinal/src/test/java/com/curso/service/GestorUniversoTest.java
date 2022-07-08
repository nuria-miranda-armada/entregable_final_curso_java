package com.curso.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.entity.Poder;
import com.curso.entity.Universo;
import com.curso.exceptions.ResourceNotFoundException;
import com.curso.repo.UniversoRepoHibernateImplementation;
import com.curso.repo.UniversoRepoSpringCRUD;

@ExtendWith(MockitoExtension.class)
public class GestorUniversoTest {

	@InjectMocks
	GestorUniverso service;
	
	@Mock
	UniversoRepoSpringCRUD universoRepoCRUD;
	
	@Mock
	UniversoRepoHibernateImplementation universoRepoHB;
	
	
	@Test 
	void findAllUniversosTest() {
		
		//definición variables de entrada y resultado.
		List<Universo> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Universo.class));
		expectedResult.add(Mockito.mock(Universo.class));
		//comportamiento esperado
		when(universoRepoHB.findAll()).thenReturn(expectedResult);
		
		//llamada a método a testear
		List<Universo>currentResult = service.findAllUniversos();
		//comprobaciones del resultado
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).size().isEqualTo(2);
}
	
	@Test 
	void findAllUniversosCRUDTest() {
		
		//definición variables de entrada y resultado.
		List<Universo> expectedResult = new ArrayList<>();
		expectedResult.add(Mockito.mock(Universo.class));
		expectedResult.add(Mockito.mock(Universo.class));
		//comportamiento esperado
		when(universoRepoCRUD.findAll()).thenReturn(expectedResult);
		
		//llamada a método a testear
		List<Universo>currentResult = service.findAllUniversosCRUD();
		//comprobaciones del resultado
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).size().isEqualTo(2);
}
	
	@Test
	void findUniversoByIdTest() {
		//definición variables de entrada y resultado.
		
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		Integer idParam = Integer.valueOf(2);
		Universo expectedResult = univ;
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.of(expectedResult));
		Universo currentResult = service.findByIdCRUD(idParam);
		assertThat(currentResult).isEqualTo(expectedResult);
	}
	
	@Test
	void findUniversoByIdErrorTest () {
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		Integer idParam = Integer.valueOf(20);
		//llamada a método a testear
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear
		
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.findByIdCRUD(idParam));
	}
	
	
	@Test
	void createTest() {
	
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		//llamada a método a testear
		//comportamiento esperado
		when(universoRepoCRUD.save(univ)).thenReturn(univ);
		//llamada a método a testear
		Universo currentResult = service.create(univ);
		//comprobaciones del resultado
		assertThat(currentResult).isEqualTo(univ);
	}
	

	
	@Test
	void updateUniversoTest() {
		Universo datosUniverso = new Universo();
		datosUniverso.setNombre("patata");
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		Universo expectedResult = univ;
		Integer idParam = Integer.valueOf(2);
		
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//llamada a método a testear
		//comportamiento esperado
		when(universoRepoCRUD.save(expectedResult)).thenReturn(expectedResult);
		
		Universo currentResult = service.updateUniverso(idParam, datosUniverso);
		expectedResult.setNombre(datosUniverso.getNombre());
		//comportamiento esperado
	
		assertThat(currentResult).isEqualTo(expectedResult);
		assertThat(currentResult.getNombre()).isEqualTo(expectedResult.getNombre());
	}
	

	@Test
	void updateUniversoErrorTest() {
		Universo datosUniverso = new Universo();
		datosUniverso.setNombre("patata");
		Integer idParam = Integer.valueOf(20);
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.empty());
		
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.updateUniverso(idParam,datosUniverso));
	}
	
	@Test 
	void deletePoder() {
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		Integer idParam = Integer.valueOf(2);
		Universo expectedResult = univ;
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.of(expectedResult));
		//Poder poderResult = service.findPoderById(idParam);
		//assertThat(expectedResult).isEqualTo(poderResult);
		service.delete(idParam);
		Mockito.verify(universoRepoCRUD,times(1)).delete(expectedResult);
		//Mockito.verify(service,times(1)).delete(idParam);
	}

	@Test
		void deletePoderResourceNotFoundTest() {
		Universo univ = new Universo();
		univ.setId(2);
		univ.setNombre("DC");
		Integer idParam = Integer.valueOf(20);
		Universo expectedResult = univ;
		//comportamiento esperado
		when(universoRepoCRUD.findById(idParam)).thenReturn(Optional.empty());
		//llamada a método a testear
		
		assertThatExceptionOfType(ResourceNotFoundException.class)
		.isThrownBy(()->service.delete(idParam));
			
	}
	
	

	
}

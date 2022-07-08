package com.curso.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import com.curso.PoyectofinalApplication;
import com.curso.entity.Superheroe;

@SpringBootTest(classes = PoyectofinalApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroeControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	
	private String getRootUrl() {
		return "http://localhost:8086";
	}
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void getSuperAllTest() {
	ResponseEntity<List> superheroes = restTemplate.getForEntity(getRootUrl()+
				"/superheroe/all", List.class);
		assertThat(superheroes.getBody()).isNotNull();

	}
	
	@Test
	public void getSuperByIdTest() {
		Superheroe superheroe = restTemplate.getForObject(getRootUrl() +
				"/superheroe/8",Superheroe.class);
		assertThatObject(superheroe).isNotNull();
	}	

	
}

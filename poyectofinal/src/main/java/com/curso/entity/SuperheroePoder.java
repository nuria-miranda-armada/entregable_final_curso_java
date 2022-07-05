package com.curso.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/*
@Setter
@Getter
@Entity
@Table(name = "superheroe_poder")*/
public class SuperheroePoder implements Serializable{
/*
	@Id
	private SuperheroePoderId id;
	//Eager porque quiero que al llamar a un super me muestre el poder 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_superheroe", nullable = false,insertable = false, updatable = false)
	private Superheroe superheroe;
	
	//UNIMOS por la columna id con la tabla poder
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_poder", nullable = false, insertable = false, updatable = false)
	private Poder poder;
*/
}

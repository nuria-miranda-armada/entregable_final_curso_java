package com.curso.superheroe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.curso.universo.entity.Universo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "superheroe")
public class Superheroe {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "estado")
	private String estado; 
		
	/*
	//UNIMOS por la columna nombre con la tabla Universo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombreuniverso")
	private Universo universo;
	*/
	//UNIMOS por la columna nombre con la tabla Universo
	@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "id_universo")
		private Universo universo;
	}

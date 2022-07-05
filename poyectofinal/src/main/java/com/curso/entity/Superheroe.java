package com.curso.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "superheroe")
public class Superheroe implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
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
	//AL hacer la relacion, hibernate crea automaticamente una columna
	//con el nombre puesto en @JoinCOlumn

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_universo")
	private Universo universo;
	
	
	@ManyToMany(fetch = FetchType.EAGER,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
	@JoinTable(name = "superheroe_poder",
	        joinColumns = { @JoinColumn(name = "id_superheroe") },
	        inverseJoinColumns = { @JoinColumn(name = "id_poder") })
	  private Set<Poder> poderes = new HashSet<>();
	
	public void addPoder(Poder poder) {
		this.poderes.add(poder);
		poder.getSuperheroes().add(this);
	}
	
	public void removePoder(Integer idPoder) {
		Poder poder = this.poderes.stream()
				.filter(p->p.getId()==idPoder).findFirst()
				.orElse(null);
		if(poder!=null) {
			this.poderes.remove(poder);
			poder.getSuperheroes().remove(this);
		}
	
	}
	
	/*
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_poder")
	private Poder poder;
	*/
	/*
	 @OneToMany(mappedBy = "superheroe",fetch = FetchType.EAGER)
	    List<SuperheroePoder>superheroePoder;
   */
	}

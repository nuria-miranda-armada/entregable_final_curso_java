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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name= "poder")
public class Poder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre", length = 50)
	private String nombre;

	 @ManyToMany(fetch = FetchType.EAGER,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "poderes")
		  @JsonIgnore
		  private Set<Superheroe> superheroes = new HashSet<>();
	 	
	 public Set<Superheroe> getSuperheroes() {
		    return superheroes;
		  }
	 
	 public void setSuperheroes(Set<Superheroe> superheroes) {
		    this.superheroes = superheroes;
		  }  
	 
	 
	 
	 /*
 * 
 * 
 * 
 * 
	 @OneToMany(mappedBy = "poder")
	    List<SuperheroePoder>superheroePoder;
*/
	
}

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
public class Superheroe implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "nombre", length = 50)
	private String nombre;

	@Column(name = "estado")
	private String estado;

	//Hibernate automáticamente crea una columna id_universo en la tabla superheroe,
	//que se usa para vincular el superhéroe con su respectivo universo.
	//@JoinColumn(name = "id_universo"): Define que la columna de la tabla superheroe que almacena la FK será id_universo.
	@ManyToOne(
			fetch = FetchType.EAGER, // Se cargan los poderes asociados de inmediato al consultar un superhéroe
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }
			) // Estas operaciones se aplican a los poderes asociados
	@JoinColumn(name = "id_universo") //Hibernate automáticamente crea una columna "id_universo" en la tabla superheroe,
	private Universo universo;

	//un superhéroe puede tener muchos poderes, y un poder puede ser utilizado por
	// muchos superhéroes.
	@ManyToMany(
			fetch = FetchType.EAGER, 
			cascade = { CascadeType.MERGE, CascadeType.PERSIST }
			)

	// Un superhéroe puede tener muchos poderes y un poder puede ser asociado con muchos superhéroes.
	// Este tipo de relación necesita una tabla intermedia que es definida por la anotación @JoinTable
	@JoinTable(name = "superheroe_poder", 
				joinColumns = { @JoinColumn(name = "id_superheroe") }, // la columna tabla que se usará para almacenar las FK será id_superheroe.
				inverseJoinColumns = { @JoinColumn(name = "id_poder") })//la columna de la tabla que se usará para almacenar las FK será id_poder.
	private Set<Poder> poderes = new HashSet<>();

	public void addPoder(Poder poder) {
	        this.poderes.add(poder);
	        poder.getSuperheroes().add(this); // Ensure the reverse side is also updated (if necessary)
	    }
	
	public void removePoder(Poder poder) {
        this.poderes.remove(poder);
        poder.getSuperheroes().remove(this); // Ensure the reverse side is also updated (if necessary)
    }
	
}

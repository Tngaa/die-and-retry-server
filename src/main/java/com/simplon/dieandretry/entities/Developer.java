package com.simplon.dieandretry.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "developers")
public class Developer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8981194826696657395L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Integer id;

	@Column(name = "name")
    private String name;
	
	@OneToMany(mappedBy = "developer")
	@JsonIgnore
	private List<Videogame> videogames;

	public Developer() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Videogame> getVideogames() {
		return videogames;
	}

	public void setVideogames(List<Videogame> videogames) {
		this.videogames = videogames;
	}
}
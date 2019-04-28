package com.simplon.dieandretry.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simplon.dieandretry.entities.Videogame;

public interface VideogameRepository extends CrudRepository<Videogame, Integer> {

}
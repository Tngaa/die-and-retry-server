package com.simplon.dieandretry.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simplon.dieandretry.entities.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Integer> {

}
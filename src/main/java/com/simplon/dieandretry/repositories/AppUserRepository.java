package com.simplon.dieandretry.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simplon.dieandretry.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	 public AppUser findByUsername(String username);
}

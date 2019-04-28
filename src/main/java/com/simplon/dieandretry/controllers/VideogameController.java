package com.simplon.dieandretry.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.dieandretry.entities.Developer;
import com.simplon.dieandretry.entities.Platform;
import com.simplon.dieandretry.entities.Publisher;
import com.simplon.dieandretry.entities.Videogame;
import com.simplon.dieandretry.repositories.DeveloperRepository;
import com.simplon.dieandretry.repositories.PlatformRepository;
import com.simplon.dieandretry.repositories.PublisherRepository;
import com.simplon.dieandretry.repositories.VideogameRepository;

@RestController
@RequestMapping("/videogames")
public class VideogameController {
	
	@Autowired
	VideogameRepository videogameRepository;
	
	@Autowired
	PlatformRepository platformRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	DeveloperRepository developerRepostiory;

	@GetMapping
	public List<Videogame> getAllVideogames() {
		return (List<Videogame>) videogameRepository.findAll();
	}
	
	@PostMapping
	public Videogame createVideogame(@RequestBody Videogame videogame) throws Exception {
		if (videogame.getPlatform() == null) {
			throw new Exception("Missing platform");
		}
		
		if (videogame.getPublisher() == null) {
			throw new Exception("Missing publisher");
		}
		
		if (videogame.getDeveloper() == null) {
			throw new Exception("Missing developer");
		}
		
		Videogame v = videogameRepository.save(videogame);
		
		return v;
	}
	
	@PutMapping(path = "/{id}")
	public Videogame updateVideogame(@PathVariable Integer id, @RequestBody Videogame videogame) throws Exception {
		if (videogame.getPlatform() == null) {
			throw new Exception("Missing platform");
		}
		
		if (videogame.getPublisher() == null) {
			throw new Exception("Missing publisher");
		}
		
		if (videogame.getDeveloper() == null) {
			throw new Exception("Missing developer");
		}
		
		Optional<Videogame> optVideogame = videogameRepository.findById(id);
		
		if (!optVideogame.isPresent()) {
			throw new Exception("Updating inexistent videogame");
		}
		
		Videogame exVideogame = optVideogame.get();
		exVideogame.setTitle(videogame.getTitle());
		exVideogame.setReleaseDate(videogame.getReleaseDate());
		exVideogame.setPlatform(videogame.getPlatform());
		exVideogame.setPublisher(videogame.getPublisher());
		exVideogame.setDeveloper(videogame.getDeveloper());
		
		exVideogame = videogameRepository.save(exVideogame);
		
		return exVideogame;
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteVideogame(@PathVariable Integer id) {
		videogameRepository.deleteById(id);
	}
	
	@GetMapping(path = "/platforms")
	public List<Platform> getAllPlatforms() {
		return (List<Platform>) platformRepository.findAll();
	}
	
	@GetMapping(path = "/publishers")
	public List<Publisher> getAllPublishers() {
		return (List<Publisher>) publisherRepository.findAll();
	}
	
	@GetMapping(path = "/developers")
	public List<Developer> getAllDevelopers() {
		return (List<Developer>) developerRepostiory.findAll();
	}
}
package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Artwork;
import com.example.demo.service.ArtworkService;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkRestController {

	@Autowired
	private ArtworkService artworkService;

	@GetMapping
	public List<Artwork> allArtworks() {
		return artworkService.getAllArtworks();
	}

	@GetMapping("/{id}")
	public Artwork getArtwork(@PathVariable long id) {
		return artworkService.getArtworkById(id);
	}

	@PostMapping("/new")
	public Artwork create(@RequestBody Artwork artwork) {
		return artworkService.insertNewArtwork(artwork);
	}

	@PutMapping("/{id}")
	public Artwork update(@PathVariable long id, @RequestBody Artwork replacement) {
		return artworkService.updateArtworkById(id, replacement);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		artworkService.deleteArtworkById(id);
	}
}
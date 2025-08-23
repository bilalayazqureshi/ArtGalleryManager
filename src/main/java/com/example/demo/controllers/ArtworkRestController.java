package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public Artwork artwork(@PathVariable long id) {
		return artworkService.getArtworkById(id);
	}
}
package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Artwork;
import com.example.demo.service.ArtworkService;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkRestController {

	private final ArtworkService artworkService;

	public ArtworkRestController(ArtworkService artworkService) {
		this.artworkService = artworkService;
	}

	@GetMapping
	public List<Artwork> allArtworks() {
		return artworkService.getAllArtworks();
	}
}
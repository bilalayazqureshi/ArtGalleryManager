package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
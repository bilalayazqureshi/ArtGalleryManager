package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Artwork;

@RestController
public class ArtworkRestController {
	@GetMapping("/api/artworks")
	public List<Artwork> allArtworks() {
		return Collections.emptyList();
	}
}
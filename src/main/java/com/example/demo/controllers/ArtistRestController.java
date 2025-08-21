package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Artist;

@RestController
public class ArtistRestController {

	@GetMapping("/api/artists")
	public List<Artist> allArtists() {
		return Collections.emptyList();
	}
}
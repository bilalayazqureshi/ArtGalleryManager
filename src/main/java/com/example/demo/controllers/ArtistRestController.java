package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Artist;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

	@GetMapping
	public List<Artist> allArtists() {
		return List.of(new Artist(1L, "Pablo Picasso", "Spanish"));
	}
}
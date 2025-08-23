package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Artist;
import com.example.demo.service.ArtistService;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

	@Autowired
	private ArtistService artistService;

	@GetMapping
	public List<Artist> allArtists() {
		return artistService.getAllArtists();
	}
}
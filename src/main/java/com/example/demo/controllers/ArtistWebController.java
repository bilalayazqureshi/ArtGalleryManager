package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Artist;
import com.example.demo.services.ArtistService;

@Controller
@RequestMapping("/artists")
public class ArtistWebController {

	@Autowired
	private ArtistService artistService;

	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String ARTIST_ATTRIBUTE = "artist";
	private static final String ARTISTS_ATTRIBUTE = "artists";

	// --- LIST / READ ALL ---
	@GetMapping
	public String listArtists(Model model) {
		List<Artist> allArtists = artistService.getAllArtists();
		model.addAttribute(ARTISTS_ATTRIBUTE, allArtists);
		model.addAttribute(MESSAGE_ATTRIBUTE, allArtists.isEmpty() ? "No artist" : "");
		return "artist";
	}
}
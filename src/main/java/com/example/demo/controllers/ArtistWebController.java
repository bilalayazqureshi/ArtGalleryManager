package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Artist;
import com.example.demo.services.ArtistService;

@Controller
@RequestMapping("/artists")
public class ArtistWebController {

	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String ARTIST_ATTRIBUTE = "artist";
	private static final String ARTISTS_ATTRIBUTE = "artists";

	@Autowired
	private ArtistService artistService;

	@GetMapping
	public String listArtists(Model model) {
		List<Artist> allArtists = artistService.getAllArtists();
		model.addAttribute(ARTISTS_ATTRIBUTE, allArtists);
		model.addAttribute(MESSAGE_ATTRIBUTE, allArtists.isEmpty() ? "No artist" : "");
		return "artist";
	}

	@GetMapping("/edit/{id}")
	public String editArtist(@PathVariable long id, Model model) {
		Artist artist = artistService.getArtistById(id);
		model.addAttribute(ARTIST_ATTRIBUTE, artist);
		model.addAttribute(MESSAGE_ATTRIBUTE, artist == null ? "No artist found with id: " + id : "");
		return "edit_artist";
	}

	@GetMapping("/new")
	public String newArtist(Model model) {
		model.addAttribute(ARTIST_ATTRIBUTE, new Artist());
		model.addAttribute(MESSAGE_ATTRIBUTE, "");
		return "artist";
	}

	@PostMapping("/save")
	public String saveArtist(Artist artist) {
		if (artist.getId() == null) {
			artistService.insertNewArtist(artist);
		} else {
			artistService.updateArtistById(artist.getId(), artist);
		}
		return "redirect:/artists";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteArtist(@PathVariable long id) {
		artistService.deleteArtistById(id);
		return "redirect:/artists";
	}
}
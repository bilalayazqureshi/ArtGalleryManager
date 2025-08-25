package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Artwork;
import com.example.demo.services.ArtworkService;

@Controller
@RequestMapping("/artworks")
public class ArtworkWebController {

	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String ARTWORK_ATTRIBUTE = "artwork";
	private static final String ARTWORKS_ATTRIBUTE = "artworks";

	@Autowired
	private ArtworkService artworkService;

	@GetMapping
	public String listArtworks(Model model) {
		List<Artwork> allArtworks = artworkService.getAllArtworks();
		model.addAttribute(ARTWORKS_ATTRIBUTE, allArtworks);
		model.addAttribute(MESSAGE_ATTRIBUTE, allArtworks.isEmpty() ? "No artwork" : "");
		return "artwork";
	}

	@GetMapping("/edit/{id}")
	public String editArtwork(@PathVariable long id, Model model) {
		Artwork artwork = artworkService.getArtworkById(id);
		model.addAttribute(ARTWORK_ATTRIBUTE, artwork);
		model.addAttribute(MESSAGE_ATTRIBUTE, artwork == null ? "No artwork found with id: " + id : "");
		return "edit_artwork";
	}

	@GetMapping("/new")
	public String newArtwork(Model model) {
		model.addAttribute(ARTWORK_ATTRIBUTE, new Artwork());
		model.addAttribute(MESSAGE_ATTRIBUTE, "");
		return "artwork";
	}

	@PostMapping("/save")
	public String saveArtwork(Artwork artwork) {
		if (artwork.getId() == null) {
			artworkService.insertNewArtwork(artwork);
		} else {
			artworkService.updateArtworkById(artwork.getId(), artwork);
		}
		return "redirect:/artworks";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteArtwork(@PathVariable long id) {
		artworkService.deleteArtworkById(id);
		return "redirect:/artworks";
	}
}
package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Artwork;
import com.example.demo.services.ArtworkService;

@Controller
@RequestMapping("/artworks")
public class ArtworkWebController {

	private static final String MESSAGE_ATTRIBUTE = "message";
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
}
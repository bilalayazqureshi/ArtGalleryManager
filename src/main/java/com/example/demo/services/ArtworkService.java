package com.example.demo.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;

@Service
public class ArtworkService {

	private final Map<Long, Artwork> artworks = new LinkedHashMap<>();

	public ArtworkService() {

		artworks.put(1L, new Artwork(1L, "Mona Lisa", "Portrait of a woman", 1503));
		artworks.put(2L, new Artwork(2L, "The Two Fridas", "Double self-portrait", 1939));
	}

	public List<Artwork> getAllArtworks() {
		return new ArrayList<>(artworks.values());
	}

	public Artwork getArtworkById(long id) {
		return artworks.get(id);
	}

	public Artwork insertNewArtwork(Artwork artwork) {
		long newId = artworks.size() + 1L;
		artwork.setId(newId);
		artworks.put(newId, artwork);
		return artwork;
	}

	public Artwork updateArtworkById(long id, Artwork replacement) {
		replacement.setId(id);
		artworks.put(id, replacement);
		return replacement;
	}

	public void deleteArtworkById(long id) {
		artworks.remove(id);
	}
}

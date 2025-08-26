package com.example.demo.services;

import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;

@Service
public class ArtworkService {

	public List<Artwork> getAllArtworks() {
		Artwork a1 = new Artwork();
		a1.setId(1L);
		a1.setTitle("Sample Artwork 1");

		Artwork a2 = new Artwork();
		a2.setId(2L);
		a2.setTitle("Sample Artwork 2");

		return Arrays.asList(a1, a2);
	}

	public Artwork getArtworkById(Long id) {
		return getAllArtworks().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
	}

	public void insertNewArtwork(Artwork artwork) {

	}

	public void updateArtworkById(Long id, Artwork updated) {

	}

	public void deleteArtworkById(Long id) {

	}
}

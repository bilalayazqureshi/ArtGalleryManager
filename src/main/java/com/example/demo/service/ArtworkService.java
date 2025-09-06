package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;
import com.example.demo.repositories.ArtworkRepository;

@Service
public class ArtworkService {

	private final ArtworkRepository artworkRepository;

	public ArtworkService(ArtworkRepository artworkRepository) {
		this.artworkRepository = artworkRepository;
	}

	public Artwork getArtworkById(long id) {
		Optional<Artwork> optionalArtwork = artworkRepository.findById(id);
		return optionalArtwork.orElse(null);
	}

	public List<Artwork> getAllArtworksByIds(List<Long> ids) {
		return artworkRepository.findAllById(ids);
	}

	public Artwork insertNewArtwork(Artwork artwork) {
		return artworkRepository.save(artwork);
	}

	public Artwork updateArtworkById(long id, Artwork updatedArtwork) {
		Optional<Artwork> existingArtwork = artworkRepository.findById(id);
		if (existingArtwork.isPresent()) {
			return artworkRepository.save(updatedArtwork);
		}
		return null;
	}

	public void deleteArtworkById(long id) {
		artworkRepository.deleteById(id);
	}

	public List<Artwork> getAllArtworks() {
		return artworkRepository.findAll();
	}
}
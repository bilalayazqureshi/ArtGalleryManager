package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.ArtworkRepository;

@Service
public class ArtworkService {

	private final ArtworkRepository artworkRepository;
	private final ArtistRepository artistRepository;

	public ArtworkService(ArtworkRepository artworkRepository, ArtistRepository artistRepository) {
		this.artworkRepository = artworkRepository;
		this.artistRepository = artistRepository;
	}

	public Artwork getArtworkById(long id) {
		Optional<Artwork> optionalArtwork = artworkRepository.findById(id);
		return optionalArtwork.orElse(null);
	}

	public List<Artwork> getAllArtworksByIds(List<Long> ids) {
		return artworkRepository.findAllById(ids);
	}

	public Artwork insertNewArtwork(Artwork artwork) {
		if (artwork.getArtist() != null && artwork.getArtist().getId() != null) {
			Optional<Artist> artistOpt = artistRepository.findById(artwork.getArtist().getId());
			artistOpt.ifPresent(artwork::setArtist);
		}
		return artworkRepository.save(artwork);
	}

	public Artwork updateArtworkById(long id, Artwork updatedArtwork) {
		Optional<Artwork> existingArtwork = artworkRepository.findById(id);
		if (existingArtwork.isPresent()) {
			if (updatedArtwork.getArtist() != null && updatedArtwork.getArtist().getId() != null) {
				Optional<Artist> artistOpt = artistRepository.findById(updatedArtwork.getArtist().getId());
				artistOpt.ifPresent(updatedArtwork::setArtist);
			}
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
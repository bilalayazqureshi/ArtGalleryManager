package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;
import com.example.demo.repository.ArtworkRepository;

@Service
public class ArtworkService {
	
	private ArtworkRepository artworkRepository; 
	
	public ArtworkService(ArtworkRepository artworkRepository) {
		this.artworkRepository = artworkRepository;
	}

	public Artwork getArtworkById(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public Artwork insertNewArtwork(Artwork artwork) {
		// TODO Auto-generated method stub
		return null;
	}

	public Artwork updateArtworkById(long l, Artwork updatedArtwork) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteArtworkById(long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Artwork> getAllArtworks() {
		// TODO Auto-generated method stub
		return null;
	}

}

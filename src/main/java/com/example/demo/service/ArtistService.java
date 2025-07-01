package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;

@Service
public class ArtistService {

	private ArtistRepository artistRepository;
	
	
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}


	public Artist getArtistById(long l) {
		// TODO Auto-generated method stub
		return null;
	}


	public Artist insertNewArtist(Artist artist) {
		// TODO Auto-generated method stub
		return null;
	}


	public Artist updateArtistById(long l, Artist updatedArtist) {
		// TODO Auto-generated method stub
		return null;
	}


	public void deleteArtistById(long id) {
		// TODO Auto-generated method stub
		
	}


	public List<Artist> getAllArtists() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

package com.example.demo.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;

@Service
public class ArtistService {

	private final Map<Long, Artist> artists = new LinkedHashMap<>();

	public ArtistService() {

		artists.put(1L, new Artist(1L, "Leonardo da Vinci", "Italian Renaissance polymath"));
		artists.put(2L, new Artist(2L, "Frida Kahlo", "Mexican painter known for self-portraits"));
	}

	public List<Artist> getAllArtists() {
		return new ArrayList<>(artists.values());
	}

	public Artist getArtistById(long id) {
		return artists.get(id);
	}

	public Artist insertNewArtist(Artist artist) {
		long newId = artists.size() + 1L;
		artist.setId(newId);
		artists.put(newId, artist);
		return artist;
	}

	public Artist updateArtistById(long id, Artist replacement) {
		replacement.setId(id);
		artists.put(id, replacement);
		return replacement;
	}

	public void deleteArtistById(long id) {
		artists.remove(id);
	}
}

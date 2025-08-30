package com.example.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Artwork;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ArtworkRepositoryTest {

	@Autowired
	private ArtworkRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void firstLearningTest() {
		Artwork artwork = new Artwork(null, "Mona Lisa", "Oil", 1503);
		Artwork saved = repository.save(artwork);
		Collection<Artwork> artworks = repository.findAll();
		assertThat(artworks).containsExactly(saved);
	}

	@Test
	void secondLearningTest() {
		Artwork artwork = new Artwork(null, "Mona Lisa", "Oil", 1503);
		Artwork saved = entityManager.persistFlushFind(artwork);
		Collection<Artwork> artworks = repository.findAll();
		assertThat(artworks).containsExactly(saved);
	}

	@Test
	void test_findByTitle() {
		Artwork saved = entityManager.persistFlushFind(new Artwork(null, "Guernica", "Oil", 1937));
		Artwork found = repository.findByTitle("Guernica");
		assertThat(found).isEqualTo(saved);
	}

	@Test
	void test_findByTitleAndMedium() {
		entityManager.persistFlushFind(new Artwork(null, "Starry Night", "Ink", 1889));
		Artwork match = entityManager.persistFlushFind(new Artwork(null, "Starry Night", "Oil on canvas", 1889));
		List<Artwork> found = repository.findByTitleAndMedium("Starry Night", "Oil on canvas");
		assertThat(found).containsExactly(match);
	}

	@Test
	void test_findByTitleOrMedium() {
		Artwork a1 = entityManager.persistFlushFind(new Artwork(null, "The Scream", "Tempera", 1893));
		Artwork a2 = entityManager.persistFlushFind(new Artwork(null, "Another", "Charcoal", 1900));
		entityManager.persistFlushFind(new Artwork(null, "Ignore", "Pastel", 1910));
		List<Artwork> found = repository.findByTitleOrMedium("The Scream", "Charcoal");
		assertThat(found).containsExactly(a1, a2);
	}

	@Test
	void test_findByYearCreatedBefore() {
		Artwork a1 = entityManager.persistFlushFind(new Artwork(null, "Old1", "Oil", 1400));
		Artwork a2 = entityManager.persistFlushFind(new Artwork(null, "Old2", "Fresco", 1450));
		entityManager.persistFlushFind(new Artwork(null, "Modern", "Acrylic", 2000));
		List<Artwork> found = repository.findAllByYearCreatedBefore(1500);
		assertThat(found).containsExactly(a1, a2);
	}

	@Test
	void testCreateArtwork() {
		Artwork artwork = new Artwork(null, "The Kiss", "Oil and gold leaf", 1907);
		Artwork saved = repository.save(artwork);
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getTitle()).isEqualTo("The Kiss");
		assertThat(saved.getMedium()).isEqualTo("Oil and gold leaf");
	}

	@Test
	void testReadArtworkById() {
		Artwork artwork = entityManager.persistFlushFind(new Artwork(null, "Girl with a Pearl Earring", "Oil", 1665));
		Optional<Artwork> foundOpt = repository.findById(artwork.getId());
		assertThat(foundOpt).isPresent();

		Artwork found = foundOpt.get();
		assertThat(found.getTitle()).isEqualTo("Girl with a Pearl Earring");
		assertThat(found.getMedium()).isEqualTo("Oil");
	}

	@Test
	void testUpdateArtwork() {
		Artwork artwork = entityManager.persistFlushFind(new Artwork(null, "Draft", "Sketch", 1900));
		artwork.setTitle("Final Version");
		artwork.setMedium("Oil");
		Artwork updated = repository.save(artwork);
		entityManager.flush();
		entityManager.clear();

		Artwork reloaded = repository.findById(updated.getId()).orElseThrow();
		assertThat(reloaded.getTitle()).isEqualTo("Final Version");
		assertThat(reloaded.getMedium()).isEqualTo("Oil");
	}

	@Test
	void testDeleteArtwork() {
		Artwork artwork = entityManager.persistFlushFind(new Artwork(null, "To be deleted", "Pastel", 1990));
		repository.deleteById(artwork.getId());
		entityManager.flush();

		boolean exists = repository.existsById(artwork.getId());
		assertThat(exists).isFalse();
	}
}
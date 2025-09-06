package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.demo.model.Artwork;
import com.example.demo.pages.ArtworkFormPage;
import com.example.demo.pages.ArtworkListPage;
import com.example.demo.repositories.ArtworkRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ArtworkWebControllerIT {

	@Autowired
	private ArtworkRepository artworkRepository;

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private ArtworkListPage listPage;

	@BeforeEach
	void setup() {
		driver = new HtmlUnitDriver();
		artworkRepository.deleteAll();
		listPage = new ArtworkListPage(driver, port);
	}

	@AfterEach
	void tearDown() {
		driver.quit();
	}

	@Test
	void test_HomePageEmpty_ShowsNoArtwork() {
		listPage.open();
		assertThat(listPage.isEmpty()).isTrue();
	}

	@Test
	void test_HomePageWithArtworks_ShowsThemInTable() {
		artworkRepository.save(new Artwork(null, "A1", "Oil", 2025));
		artworkRepository.save(new Artwork(null, "A2", "Acrylic", 2026));

		listPage.open();
		String txt = listPage.tableText();
		assertThat(txt).contains("1", "A1", "Oil", "2025", "0 artist", "Edit", "Delete");
		assertThat(txt).contains("2", "A2", "Acrylic", "2026", "0 artist", "Edit", "Delete");
	}

	@Test
	void test_CanCreateArtworkAndSeeIt() {
		ArtworkFormPage form = listPage.open().clickNew();
		form.setTitle("MyArt").setMedium("Ink").setYearCreated("2027").submit();

		listPage.open();
		assertThat(listPage.tableText()).contains("MyArt", "Ink", "2027");
	}

	@Test
	void test_CanUpdateArtworkAndSeeChanges() {
		Artwork a = artworkRepository.save(new Artwork(null, "OldTitle", "Charcoal", 2022));

		ArtworkFormPage form = listPage.open().clickEdit(a.getId());
		form.setTitle("UpdatedTitle").setMedium("Watercolor").setYearCreated("2023").submit();

		listPage.open();
		assertThat(listPage.tableText()).contains("UpdatedTitle", "Watercolor", "2023");
	}

	@Test
	void test_CanDeleteArtworkAndItDisappears() {
		Artwork a = artworkRepository.save(new Artwork(null, "DeleteMe", "Spray", 2020));

		listPage.open().clickDelete(a.getId());
		String h1 = driver.findElement(By.tagName("h1")).getText();
		assertThat(h1).contains("Artwork with ID " + a.getId() + " was deleted.");

		driver.findElement(By.cssSelector("form[action='/artworks'] button")).click();
		listPage.open();
		if (listPage.isEmpty()) {
			assertThat(driver.findElement(By.xpath("//*[text()='No artwork']")).isDisplayed()).isTrue();
		} else {
			assertThat(listPage.tableText()).doesNotContain("DeleteMe");
		}
	}
}
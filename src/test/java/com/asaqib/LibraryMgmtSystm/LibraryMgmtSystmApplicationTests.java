package com.asaqib.LibraryMgmtSystm;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.asaqib.LibraryMgmtSystm.model.Books;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryMgmtSystmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryMgmtSystmApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
	return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateBook() {
	Books book = new Books();
	book.setIsbn(12345);
	book.setTitle("Science");
	book.setAuthor("Jhon");
	book.setPrice("40");
	ResponseEntity<Books> postResponse = restTemplate.postForEntity(getRootUrl() + "/addBooks", book, Books.class);
	Assert.assertNotNull(postResponse);
	Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testGetAllBooks() {
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/getBooks",
	HttpMethod.GET, entity, String.class);
	Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetBooksById() {
	Books book = restTemplate.getForObject(getRootUrl() + "/books/12345", Books.class);
	System.out.println(book.getTitle());
	Assert.assertNotNull(book);
	}
	
	@Test
	public void testUpdateBooks() {
	int id = 12345;
	Books book = restTemplate.getForObject(getRootUrl() + "/updateBooks/" + id, Books.class);
	book.setAuthor("Benjamin");
	restTemplate.put(getRootUrl() + "/users/" + id, book);
	Books updatedUser = restTemplate.getForObject(getRootUrl() + "/updateBooks/" + id, Books.class);
	Assert.assertNotNull(updatedUser);
	}
	
	@Test
	public void testDeleteBooks() {
	int id = 12345;
	Books book = restTemplate.getForObject(getRootUrl() + "/deleteBooks/" + id, Books.class);
	Assert.assertNotNull(book);
	restTemplate.delete(getRootUrl() + "/deleteBooks/" + id);
	try {
	book = restTemplate.getForObject(getRootUrl() + "/users/" + id, Books.class);
	} catch (final HttpClientErrorException e) {
	Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	}
	
	
}

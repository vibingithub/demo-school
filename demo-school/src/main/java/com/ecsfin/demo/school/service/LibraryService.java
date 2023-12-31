package com.ecsfin.demo.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecsfin.demo.school.model.Book;
import com.ecsfin.demo.school.model.BookLending;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class LibraryService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${library.service-url}")
	private String libraryServiceURL;
	
	private final String LIBRARY_SERVICE = "libraryService";
	
	@CircuitBreaker(name = LIBRARY_SERVICE, fallbackMethod = "getFallbackBook")
	//@Retry(name = LIBRARY_SERVICE, fallbackMethod = "getFallbackBook")
	//@RateLimiter(name = LIBRARY_SERVICE, fallbackMethod = "getFallbackBook")
	//@Bulkhead(name = LIBRARY_SERVICE, fallbackMethod = "getFallbackBook")
	public Book getBook(BookLending bookLending) {
		ResponseEntity<Book> bookResponseEntity 
			=  restTemplate.getForEntity(libraryServiceURL+"/api/books/"+bookLending.getBookId(), Book.class);
		Book book = bookResponseEntity.getBody();
		book.setStatus(bookLending.getStatus());
		return book;
	}
	
	public Book getFallbackBook(BookLending bookLending, Throwable t) {
		return Book.builder()
				.name("Book not Found")
				.build();
	}
}

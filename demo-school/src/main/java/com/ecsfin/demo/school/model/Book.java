package com.ecsfin.demo.school.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

	private String bookId;
	private String name;
	private String genre;
	private String language;
	
	private String status;
}

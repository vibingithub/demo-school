package com.ecsfin.demo.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLending {

	private String lendId;
	private String studentId;
	private String bookId;
	private String status;
}

package com.ecsfin.demo.school.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

	private String id;
	private String name;
	private String studyingClass;
	private int age;
	private String sex;
	
	private List<Book> books;
}

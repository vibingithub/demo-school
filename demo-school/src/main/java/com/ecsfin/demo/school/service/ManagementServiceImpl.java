package com.ecsfin.demo.school.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecsfin.demo.school.model.Book;
import com.ecsfin.demo.school.model.BookLending;
import com.ecsfin.demo.school.model.Books;
import com.ecsfin.demo.school.model.Student;
import com.ecsfin.demo.school.model.Students;

@Service
public class ManagementServiceImpl implements ManagementService{
	
	List<BookLending> bookLendinglist =  Arrays.asList(
			new BookLending("L1", "S1", "B1", "OUT"),
			new BookLending("L2", "S1", "B2", "IN"),
			new BookLending("L3", "S2", "B3", "OUT"),
			new BookLending("L4", "S3", "B4", "OUT"),
			new BookLending("L5", "S2", "B2", "OUT"),
			new BookLending("L6", "S4", "B5", "IN"),
			new BookLending("L7", "S5", "B6", "OUT"),
			new BookLending("L8", "S5", "B7", "OUT")
			);

	@Autowired
	StudentService studentService;
	
	@Autowired
	LibraryService libraryService;
	
	@Override
	public Student getBooksUsedByStudent(String studentId) {
		Student student = studentService.getStudent(studentId);

		student.setBooks(bookLendinglist.stream()
								.filter(b->b.getStudentId().equals(studentId))
								.map(b->libraryService.getBook(b))
								.collect(Collectors.toList()));
		
		return student;
	}
}

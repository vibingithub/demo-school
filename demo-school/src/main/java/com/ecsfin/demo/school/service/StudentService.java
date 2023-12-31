package com.ecsfin.demo.school.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecsfin.demo.school.model.Student;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class StudentService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${student.service-url}")
	private String studentServiceURL;
	
	private final String STUDENT_SERVICE = "studentService";
	
	
	@CircuitBreaker(name = STUDENT_SERVICE, fallbackMethod = "getFallBackStudent")
	//@Retry(name = STUDENT_SERVICE, fallbackMethod = "getFallBackStudent")
	//@RateLimiter(name = STUDENT_SERVICE, fallbackMethod = "getFallBackStudent")
	//@Bulkhead(name = STUDENT_SERVICE, fallbackMethod = "getFallBackStudent")
	public Student getStudent(String studentId) {
		ResponseEntity<Student> responseEntity 
		= restTemplate.getForEntity(studentServiceURL+"/api/students/"+studentId, Student.class);
		
		Student student = responseEntity.getBody();
		
		return student;
	}
	
	public Student getFallBackStudent(String studentId, Throwable t) {
		return Student.builder()
					.name("No Student Found")
					.build();
	}
}

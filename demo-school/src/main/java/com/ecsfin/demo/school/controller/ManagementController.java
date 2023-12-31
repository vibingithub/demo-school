package com.ecsfin.demo.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecsfin.demo.school.model.Student;
import com.ecsfin.demo.school.service.ManagementService;

@RestController
@RequestMapping("/api/mgmt")
public class ManagementController {
	
	@Autowired
	ManagementService managementService;

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getBooksUsedByStudent(@PathVariable String studentId){
		Student student = managementService.getBooksUsedByStudent(studentId);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}

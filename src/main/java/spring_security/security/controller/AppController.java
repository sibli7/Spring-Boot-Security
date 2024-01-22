package spring_security.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_security.security.entity.Student;
import spring_security.security.repository.StudentRepo;

@RestController
public class AppController {

     @Autowired
     StudentRepo studentRepo;

     @GetMapping("/students")
	public ResponseEntity<List<Student>> studen_list(Model model) {

		List<Student> student = studentRepo.findAll();

		// model.addAttribute("student",student);

		return ResponseEntity.ok(student);
	}
     
}

package com.example.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lesson04.bo.NewStudentBO;
import com.example.lesson04.model.NewStudent;

@RequestMapping("/lesson04/ex02")
@Controller
public class NewStudentController {
	
	@Autowired
	private NewStudentBO newStudentBO;
	
	// 요청 url: 	http://localhost:8080/lesson04/ex02/1
	@GetMapping("/1")
	public String addStudentView() {
		return "lesson04/addStudent";
	}
	
	// 요청 url: 	http://localhost:8080/lesson04/ex02/add_student
	// PostMapping이기 때문에 이쪽에서 바로 시작할 수 없다.
	@PostMapping("/add_student")
	public String addStudent(
			@ModelAttribute NewStudent newStudent,	// name 태그 값과 일치하는 필드에 값이 들어간다.
			Model model
			) {
		// insert DB
		newStudentBO.addNewStudent(newStudent);
		
		// select DB
		// 아래 방식보단 내려주는 객체는 새로 만드는게 좋다.
		newStudent = newStudentBO.getNewStudentById(newStudent.getId());
		model.addAttribute("result", newStudent);
		model.addAttribute("subject", "학생 정보");
		
		return "lesson04/afterAddStudent";	// 추가된 학생 정보를 나타내는 jsp
	}
}
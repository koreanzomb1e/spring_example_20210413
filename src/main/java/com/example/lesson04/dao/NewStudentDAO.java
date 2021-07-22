package com.example.lesson04.dao;

import org.springframework.stereotype.Repository;

import com.example.lesson04.model.NewStudent;

@Repository
public interface NewStudentDAO {

	public void addNewStudent(NewStudent newStudent);
	
	public NewStudent selectNewStudentById(int id);
}

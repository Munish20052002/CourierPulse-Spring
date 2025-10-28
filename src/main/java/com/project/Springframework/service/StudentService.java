package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Student;
import com.project.Springframework.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
	
	public Student findStudentById(Integer id) {
		return studentRepository.findById(id).orElseThrow();
	}
	public void deleteStudenteById(Integer id) {
		studentRepository.deleteById(id);
	}

	public List<Student> findAllStudent() {
		return studentRepository.findAll();
	}

}

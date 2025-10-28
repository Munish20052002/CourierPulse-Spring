package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

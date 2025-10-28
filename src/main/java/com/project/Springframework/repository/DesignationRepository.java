package com.project.Springframework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {

	List<Designation> findBydepartmentId(Integer departmentId);

}

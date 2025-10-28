package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.CourierItem;


@Repository
public interface CourierItemRepository extends JpaRepository<CourierItem, Integer> {

}

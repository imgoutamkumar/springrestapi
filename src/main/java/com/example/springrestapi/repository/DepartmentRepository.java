package com.example.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}